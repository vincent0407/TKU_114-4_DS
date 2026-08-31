import java.util.ArrayList;
import java.util.List;

public class CollisionBucketReport {

    private int bucketCount;
    private List<List<Integer>> buckets;

    public CollisionBucketReport(int bucketCount) {
        if (bucketCount <= 0) {
            throw new IllegalArgumentException("Bucket 數量必須大於 0");
        }
        this.bucketCount = bucketCount;
        this.buckets = new ArrayList<>();
        for (int i = 0; i < bucketCount; i++) {
            this.buckets.add(new ArrayList<>());
        }
    }

    // Hash 函數：處理負數取模問題
    private int getBucketIndex(int key) {
        int index = key % bucketCount;
        if (index < 0) {
            index += bucketCount;
        }
        return index;
    }

    // 插入 key (處理重複值與邊界情況)
    public void insertAll(int[] keys) {
        if (keys == null || keys.length == 0) {
            return;
        }

        for (int key : keys) {
            int index = getBucketIndex(key);
            List<Integer> bucket = buckets.get(index);
            // 避免重複 key 重複加入同一 bucket
            if (!bucket.contains(key)) {
                bucket.add(key);
            }
        }
    }

    // 印出各 bucket 狀態與統計報告
    public void printReport() {
        int totalCollisions = 0;
        int maxChainLength = 0;

        System.out.println("=== Collision Bucket Report ===");
        for (int i = 0; i < bucketCount; i++) {
            List<Integer> bucket = buckets.get(i);
            int size = bucket.size();
            System.out.println("Bucket " + i + ": " + bucket);

            if (size > 1) {
                totalCollisions += (size - 1);
            }
            if (size > maxChainLength) {
                maxChainLength = size;
            }
        }

        System.out.println("-------------------------------");
        System.out.println("Total Collisions: " + totalCollisions);
        System.out.println("Max Chain Length: " + maxChainLength);
    }

    public static void main(String[] args) {
        // 測試案例包含負數 key、重複 key 與一般輸入
        int[] inputKeys = {15, -5, 25, 7, -13, 15, 35, 17, -3};
        int numBuckets = 5;

        CollisionBucketReport report = new CollisionBucketReport(numBuckets);
        report.insertAll(inputKeys);
        report.printReport();

        // 空輸入測試
        System.out.println("\n--- 空輸入測試 ---");
        CollisionBucketReport emptyReport = new CollisionBucketReport(3);
        emptyReport.insertAll(null);
        emptyReport.printReport();
    }
}
