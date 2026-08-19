// 檔案名稱：WildcardNumberTools.java

import java.util.ArrayList;
import java.util.List;

public class WildcardNumberTools {

    /**
     * 計算平均值
     * 可同時接收 List<Integer>、List<Double> 等任何繼承自 Number 的 List
     * 空 list 時回傳 0.0
     */
    public static double average(List<? extends Number> values) {
        if (values == null || values.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;
        for (Number num : values) {
            sum += num.doubleValue();
        }
        return sum / values.size();
    }

    /**
     * 尋找最大值
     * 可同時接收 List<Integer>、List<Double> 等任何繼承自 Number 的 List
     * 空 list 時回傳 Double.NaN
     */
    public static double maximum(List<? extends Number> values) {
        if (values == null || values.isEmpty()) {
            return Double.NaN;
        }

        double max = Double.NEGATIVE_INFINITY;
        for (Number num : values) {
            if (num.doubleValue() > max) {
                max = num.doubleValue();
            }
        }
        return max;
    }

    /**
     * 將 [start, end] 範圍內的整數寫入 target 中
     * start > end 時不加入任何資料
     */
    public static void addRange(List<? super Integer> target, int start, int end) {
        if (target == null || start > end) {
            return;
        }

        for (int i = start; i <= end; i++) {
            target.add(i); // 可安全寫入 Integer
        }
    }

    // 測試主程式
    public static void main(String[] args) {
        // 1. 測試 average 與 maximum
        List<Integer> intList = List.of(10, 20, 30, 40);
        List<Double> doubleList = List.of(1.5, 2.5, 3.5);
        List<Number> emptyList = new ArrayList<>();

        System.out.println("=== 測試 average ===");
        System.out.println("Integer List 平均: " + average(intList));       // 25.0
        System.out.println("Double List 平均: " + average(doubleList));   // 2.5
        System.out.println("空 List 平均: " + average(emptyList));         // 0.0

        System.out.println("\n=== 測試 maximum ===");
        System.out.println("Integer List 最大值: " + maximum(intList));     // 40.0
        System.out.println("Double List 最大值: " + maximum(doubleList)); // 3.5
        System.out.println("空 List 最大值: " + maximum(emptyList));       // NaN

        System.out.println("\n=== 測試 addRange ===");
        // 可以寫入 List<Integer> 或 List<Number> 或 List<Object>
        List<Number> numberList = new ArrayList<>();
        
        // 正常加入 1 到 5
        addRange(numberList, 1, 5);
        System.out.println("加入 1~5 後的結果: " + numberList); // [1, 2, 3, 4, 5]

        // start > end 的狀況（不加入任何資料）
        addRange(numberList, 10, 5);
        System.out.println("嘗試加入 10~5 (start > end) 後的結果: " + numberList); // 保持不變
    }
}
