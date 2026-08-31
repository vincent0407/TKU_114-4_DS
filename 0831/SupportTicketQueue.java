import java.util.PriorityQueue;

public class SupportTicketQueue {
    static class Ticket implements Comparable<Ticket> {
        String id;
        int severity;
        int createdOrder;

        public Ticket(String id, int severity, int createdOrder) {
            this.id = id;
            this.severity = severity;
            this.createdOrder = createdOrder;
        }

        @Override
        public int compareTo(Ticket other) {
            if (this.severity != other.severity) {
                return Integer.compare(other.severity, this.severity); // severity 數字越大越優先
            }
            return Integer.compare(this.createdOrder, other.createdOrder); // createdOrder 越小越早
        }

        @Override
        public String toString() {
            return id + "|" + severity + "|" + createdOrder;
        }
    }

    public static void main(String[] args) {
        PriorityQueue<Ticket> pq = new PriorityQueue<>();

        // 測試範例資料
        pq.add(new Ticket("T001", 3, 1));
        pq.add(new Ticket("T002", 5, 2));
        pq.add(new Ticket("T003", 5, 3));
        pq.add(new Ticket("T004", 2, 4));

        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
    }
}
