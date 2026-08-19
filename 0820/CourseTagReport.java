// 檔案名稱：CourseTagReport.java

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CourseTagReport {

    public static void main(String[] args) {
        // 模擬輸入一組可能重複的課程標籤
        String[] rawTags = {"Java", "Python", "Java", "Data Structure", "Algorithm", "Python", "Java"};

        // 1. List<String> 保存原始順序
        List<String> tagList = new ArrayList<>();
        for (String tag : rawTags) {
            tagList.add(tag);
        }

        // 2. Set<String> 保存不重複標籤
        Set<String> tagSet = new HashSet<>(tagList);

        // 3. Map<String, Integer> 統計次數
        Map<String, Integer> tagCountMap = new HashMap<>();
        for (String tag : tagList) {
            // 若 key 不存在則給預設值 0，再加 1
            tagCountMap.put(tag, tagCountMap.getOrDefault(tag, 0) + 1);
        }

        // --- 輸出結果 ---
        System.out.println("=== 1. List (保存原始順序) ===");
        System.out.println(tagList);

        System.out.println("\n=== 2. Set (保存不重複標籤) ===");
        System.out.println(tagSet);

        System.out.println("\n=== 3. Map (統計次數) ===");
        for (Map.Entry<String, Integer> entry : tagCountMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + " 次");
        }

        // --- 三種資料結構用途說明 ---
        System.out.println("\n================ 各自用途說明 ================");
        System.out.println("1. List (ArrayList):");
        System.out.println("   - 特性：允許重複元素，維護插入的「先後順序」。");
        System.out.println("   - 用途：適合用於紀錄歷史軌跡、需要保留完整輸入順序或依索引 (index) 存取資料的場景。");
        System.out.println("\n2. Set (HashSet):");
        System.out.println("   - 特性：不允許重複元素，自動過濾重複資料（不保證順序）。");
        System.out.println("   - 用途：適合用於快速去重、檢查元素是否存在（Contains 檢查效率高）。");
        System.out.println("\n3. Map (HashMap):");
        System.out.println("   - 特性：以「鍵值對 (Key-Value)」形式儲存，Key 唯一不重複。");
        System.out.println("   - 用途：適合用於建立對應關係、頻率統計、快速鍵值查詢（如帳號對應使用者資料）。");
    }
}
