public class MinValueHolder {

    private volatile int minValue = Integer.MAX_VALUE;

    public void trySetMin(int min) {
        if (min < minValue) {
            synchronized (this) {
                if (min < minValue)
                    minValue = min;
            }
        }
    }

    public int getMinValue() {
        return minValue;
    }

}
