// 檔案名稱：EnrollmentCleanup.java

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class EnrollmentCleanup {

    public static void main(String[] args) {
        // 建立包含重複、空白與 null 資料的清單
        List<String> rawList = new ArrayList<>();
        rawList.add("Alice");
        rawList.add("Bob");
        rawList.add(null);
        rawList.add("   ");
        rawList.add("Alice"); // 重複
        rawList.add("Charlie");
        rawList.add("");      // 空白
        rawList.add("Bob");   // 重複
        rawList.add(null);

        System.out.println("=== 1. 清理前的原始名單 ===");
        System.out.println(rawList);

        // 使用 Iterator 安全移除不合法資料 (null 或 Trim 後為空的字串)
        Iterator<String> iterator = rawList.iterator();
        while (iterator.hasNext()) {
            String name = iterator.next();
            if (name == null || name.trim().isEmpty()) {
                iterator.remove(); // 安全移除元素
            }
        }

        System.out.println("\n=== 2. 清理無效資料後的名單 ===");
        System.out.println(rawList);

        // 使用 Set 找出重複姓名
        Set<String> seenSet = new HashSet<>();
        Set<String> duplicateSet = new HashSet<>();

        for (String name : rawList) {
            // trim() 確保格式統一
            String cleanName = name.trim();
            // 如果 add 回傳 false，代表 seenSet 中已有此姓名 -> 為重複資料
            if (!seenSet.add(cleanName)) {
                duplicateSet.add(cleanName);
            }
        }

        System.out.println("\n=== 3. 重複報名者報告 ===");
        System.out.println("重複報名的姓名: " + duplicateSet);
    }
}
