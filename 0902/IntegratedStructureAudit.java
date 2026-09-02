import java.util.*;

public class IntegratedStructureAudit {
    public static class AuditReport {
        public String scenario;
        public String selectedStructure;
        public boolean isReasonable;
        public String diagnosisMessage;

        public AuditReport(String scenario, String selectedStructure, boolean isReasonable, String diagnosisMessage) {
            this.scenario = scenario;
            this.selectedStructure = selectedStructure;
            this.isReasonable = isReasonable;
            this.diagnosisMessage = diagnosisMessage;
        }

        @Override
        public String toString() {
            return String.format("[%s] 選擇: %-12s | 合理: %-5b | 診斷: %s", scenario, selectedStructure, isReasonable, diagnosisMessage);
        }
    }

    public static AuditReport auditScenario(String scenario, String chosenDS) {
        if (scenario == null || chosenDS == null) {
            return new AuditReport("Empty Scenario", "None", false, "情境或資料結構不可為空！");
        }

        switch (scenario.toLowerCase()) {
            case "history_undo":
                boolean undoOk = chosenDS.equalsIgnoreCase("Stack");
                return new AuditReport(scenario, chosenDS, undoOk, undoOk ? "結構正確，符合 LIFO 需求。" : "不合理！建議改用 Stack。");

            case "priority_task":
                boolean pqOk = chosenDS.equalsIgnoreCase("Heap") || chosenDS.equalsIgnoreCase("PriorityQueue");
                return new AuditReport(scenario, chosenDS, pqOk, pqOk ? "結構正確，符合動態極值處理。" : "不合理！建議改用 Heap / PriorityQueue。");

            case "prefix_search":
                boolean trieOk = chosenDS.equalsIgnoreCase("Trie") || chosenDS.equalsIgnoreCase("BST");
                return new AuditReport(scenario, chosenDS, trieOk, trieOk ? "結構適合樹狀前綴搜尋。" : "不合理！建議改用 Trie。");

            default:
                return new AuditReport(scenario, chosenDS, false, "未知情境，無法評估。");
        }
    }

    public static void main(String[] args) {
        System.out.println("--- 4. IntegratedStructureAudit (測試情境) ---");
        System.out.println(auditScenario("history_undo", "Stack"));
        System.out.println(auditScenario("history_undo", "Queue"));
        System.out.println(auditScenario("priority_task", "Heap"));

        System.out.println("\n--- 4. IntegratedStructureAudit (邊界案例) ---");
        System.out.println(auditScenario(null, "List"));
    }
}
