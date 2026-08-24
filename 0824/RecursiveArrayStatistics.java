public class RecursiveArrayStatistics {

    public static int maximum(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty.");
        }
        return maxHelper(arr, 0);
    }

    private static int maxHelper(int[] arr, int index) {
        if (index == arr.length - 1) return arr[index];
        return Math.max(arr[index], maxHelper(arr, index + 1));
    }

    public static int minimum(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty.");
        }
        return minHelper(arr, 0);
    }

    private static int minHelper(int[] arr, int index) {
        if (index == arr.length - 1) return arr[index];
        return Math.min(arr[index], minHelper(arr, index + 1));
    }

    public static int countAbove(int[] arr, int limit) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty.");
        }
        return countAboveHelper(arr, limit, 0);
    }

    private static int countAboveHelper(int[] arr, int limit, int index) {
        if (index == arr.length) return 0;
        int count = (arr[index] > limit) ? 1 : 0;
        return count + countAboveHelper(arr, limit, index + 1);
    }

    public static void main(String[] args) {
        int[] data = {3, 7, 12, -2, 5, 18, 0};
        System.out.println("Max: " + maximum(data));
        System.out.println("Min: " + minimum(data));
        System.out.println("Count > 5: " + countAbove(data, 5));
    }
}
