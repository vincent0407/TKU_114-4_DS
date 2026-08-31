import java.util.LinkedList;

public class IntegerStringHashTable {

    private static class Node {
        int key;
        String value;

        Node(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Node>[] buckets;
    private int capacity;
    private int size;

    @SuppressWarnings("unchecked")
    public IntegerStringHashTable(int capacity) {
        this.capacity = capacity;
        this.buckets = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
        this.size = 0;
    }

    public IntegerStringHashTable() {
        this(10);
    }

    private int hash(int key) {
        int index = key % capacity;
        return index < 0 ? index + capacity : index;
    }

    public void put(int key, String value) {
        int index = hash(key);
        LinkedList<Node> bucket = buckets[index];

        for (Node node : bucket) {
            if (node.key == key) {
                node.value = value; // 相同 key 更新 value，size 不增加
                return;
            }
        }

        bucket.add(new Node(key, value));
        size++;
    }

    public String get(int key) {
        int index = hash(key);
        LinkedList<Node> bucket = buckets[index];

        for (Node node : bucket) {
            if (node.key == key) {
                return node.value;
            }
        }
        return null;
    }

    public boolean containsKey(int key) {
        return get(key) != null;
    }

    public boolean remove(int key) {
        int index = hash(key);
        LinkedList<Node> bucket = buckets[index];

        for (Node node : bucket) {
            if (node.key == key) {
                bucket.remove(node);
                size--;
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void bucketReport() {
        System.out.println("=== Bucket Report ===");
        for (int i = 0; i < capacity; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("Bucket ").append(i).append(": ");
            for (Node node : buckets[i]) {
                sb.append("[").append(node.key).append(" -> ").append(node.value).append("] ");
            }
            System.out.println(sb.toString());
        }
        System.out.println("Total Size: " + size);
    }

    public static void main(String[] args) {
        IntegerStringHashTable map = new IntegerStringHashTable(5);

        map.put(1, "Alice");
        map.put(6, "Bob");     // Hash 碰撞 (1 % 5 == 6 % 5)
        map.put(11, "Charlie");// Hash 碰撞
        map.put(1, "Alice_Updated"); // key 重複更新測試

        System.out.println("Key 1 查詢結果: " + map.get(1));
        System.out.println("包含 Key 6? " + map.containsKey(6));
        System.out.println("移除 Key 6: " + map.remove(6));

        map.bucketReport();
    }
}
