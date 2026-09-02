import java.util.*;

public class ServiceRequestSystem {
    public static class Request implements Comparable<Request> {
        String id;
        int priority; // 數字越大越優先

        public Request(String id, int priority) {
            this.id = id;
            this.priority = priority;
        }

        @Override
        public int compareTo(Request o) {
            return Integer.compare(o.priority, this.priority); // 降序
        }

        @Override
        public String toString() {
            return "[ID: " + id + ", Priority: " + priority + "]";
        }
    }

    private Map<String, Request> map = new HashMap<>();
    private PriorityQueue<Request> pq = new PriorityQueue<>();

    public void addRequest(String id, int priority) {
        Request req = new Request(id, priority);
        map.put(id, req);
        pq.offer(req);
    }

    public Request getNextRequest() {
        while (!pq.isEmpty()) {
            Request top = pq.poll();
            if (map.containsKey(top.id) && map.get(top.id) == top) {
                map.remove(top.id);
                return top;
            }
        }
        return null;
    }

    public boolean cancelRequest(String id) {
        if (map.containsKey(id)) {
            Request req = map.remove(id);
            pq.remove(req); // 保持兩份結構同步
            return true;
        }
        return false;
    }

    public Request queryById(String id) {
        return map.get(id);
    }

    public static void main(String[] args) {
        ServiceRequestSystem sys = new ServiceRequestSystem();
        sys.addRequest("R1", 2);
        sys.addRequest("R2", 5);
        sys.addRequest("R3", 1);

        System.out.println("--- 2. ServiceRequestSystem (測試) ---");
        System.out.println("取消 R2: " + sys.cancelRequest("R2"));
        System.out.println("取下一筆 (應為 R1): " + sys.getNextRequest());

        System.out.println("--- 2. ServiceRequestSystem (邊界案例) ---");
        System.out.println("查詢已被取走/取消的 R2: " + sys.queryById("R2"));
        System.out.println("取消不存在的 R99: " + sys.cancelRequest("R99"));
    }
}
