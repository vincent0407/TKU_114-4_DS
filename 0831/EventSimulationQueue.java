import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class EventSimulationQueue {

    public static class Event implements Comparable<Event> {
        private String id;
        private long timestamp;  // 事件時間點
        private String type;     // 事件類型
        private int sequence;    // 同時間點下的執行順序

        public Event(String id, long timestamp, String type, int sequence) {
            this.id = id;
            this.timestamp = timestamp;
            this.type = type;
            this.sequence = sequence;
        }

        public String getId() {
            return id;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public String getType() {
            return type;
        }

        public int getSequence() {
            return sequence;
        }

        @Override
        public int compareTo(Event other) {
            if (this.timestamp != other.timestamp) {
                return Long.compare(this.timestamp, other.timestamp); // 時間早的先執行
            }
            return Integer.compare(this.sequence, other.sequence);   // 時間相同依 sequence 排序
        }

        @Override
        public String toString() {
            return String.format("[Time: %d | Seq: %d | ID: %s | Type: %s]", timestamp, sequence, id, type);
        }
    }

    private PriorityQueue<Event> eventQueue;
    private List<String> executionLogs;

    public EventSimulationQueue() {
        this.eventQueue = new PriorityQueue<>();
        this.executionLogs = new ArrayList<>();
    }

    // 新增事件
    public void addEvent(Event event) {
        eventQueue.add(event);
    }

    // 取消指定事件 (依 ID)
    public boolean cancelEvent(String eventId) {
        Event target = null;
        for (Event e : eventQueue) {
            if (e.getId().equals(eventId)) {
                target = e;
                break;
            }
        }
        if (target != null) {
            eventQueue.remove(target);
            executionLogs.add("取消事件成功: " + target);
            return true;
        }
        executionLogs.add("取消失敗：找不到 ID 為 " + eventId + " 的事件");
        return false;
    }

    // 執行模擬
    public void runSimulation() {
        executionLogs.add("--- 開始執行模擬 ---");
        while (!eventQueue.isEmpty()) {
            Event current = eventQueue.poll();
            String log = "執行事件 -> " + current;
            executionLogs.add(log);
        }
        executionLogs.add("--- 模擬結束 ---");
    }

    // 輸出完整執行紀錄
    public void printLogs() {
        for (String log : executionLogs) {
            System.out.println(log);
        }
    }

    public static void main(String[] args) {
        EventSimulationQueue simulator = new EventSimulationQueue();

        // 加入測試事件
        simulator.addEvent(new Event("E01", 1000, "START_SERVER", 1));
        simulator.addEvent(new Event("E02", 1000, "LOAD_CONFIG", 2));
        simulator.addEvent(new Event("E03", 500,  "INIT_DATABASE", 1));
        simulator.addEvent(new Event("E04", 1500, "USER_LOGIN", 1));
        simulator.addEvent(new Event("E05", 1500, "LOG_METRICS", 2));

        // 取消指定事件
        simulator.cancelEvent("E02");

        // 執行模擬與列印紀錄
        simulator.runSimulation();
        System.out.println();
        simulator.printLogs();
    }
}
