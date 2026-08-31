import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StudentIdHashAnalysis {

    public static class AnalysisResult {
        int bucketCount;
        int totalCollisions;
        int maxChainLength;
        double avgChainLength;

        public AnalysisResult(int bucketCount, int totalCollisions, int maxChainLength, double avgChainLength) {
            this.bucketCount = bucketCount;
            this.totalCollisions = totalCollisions;
            this.maxChainLength = maxChainLength;
            this.avgChainLength = avgChainLength;
        }

        public void printSummary() {
            System.out.println("Bucket Count: " + bucketCount);
            System.out.println("  - Total Collisions: " + totalCollisions);
            System.out.println("  - Max Chain Length: " + maxChainLength);
            System.out.printf("  - Avg Chain Length (非空桶): %.2f\n", avgChainLength);
        }
    }

    public static AnalysisResult analyze(List<String> studentIds, int bucketCount) {
        @SuppressWarnings("unchecked")
        LinkedList<String>[] buckets = new LinkedList[bucketCount];
        for (int i = 0; i < bucketCount; i++) {
            buckets[i] = new LinkedList<>();
        }

        for (String id : studentIds) {
            int index = Math.abs(id.hashCode()) % bucketCount;
            buckets[index].add(id);
        }

        int totalCollisions = 0;
        int maxChainLength = 0;
        int nonEmtpyBuckets = 0;
        int totalElementsInChains = 0;

        for (LinkedList<String> bucket : buckets) {
            int size = bucket.size();
            if (size > 0) {
                nonEmtpyBuckets++;
                totalElementsInChains += size;
            }
            if (size > 1) {
                totalCollisions += (size - 1);
            }
            if (size > maxChainLength) {
                maxChainLength = size;
            }
        }

        double avgChainLength = nonEmtpyBuckets == 0 ? 0 : (double) totalElementsInChains / nonEmtpyBuckets;
        return new AnalysisResult(bucketCount, totalCollisions, maxChainLength, avgChainLength);
    }

    public static void main(String[] args) {
        // 模擬學生學號資料
        List<String> studentIds = new ArrayList<>();
        for (int i = 41300000; i < 41300100; i++) {
            studentIds.add(String.valueOf(i));
        }

        int bucketCount1 = 10;
        int bucketCount2 = 31; // 通常質數效果較佳

        System.out.println("=== 學號 Collision 分析比較 ===");
        AnalysisResult res1 = analyze(studentIds, bucketCount1);
        AnalysisResult res2 = analyze(studentIds, bucketCount2);

        res1.printSummary();
        System.out.println("--------------------------------");
        res2.printSummary();
    }
}
