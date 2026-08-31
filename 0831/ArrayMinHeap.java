import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayMinHeap {
    private int[] heap;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayMinHeap() {
        this.heap = new int[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public void add(int val) {
        if (size == heap.length) {
            resize();
        }
        heap[size] = val;
        siftUp(size);
        size++;
    }

    public int remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        int minVal = heap[0];
        heap[0] = heap[size - 1];
        size--;
        siftDown(0);
        return minVal;
    }

    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return heap[0];
    }

    public int[] snapshot() {
        return Arrays.copyOf(heap, size);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize() {
        int[] newHeap = new int[heap.length * 2];
        System.arraycopy(heap, 0, newHeap, 0, heap.length);
        heap = newHeap;
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap[index] < heap[parent]) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    private void siftDown(int index) {
        while (index * 2 + 1 < size) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            int smallest = left;

            if (right < size && heap[right] < heap[left]) {
                smallest = right;
            }

            if (heap[index] > heap[smallest]) {
                swap(index, smallest);
                index = smallest;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public static void main(String[] args) {
        ArrayMinHeap minHeap = new ArrayMinHeap();
        int[] data = {45, 12, 85, 32, 89, 39, 69, 44, 42, 1, 68, 46, 23, 7, 91, 15, 60, 5, 88, 50, 3};
        
        for (int val : data) {
            minHeap.add(val);
        }

        System.out.println("當前 Heap 陣列 (Snapshot): " + Arrays.toString(minHeap.snapshot()));
        System.out.println("Heap 元素總數: " + minHeap.size());
        
        System.out.print("依序移除印出: ");
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.remove() + " ");
        }
        System.out.println();
    }
}
