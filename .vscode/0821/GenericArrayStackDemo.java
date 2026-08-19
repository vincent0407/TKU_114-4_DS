import java.util.Arrays;

class ArrayStack<T> {
    private Object[] data;
    private int top;
    private int capacity;

    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        this.capacity = capacity;
        this.data = new Object[capacity]; // 不使用 Stack, Deque, List，採用原生陣列
        this.top = -1;
    }

    public void push(T element) {
        if (isFull()) {
            throw new RuntimeException("Stack 已滿，無法 push: " + element);
        }
        data[++top] = element;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack 為空，無法 pop");
        }
        T element = (T) data[top];
        data[top--] = null; // 清除引用
        return element;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack 為空，無法 peek");
        }
        return (T) data[top];
    }

    public int size() {
        return top + 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == capacity - 1;
    }
}

public class GenericArrayStackDemo {
    public static void main(String[] args) {
        // 測試 ArrayStack<String>
        System.out.println("=== 測試 ArrayStack<String> ===");
        ArrayStack<String> stringStack = new ArrayStack<>(3);
        stringStack.push("Java");
        stringStack.push("Python");
        stringStack.push("C++");
        System.out.println("Stack 是否已滿: " + stringStack.isFull());
        System.out.println("頂端元素 (peek): " + stringStack.peek());
        System.out.println("Pop 元素: " + stringStack.pop());
        System.out.println("當前大小: " + stringStack.size());

        // 測試 ArrayStack<Integer>
        System.out.println("\n=== 測試 ArrayStack<Integer> ===");
        ArrayStack<Integer> intStack = new ArrayStack<>(2);
        System.out.println("Stack 是否為空: " + intStack.isEmpty());
        intStack.push(100);
        intStack.push(200);
        System.out.println("Stack 是否已滿: " + intStack.isFull());
        System.out.println("Pop 元素: " + intStack.pop());
        System.out.println("Pop 元素: " + intStack.pop());
        System.out.println("Stack 是否為空: " + intStack.isEmpty());
    }
}
