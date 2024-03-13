import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        int numberOfThreads = 2;
        int[] arr = new int[1000000000];
        Random random = new Random();
        initiateArray(arr, random);
        arr[99932345] = -1;

        List<FindMinTask> tasks = getTasks(arr, numberOfThreads);
        ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
        MinValueHolder holder = new MinValueHolder();

        long currentMilliseconds = System.currentTimeMillis();

        List<Future<Integer>> results = service.invokeAll(tasks);
        for (Future<Integer> result : results) {
            holder.trySetMin(result.get());
        }

        System.out.println("Time elapsed: " + (System.currentTimeMillis() - currentMilliseconds));
        System.out.println(holder.getMinValue());
        service.shutdown();

    }

    private static List<FindMinTask> getTasks(int[] arr, int numberOfThreads) {

        List<FindMinTask> tasks = new ArrayList<>();
        int startIndex = 0;
        int endIndex = arr.length / numberOfThreads;
        int bound = arr.length / numberOfThreads;

        while (numberOfThreads > 0) {

            if (numberOfThreads == 1)
                endIndex = arr.length;
            tasks.add(new FindMinTask(arr, startIndex, endIndex));
            numberOfThreads--;
            startIndex = endIndex;
            endIndex += bound;
        }

        return tasks;
    }

    private static void initiateArray(int[] array, Random random) {

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(0, 1000000);
        }

    }
}
