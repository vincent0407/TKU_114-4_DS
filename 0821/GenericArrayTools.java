// 檔案名稱：GenericArrayTools.java

import java.util.Objects;

public class GenericArrayTools {

    /**
     * 計算 target 在陣列中出現的次數
     * 支援 null 值的比對
     */
    public static <T> int countMatches(T[] data, T target) {
        if (data == null || data.length == 0) {
            return 0;
        }

        int count = 0;
        for (T item : data) {
            if (Objects.equals(item, target)) {
                count++;
            }
        }
        return count;
    }

    /**
     * 回傳陣列的最後一個元素
     * 若陣列為 null 或長度為 0，回傳 null
     */
    public static <T> T last(T[] data) {
        if (data == null || data.length == 0) {
            return null;
        }
        return data[data.length - 1];
    }

    /**
     * 交換陣列中指定位置的兩個元素
     * 若陣列為 null 或索引不合法，拋出 IllegalArgumentException 或報錯處理
     */
    public static <T> void swap(T[] data, int first, int second) {
        // 邊界檢查
        if (data == null) {
            throw new IllegalArgumentException("陣列不能為 null");
        }
        if (first < 0 || first >= data.length || second < 0 || second >= data.length) {
            throw new IndexOutOfBoundsException("不合法的 index: " + first + " 或 " + second);
        }

        // 交換元素
        T temp = data[first];
        data[first] = data[second];
        data[second] = temp;
    }

    // 測試的主程式
    public static void main(String[] args) {
        // 測試用資料
        String[] colors = {"Red", "Green", "Blue", "Green", null};

        // 1. 測試 countMatches
        System.out.println("Green 出現次數: " + countMatches(colors, "Green")); // 2
        System.out.println("null 出現次數: " + countMatches(colors, null));    // 1
        System.out.println("空陣列匹配測試: " + countMatches(new String[]{}, "A")); // 0

        // 2. 測試 last
        System.out.println("最後一個元素: " + last(colors)); // null
        Integer[] nums = {10, 20, 30};
        System.out.println("數字陣列最後一個元素: " + last(nums)); // 30
        System.out.println("null 陣列測試 last: " + last(null)); // null

        // 3. 測試 swap
        System.out.println("\n交換前 nums[0]: " + nums[0] + ", nums[2]: " + nums[2]);
        swap(nums, 0, 2);
        System.out.println("交換後 nums[0]: " + nums[0] + ", nums[2]: " + nums[2]);

        // 4. 測試不合法 index 的例外處理
        try {
            swap(nums, 0, 5); // 索引超出範圍
        } catch (IndexOutOfBoundsException e) {
            System.out.println("成功捕捉例外狀況: " + e.getMessage());
        }
    }
}
