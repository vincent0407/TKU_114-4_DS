import java.util.*;

class DeliveryItem {
    private String id;
    private String destination;

    public DeliveryItem(String id, String destination) {
        this.id = id;
        this.destination = destination;
    }

    public String getId() { return id; }

    @Override
    public String toString() {
        return "[" + id + "] -> " + destination;
    }
}

public class DeliveryWorkflowSystem {
    private Map<String, DeliveryItem> map = new HashMap<>();
    private Deque<DeliveryItem> waitingQueue = new ArrayDeque<>();
    private Deque<DeliveryItem> completedStack = new ArrayDeque<>();

    // 新增包裹（重複 id 不得加入）
    public boolean addPackage(String id, String destination) {
        if (map.containsKey(id)) {
            System.out.println("新增失敗：包裹 ID " + id + " 已存在！");
            return false;
        }
        DeliveryItem item = new DeliveryItem(id, destination);
        map.put(id, item);
        waitingQueue.addLast(item);
        System.out.println("新增包裹成功: " + item);
        return true;
    }

    // 處理下一個配送
    public void processNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("處理失敗：沒有等待配送的包裹");
            return;
        }
        DeliveryItem item = waitingQueue.pollFirst();
        completedStack.push(item);
        System.out.println("已完成配送: " + item);
    }

    // Undo 復原最後一個已完成的配送，將其放回等待隊列前端
    public void undo() {
        if (completedStack.isEmpty()) {
            System.out.println("Undo 失敗：沒有已完成的配送紀錄");
            return;
        }
        DeliveryItem item = completedStack.pop();
        waitingQueue.addFirst(item);
        System.out.println("Undo 成功：包裹 " + item.getId() + " 已放回待配送隊列前端");
    }

    // 依配送編號查詢
    public void query(String id) {
        DeliveryItem item = map.get(id);
        if (item == null) {
            System.out.println("查詢結果：找不到包裹 ID " + id);
        } else {
            System.out.println("查詢結果: " + item);
        }
    }

    // 統計資訊
    public void printStats() {
        System.out.println("\n--- 物流系統統計報告 ---");
        System.out.println("總登記數: " + map.size());
        System.out.println("待配送數: " + waitingQueue.size() + " " + waitingQueue);
        System.out.println("已完成數: " + completedStack.size() + " " + completedStack);
        System.out.println("----------------------\n");
    }

    public static void main(String[] args) {
        DeliveryWorkflowSystem sys = new DeliveryWorkflowSystem();

        sys.addPackage("D001", "台北市");
        sys.addPackage("D002", "新北市");
        sys.addPackage("D001", "台中市"); // 重複 id 測試

        sys.processNext();
        sys.printStats();

        sys.undo();
        sys.printStats();

        sys.query("D002");
        sys.query("D999"); // 不存在的 id 測試
    }
}
