import java.util.*;

public class DataStructureDecisionReport {
    public static class Requirement {
        int id;
        String scenario;

        public Requirement(int id, String scenario) {
            this.id = id;
            this.scenario = scenario;
        }
    }

    public static class Recommendation {
        String dataStructure;
        String reason;
        String bigO;

        public Recommendation(String ds, String reason, String bigO) {
            this.dataStructure = ds;
            this.reason = reason;
            this.bigO = bigO;
        }

        @Override
        public String toString() {
            return "DS: " + dataStructure + " | Big-O: " + bigO + " | Reason: " + reason;
        }
    }

    public static Map<Integer, Recommendation> evaluate12Requirements(List<Requirement> reqs) {
        Map<Integer, Recommendation> report = new LinkedHashMap<>();
        if (reqs == null) return report;

        for (Requirement req : reqs) {
            switch (req.id) {
                case 1: report.put(1, new Recommendation("HashMap", "快速依據Key查詢", "O(1)")); break;
                case 2: report.put(2, new Recommendation("PriorityQueue", "快速取得最優先/最小值", "O(log N)")); break;
                case 3: report.put(3, new Recommendation("ArrayList", "頻繁隨機存取索引", "O(1)")); break;
                case 4: report.put(4, new Recommendation("LinkedList", "頻繁在兩端插入/刪除", "O(1)")); break;
                case 5: report.put(5, new Recommendation("Stack", "後進先出 (LIFO) 狀態追蹤", "O(1)")); break;
                case 6: report.put(6, new Recommendation("Queue", "先進先出 (FIFO) 排隊處理", "O(1)")); break;
                case 7: report.put(7, new Recommendation("TreeSet (BST)", "維護元素有序性與範圍搜尋", "O(log N)")); break;
                case 8: report.put(8, new Recommendation("HashSet", "快速去除重複元素", "O(1)")); break;
                case 9: report.put(9, new Recommendation("Adjacency List (Graph)", "稀疏圖的邊與鄰居儲存", "O(V + E)")); break;
                case 10: report.put(10, new Recommendation("Adjacency Matrix (Graph)", "稠密圖且需快速檢查兩點是否相連", "O(1) Check")); break;
                case 11: report.put(11, new Recommendation("Trie", "字串前綴匹配與自動補全", "O(L) [L為長度]")); break;
                case 12: report.put(12, new Recommendation("Disjoint Set (Union-Find)", "動態連通性與動態群組合併", "O(α(N))")); break;
                default: report.put(req.id, new Recommendation("Unknown", "未知需求", "N/A")); break;
            }
        }
        return report;
    }

    public static void main(String[] args) {
        List<Requirement> reqs = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            reqs.add(new Requirement(i, "Scenario " + i));
        }

        System.out.println("--- 6. DataStructureDecisionReport (12組需求決議) ---");
        Map<Integer, Recommendation> result = evaluate12Requirements(reqs);
        result.forEach((k, v) -> System.out.println("需求 " + k + ": " + v));

        System.out.println("\n--- 6. DataStructureDecisionReport (邊界案例: 空清單) ---");
        System.out.println(evaluate12Requirements(null));
    }
}
