import java.util.*;

class Patient {
    private String id;
    private String name;

    public Patient(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return name + " (" + id + ")";
    }
}

public class ClinicQueueSystem {
    private Deque<Patient> waitingQueue = new ArrayDeque<>();
    private List<Patient> completedList = new ArrayList<>();

    // 1. 一般掛號 (FIFO)
    public void register(Patient p) {
        waitingQueue.addLast(p);
        System.out.println("成功掛號: " + p);
    }

    // 2. 取消指定病歷號
    public boolean cancel(String id) {
        Iterator<Patient> iterator = waitingQueue.iterator();
        while (iterator.hasNext()) {
            Patient p = iterator.next();
            if (p.getId().equals(id)) {
                iterator.remove();
                System.out.println("成功取消掛號: " + p);
                return true;
            }
        }
        System.out.println("取消失敗：找不到病歷號 " + id);
        return false;
    }

    // 3. 叫號
    public Patient callNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("叫號失敗：當前沒有等待中的病患");
            return null;
        }
        Patient p = waitingQueue.pollFirst();
        completedList.add(p);
        System.out.println("請診：叫號 " + p);
        return p;
    }

    // 4. 查看下一位
    public Patient peekNext() {
        Patient p = waitingQueue.peekFirst();
        if (p == null) {
            System.out.println("下一位：無");
        } else {
            System.out.println("下一位預計看診: " + p);
        }
        return p;
    }

    // 5. 當日完成清單
    public void printCompletedList() {
        System.out.println("當日已看診完成清單: " + completedList);
    }

    public static void main(String[] args) {
        ClinicQueueSystem clinic = new ClinicQueueSystem();

        clinic.register(new Patient("P001", "張三"));
        clinic.register(new Patient("P002", "李四"));
        clinic.register(new Patient("P003", "王五"));

        clinic.peekNext();
        clinic.cancel("P002"); // 取消中間的病患
        
        clinic.callNext(); // 應叫號 P001
        clinic.callNext(); // 應叫號 P003
        clinic.callNext(); // 測試空隊列叫號

        clinic.printCompletedList();
    }
}
