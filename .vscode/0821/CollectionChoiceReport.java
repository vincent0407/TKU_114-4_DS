import java.util.*;

public class CollectionChoiceReport {
    public static void main(String[] args) {
        System.out.println("=== 課後作業四：集合選擇報告與實作 ===\n");

        // 需求 1：保留搜尋紀錄且允許重複
        System.out.println("需求 1：保留搜尋紀錄且允許重複");
        System.out.println("選擇結構：Interface: List | Implementation: ArrayList");
        List<String> searchHistory = new ArrayList<>();
        searchHistory.add("Java教程");
        searchHistory.add("Data Structure");
        searchHistory.add("Java教程"); // 允許重複
        System.out.println("操作結果: " + searchHistory + "\n");

        // 需求 2：保存不重複會員編號
        System.out.println("需求 2：保存不重複會員編號");
        System.out.println("選擇結構：Interface: Set | Implementation: HashSet");
        Set<String> memberIds = new HashSet<>();
        memberIds.add("M1001");
        memberIds.add("M1002");
        memberIds.add("M1001"); // 重複，不會被加入
        System.out.println("操作結果: " + memberIds + "\n");

        // 需求 3：以學號查詢成績
        System.out.println("需求 3：以學號查詢成績");
        System.out.println("選擇結構：Interface: Map | Implementation: HashMap");
        Map<String, Integer> studentGrades = new HashMap<>();
        studentGrades.put("S001", 95);
        studentGrades.put("S002", 88);
        System.out.println("操作結果 (查詢 S001 成績): " + studentGrades.get("S001") + "\n");

        // 需求 4：依照到達順序處理列印工作 (FIFO)
        System.out.println("需求 4：依照到達順序處理列印工作");
        System.out.println("選擇結構：Interface: Queue / Deque | Implementation: ArrayDeque");
        Deque<String> printQueue = new ArrayDeque<>();
        printQueue.addLast("Doc1.pdf");
        printQueue.addLast("Doc2.docx");
        System.out.println("處理列印: " + printQueue.pollFirst());
        System.out.println("剩餘佇列: " + printQueue + "\n");

        // 需求 5：復原最近操作 (LIFO)
        System.out.println("需求 5：復原最近操作");
        System.out.println("選擇結構：Interface: Deque | Implementation: ArrayDeque");
        Deque<String> undoStack = new ArrayDeque<>();
        undoStack.push("Action 1");
        undoStack.push("Action 2");
        System.out.println("復原動作: " + undoStack.pop());
        System.out.println("剩餘 Stack: " + undoStack);
    }
}
