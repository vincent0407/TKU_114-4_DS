import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class EmergencyTriageQueue {

    public static class Patient implements Comparable<Patient> {
        private String patientId;
        private int urgency;      // 危急程度：數字越大越優先
        private int arrivalOrder; // 到院順序：數字越小越早

        public Patient(String patientId, int urgency, int arrivalOrder) {
            this.patientId = patientId;
            this.urgency = urgency;
            this.arrivalOrder = arrivalOrder;
        }

        public String getPatientId() {
            return patientId;
        }

        public int getUrgency() {
            return urgency;
        }

        public int getArrivalOrder() {
            return arrivalOrder;
        }

        @Override
        public int compareTo(Patient other) {
            if (this.urgency != other.urgency) {
                return Integer.compare(other.urgency, this.urgency); // 急診等級高的先處理
            }
            return Integer.compare(this.arrivalOrder, other.arrivalOrder); // 等級相同時先到先處理
        }

        @Override
        public String toString() {
            return String.format("病患[%s] - 危急程度: %d, 到院順序: %d", patientId, urgency, arrivalOrder);
        }
    }

    private PriorityQueue<Patient> queue;
    private int autoArrivalCounter;

    public EmergencyTriageQueue() {
        this.queue = new PriorityQueue<>();
        this.autoArrivalCounter = 0;
    }

    // 報到 (自動遞增到院順序)
    public void register(String patientId, int urgency) {
        autoArrivalCounter++;
        Patient p = new Patient(patientId, urgency, autoArrivalCounter);
        queue.add(p);
        System.out.println("成功報到: " + p);
    }

    // 查看下一位
    public Patient peekNext() {
        if (queue.isEmpty()) {
            System.out.println("目前無候診病患。");
            return null;
        }
        return queue.peek();
    }

    // 叫號
    public Patient callNext() {
        if (queue.isEmpty()) {
            throw new NoSuchElementException("叫號失敗：急診佇列為空！");
        }
        Patient next = queue.poll();
        System.out.println("叫號通知: 請 " + next.getPatientId() + " 號病患至診間就診。");
        return next;
    }

    // 查詢目前人數
    public int getWaitingCount() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public static void main(String[] args) {
        EmergencyTriageQueue triage = new EmergencyTriageQueue();

        System.out.println("=== 急診報到測試 ===");
        triage.register("P001", 3);
        triage.register("P002", 5); // 最高危急
        triage.register("P003", 3); // 與 P001 同等級，但較晚到
        triage.register("P004", 1);

        System.out.println("\n目前候診人數: " + triage.getWaitingCount());
        System.out.println("下一位診察病患: " + triage.peekNext());

        System.out.println("\n=== 開始依序叫號 ===");
        while (!triage.isEmpty()) {
            triage.callNext();
        }

        System.out.println("\n=== 空佇列叫號測試 ===");
        try {
            triage.callNext();
        } catch (NoSuchElementException e) {
            System.out.println("捕捉例外處理: " + e.getMessage());
        }
    }
}
