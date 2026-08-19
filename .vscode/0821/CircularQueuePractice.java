import java.util.Arrays;

class CircularQueue<T> {
    private Object[] array;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        this.array = new Object[capacity];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    public void enqueue(T item) {
        if (size == capacity) {
            System.out.println("Queue 已滿，無法加入: " + item);
            return;
        }
        array[rear] = item;
        rear = (rear + 1) % capacity; // 使用 modulo 循環 index
        size++;
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (size == 0) {
            System.out.println("Queue 為空，無法取出");
            return null;
        }
        T item = (T) array[front];
        array[front] = null; // 清除引用
        front = (front + 1) % capacity; // 不搬移元素，使用 modulo 循環
        size--;
        return item;
    }

    public void printState(String action) {
        System.out.printf("[%s] -> Array: %s, front: %d, rear: %d, size: %d\n",
                action, Arrays.toString(array), front, rear, size);
    }

    // 依 FIFO 順序取出所有元素
    public void printAllInFIFO() {
        System.out.print("依 FIFO 順序印出所有元素: ");
        int current = front;
        for (int i = 0; i < size; i++) {
            System.out.print(array[current] + " ");
            current = (current + 1) % capacity;
        }
        System.out.println();
    }
}

public class CircularQueuePractice {
    public static void main(String[] args) {
        // 以容量 4 建立 CircularQueue<String>
        CircularQueue<String> cq = new CircularQueue<>(4);

        System.out.println("=== 開始執行連續操作 ===");
        
        cq.enqueue("A"); cq.printState("enqueue A");
        cq.enqueue("B"); cq.printState("enqueue B");
        cq.enqueue("C"); cq.printState("enqueue C");
        
        cq.dequeue(); cq.printState("dequeue");
        cq.dequeue(); cq.printState("dequeue");
        
        cq.enqueue("D"); cq.printState("enqueue D");
        cq.enqueue("E"); cq.printState("enqueue E");
        cq.enqueue("F"); cq.printState("enqueue F");
        
        cq.dequeue(); cq.printState("dequeue");
        
        cq.enqueue("G"); cq.printState("enqueue G");

        System.out.println("\n=== 最終結果 ===");
        cq.printAllInFIFO();
    }
}
