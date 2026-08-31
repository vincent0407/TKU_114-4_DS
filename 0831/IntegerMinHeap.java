import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class IntegerMinHeap {
    private List<Integer> heap = new ArrayList<>();

    public void add(int val) {
        heap.add(val);
        siftUp(heap.size() - 1);
    }

    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return heap.get(0);
    }

    public int removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        int minVal = heap.get(0);
        int lastVal = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, lastVal);
            siftDown(0);
        }
        return minVal;
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.get(index) < heap.get(parent)) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    private void siftDown(int index) {
        int size = heap.size();
        while (index * 2 + 1 < size) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            int smallest = left;

            if (right < size && heap.get(right) < heap.get(left)) {
                smallest = right;
            }

            if (heap.get(index) > heap.get(smallest)) {
                swap(index, smallest);
                index = smallest;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public static void main(String[] args) {
        IntegerMinHeap minHeap = new IntegerMinHeap();
        int[] testValues = {45, 12, 85, 32, 89, 39, 69, 44, 42, 1, 68, 46};
        
        for (int val : testValues) {
            minHeap.add(val);
        }

        System.out.println("驗證移除結果：");
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.removeMin() + " ");
        }
        System.out.println();
    }
}
