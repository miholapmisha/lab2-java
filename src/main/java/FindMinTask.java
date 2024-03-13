import java.util.concurrent.Callable;

public class FindMinTask implements Callable<Integer> {

    private final int[] arr;

    private final int lowerBound;

    private final int upperBound;

    public FindMinTask(int[] arr, int lowerBound, int upperBound) {
        this.arr = arr;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public Integer call() {
        int min = Integer.MAX_VALUE;

        for (int i = lowerBound; i < upperBound; i++) {
            if (arr[i] < min)
                min = arr[i];
        }

        return min;
    }
}
