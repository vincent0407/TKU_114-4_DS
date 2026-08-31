import java.util.Arrays;
import java.util.List;

public class HeapPropertyValidator {

    public static boolean isMinHeap(List<Integer> list) {
        if (list == null) {
            return false;
        }
        if (list.size() <= 1) {
            return true;
        }

        int n = list.size();
        for (int i = 0; i <= (n - 2) / 2; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n && list.get(i) > list.get(left)) {
                return false;
            }
            if (right < n && list.get(i) > list.get(right)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isMaxHeap(List<Integer> list) {
        if (list == null) {
            return false;
        }
        if (list.size() <= 1) {
            return true;
        }

        int n = list.size();
        for (int i = 0; i <= (n - 2) / 2; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n && list.get(i) < list.get(left)) {
                return false;
            }
            if (right < n && list.get(i) < list.get(right)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<Integer> minHeapList = Arrays.asList(10, 15, 20, 17, 25);
        List<Integer> maxHeapList = Arrays.asList(50, 30, 40, 10, 20);
        List<Integer> invalidHeap = Arrays.asList(10, 5, 20);

        System.out.println("isMinHeap (valid): " + isMinHeap(minHeapList)); // true
        System.out.println("isMaxHeap (valid): " + isMaxHeap(maxHeapList)); // true
        System.out.println("isMinHeap (invalid): " + isMinHeap(invalidHeap)); // false
        System.out.println("Null test: " + isMinHeap(null)); // false
    }
}
