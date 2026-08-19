// 檔案名稱：WordIndexSystem.java

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordIndexSystem {

    public static void main(String[] args) {
        // 內建句子陣列 (包含大小寫、標點符號如句點與逗號)
        String[] sentences = {
            "Java is a popular programming language.",
            "Java is powerful, versatile, and widely used.",
            "Learning Java programming is fun and useful!"
        };

        Map<String, Integer> wordCountMap = new HashMap<>();
        Set<String> uniqueWords = new HashSet<>();

        // 讀取並處理每個句子
        for (String sentence : sentences) {
            // 1. 轉為小寫並移除句點、逗號與驚嘆號等標點符號
            String cleanedSentence = sentence.toLowerCase().replaceAll("[,.!]", "");

            // 2. 依據空白字元切割成單字
            String[] words = cleanedSentence.split("\\s+");

            for (String word : words) {
                if (word.isEmpty()) continue;

                // 保存到 Set (不重複單字)
                uniqueWords.add(word);

                // 統計到 Map
                wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
            }
        }

        // --- 輸出結果 ---
        System.out.println("=== 所有不重複單字 (Set) ===");
        System.out.println(uniqueWords);

        System.out.println("\n=== 所有單字出現次數統計 (Map) ===");
        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + " 次");
        }

        System.out.println("\n=== 出現至少兩次 (>= 2) 的單字 ===");
        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
            if (entry.getValue() >= 2) {
                System.out.println(entry.getKey() + " (" + entry.getValue() + " 次)");
            }
        }
    }
}
