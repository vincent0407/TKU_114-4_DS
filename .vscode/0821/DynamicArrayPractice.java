import java.util.Arrays;

class DynamicArray<T> {
    private Object[] elementData;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public DynamicArray() {
        this.elementData = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public DynamicArray(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
        this.elementData = new Object[initialCapacity];
        this.size = 0;
    }

    // 檢查擴容：容量滿時擴充為兩倍
    private void ensureCapacity() {
        if (size >= elementData.length) {
            int newCapacity = elementData.length == 0 ? DEFAULT_CAPACITY : elementData.length * 2;
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    // 邊界檢查
    private void checkIndexForAccess(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    public void add(T value) {
        ensureCapacity();
        elementData[size++] = value;
    }

    public void add(int index, T value) {
        checkIndexForAdd(index);
        ensureCapacity();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndexForAccess(index);
        return (T) elementData[index];
    }

    @SuppressWarnings("unchecked")
    public T set(int index, T value) {
        checkIndexForAccess(index);
        T oldValue = (T) elementData[index];
        elementData[index] = value;
        return oldValue;
    }

    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkIndexForAccess(index);
        T oldValue = (T) elementData[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        elementData[--size] = null; // 移除後最後一個無效表格設為 null
        return oldValue;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return elementData.length;
    }
}

public class DynamicArrayPractice {
    public static void main(String[] args) {
        // 1. 使用 String 測試
        System.out.println("=== 測試 DynamicArray<String> ===");
        DynamicArray<String> strArray = new DynamicArray<>(2);
        strArray.add("A");
        strArray.add("B");
        System.out.println("容量擴充測試 (原容量2 -> 新增第3個元素):");
        strArray.add("C"); // 觸發自動擴容為 4
        System.out.println("Size: " + strArray.size() + ", Capacity: " + strArray.capacity());

        strArray.add(1, "Inserted"); // 指定位置插入
        System.out.println("索引 1 插入後: " + strArray.get(1));

        // 2. 使用 Integer 測試
        System.out.println("\n=== 測試 DynamicArray<Integer> ===");
        DynamicArray<Integer> intArray = new DynamicArray<>(5);
        intArray.add(10);
        intArray.add(20);
        intArray.add(30);

        System.out.println("刪除索引 1 (原數值 20): " + intArray.remove(1));
        System.out.println("當前 Size: " + intArray.size());

        // 3. 測試邊界條件與例外處理
        System.out.println("\n=== 邊界與例外測試 ===");
        try {
            System.out.println("嘗試存取 index -1:");
            intArray.get(-1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("捕獲例外: " + e.getMessage());
        }

        try {
            System.out.println("嘗試存取 index >= size:");
            intArray.get(intArray.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("捕獲例外: " + e.getMessage());
        }

        try {
            System.out.println("嘗試在空結構/越界刪除:");
            DynamicArray<String> emptyArray = new DynamicArray<>();
            emptyArray.remove(0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("捕獲例外: " + e.getMessage());
        }
    }
}
