import java.util.*;

class ServiceTicket {
    private String ticketId;
    private String customerName;

    public ServiceTicket(String ticketId, String customerName) {
        this.ticketId = ticketId;
        this.customerName = customerName;
    }

    public String getTicketId() { return ticketId; }

    @Override
    public String toString() {
        return "[" + ticketId + ": " + customerName + "]";
    }
}

public class ServiceCenterWorkflow {
    private Map<String, ServiceTicket> ticketMap = new HashMap<>();
    private Deque<ServiceTicket> waitingQueue = new ArrayDeque<>();
    private Deque<ServiceTicket> completedStack = new ArrayDeque<>();
    private Set<String> existingIds = new HashSet<>();

    // 建立服務單（防重複 ID）
    public boolean createTicket(String ticketId, String customerName) {
        if (existingIds.contains(ticketId)) {
            System.out.println("建立失敗：Ticket ID " + ticketId + " 已存在！");
            return false;
        }
        ServiceTicket ticket = new ServiceTicket(ticketId, customerName);
        ticketMap.put(ticketId, ticket);
        existingIds.add(ticketId);
        waitingQueue.addLast(ticket);
        System.out.println("成功建立票券: " + ticket);
        return true;
    }

    // 處理下一位服務
    public ServiceTicket processNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("處理失敗：等待隊列為空 (空 Queue)");
            return null;
        }
        ServiceTicket ticket = waitingQueue.pollFirst();
        completedStack.push(ticket);
        System.out.println("完成服務: " + ticket);
        return ticket;
    }

    // 取消等待中的票券（只作用於尚未處理的 ticket）
    public boolean cancelWaiting(String ticketId) {
        Iterator<ServiceTicket> iterator = waitingQueue.iterator();
        while (iterator.hasNext()) {
            ServiceTicket ticket = iterator.next();
            if (ticket.getTicketId().equals(ticketId)) {
                iterator.remove();
                System.out.println("取消等待成功: " + ticket);
                return true;
            }
        }
        System.out.println("取消失敗：在等待隊列中找不到票券 " + ticketId);
        return false;
    }

    // Undo 最後一次完成：將最後完成的 ticket 放回 waiting queue 前端
    public boolean undoLastCompletion() {
        if (completedStack.isEmpty()) {
            System.out.println("Undo 失敗：已完成 Stack 為空");
            return false;
        }
        ServiceTicket ticket = completedStack.pop();
        waitingQueue.addFirst(ticket);
        System.out.println("Undo 成功：將票券 " + ticket + " 放回等待隊列前端");
        return true;
    }

    // 依 ticket ID 查詢
    public ServiceTicket findById(String ticketId) {
        return ticketMap.get(ticketId);
    }

    // 印出摘要
    public void printSummary() {
        System.out.println("\n===== 服務中心狀態摘要 =====");
        System.out.println("等待佇列 (Queue): " + waitingQueue);
        System.out.println("完成歷程 (Stack): " + completedStack);
        System.out.println("歷史記錄總筆數: " + existingIds.size());
        System.out.println("===========================\n");
    }

    public static void main(String[] args) {
        ServiceCenterWorkflow sc = new ServiceCenterWorkflow();

        // 測試 1：空 Queue 處理
        sc.processNext();

        // 測試 2：建立票券與重複 ID 防範
        sc.createTicket("TK01", "Alice");
        sc.createTicket("TK02", "Bob");
        sc.createTicket("TK01", "Charlie"); // 重複 ID 測試

        // 測試 3：取消不存在的 ID
        sc.cancelWaiting("TK99");

        // 測試 4：取消尚在等待的票券
        sc.cancelWaiting("TK02");

        // 建立更多票券並處理
        sc.createTicket("TK03", "David");
        sc.createTicket("TK04", "Eve");
        sc.processNext(); // 完成 TK01
        sc.processNext(); // 完成 TK03

        sc.printSummary();

        // 測試 5：連續兩次 Undo
        System.out.println("--- 測試連續兩次 Undo ---");
        sc.undoLastCompletion();
        sc.undoLastCompletion();
        sc.undoLastCompletion(); // 測試第三次 (應顯示 Stack 為空)

        sc.printSummary();
    }
}
