package midterm_exam;

import java.util.ArrayList;
import java.util.List;

public class Q05_BoundedBox<T extends Comparable<T>> {

    private final int capacity;
    private final List<T> elements;

    // 建構子：1. capacity 小於 1 時拋出 IllegalArgumentException
    public Q05_BoundedBox(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity must be at least 1");
        }
        this.capacity = capacity;
        this.elements = new ArrayList<>();
    }

    // 2. add() 拒絕 null 及超過容量的資料，回傳 false 且不修改內容
    public boolean add(T value) {
        if (value == null || isFull()) {
            return false;
        }
        elements.add(value);
        return true;
    }

    public int size() {
        return elements.size();
    }

    public boolean isFull() {
        return elements.size() >= capacity;
    }

    // 3. minimum() 在 empty box 回傳 null，其他情況使用 compareTo()
    public T minimum() {
        if (elements.isEmpty()) {
            return null;
        }
        T min = elements.get(0);
        for (T item : elements) {
            if (item.compareTo(min) < 0) {
                min = item;
            }
        }
        return min;
    }

    // 3. maximum() 在 empty box 回傳 null，其他情況使用 compareTo()
    public T maximum() {
        if (elements.isEmpty()) {
            return null;
        }
        T max = elements.get(0);
        for (T item : elements) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }
        return max;
    }

    // 4. countGreaterThan() 計算嚴格大於 threshold 的數量；threshold 為 null 時回傳 0
    public int countGreaterThan(T threshold) {
        if (threshold == null) {
            return 0;
        }
        int count = 0;
        for (T item : elements) {
            if (item.compareTo(threshold) > 0) {
                count++;
            }
        }
        return count;
    }

    // 5. snapshot() 保留加入順序，caller 修改回傳 List 時不能影響 box
    public List<T> snapshot() {
        return new ArrayList<>(elements);
    }

    // 測試用 main 方法
    public static void main(String[] args) {
        Q05_BoundedBox<Integer> box = new Q05_BoundedBox<>(3);
        System.out.println(box.add(40));
        System.out.println(box.add(10));
        System.out.println(box.add(30));
        System.out.println(box.add(20)); // 超過容量，應該回傳 false
        System.out.println(box.minimum());
        System.out.println(box.maximum());
        System.out.println(box.countGreaterThan(25));
        System.out.println(box.snapshot());
    }
}
