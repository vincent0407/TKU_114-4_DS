import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxHeapInsertTrace {
    private List<Integer> heap = new ArrayList<>();

    public void add(int val) {
        heap.add(val);
        siftUp(heap.size() - 1);
        System.out.println(snapshot());
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.get(index) > heap.get(parent)) {
                int temp = heap.get(index);
                heap.set(index, heap.get(parent));
                heap.set(parent, temp);
                index = parent;
            } else {
                break;
            }
        }
    }

    public int peekMax() {
        if (heap.isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        return heap.get(0);
    }

    public List<Integer> snapshot() {
        return new ArrayList<>(heap);
    }

    public static void main(String[] args) {
        MaxHeapInsertTrace maxHeap = new MaxHeapInsertTrace();
        int[] data = {25, 40, 10, 50, 30, 50};
        
        for (int val : data) {
            maxHeap.add(val);
        }

        System.out.println("Root: " + maxHeap.peekMax());
    }
}
