import java.util.Arrays;

public class RecursiveCallReport {

    public static int sum(int[] data, int index) {
        // 邊界條件：空陣列或索引超出範圍
        if (data == null || index >= data.length) {
            System.out.printf("index: %d, current value: N/A, recursive result: 0, return value: 0%n", index);
            return 0;
        }

        int currentValue = data[index];
        int recursiveResult = sum(data, index + 1);
        int total = currentValue + recursiveResult;

        System.out.printf("index: %d, current value: %d, recursive result: %d, return value: %d%n",
                index, currentValue, recursiveResult, total);

        return total;
    }

    public static void main(String[] args) {
        System.out.println("=== 測試 1：一般陣列 ===");
        sum(new int[]{10, 20, 30}, 0);

        System.out.println("\n=== 測試 2：單一元素陣列 ===");
        sum(new int[]{42}, 0);

        System.out.println("\n=== 測試 3：Empty Array ===");
        sum(new int[]{}, 0);
    }
}
