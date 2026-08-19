class TaskNode {
    String id;
    String name;
    TaskNode next;

    public TaskNode(String id, String name) {
        this.id = id;
        this.name = name;
        this.next = null;
    }

    @Override
    public String toString() {
        return "[" + id + ": " + name + "]";
    }
}

class TaskLinkedList {
    private TaskNode head;
    private int size;

    public TaskLinkedList() {
        this.head = null;
        this.size = 0;
    }

    // 檢查 ID 是否重複
    public boolean findById(String id) {
        TaskNode current = head;
        while (current != null) {
            if (current.id.equals(id)) return true;
            current = current.next;
        }
        return false;
    }

    // 新增至頭部
    public boolean addFirst(TaskNode task) {
        if (findById(task.id)) {
            System.out.println("新增失敗：重複的 ID " + task.id);
            return false;
        }
        task.next = head;
        head = task;
        size++;
        return true;
    }

    // 新增至尾部
    public boolean addLast(TaskNode task) {
        if (findById(task.id)) {
            System.out.println("新增失敗：重複的 ID " + task.id);
            return false;
        }
        if (head == null) {
            head = task;
        } else {
            TaskNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = task;
        }
        size++;
        return true;
    }

    // 在指定的 existingId 後面插入
    public boolean insertAfter(String existingId, TaskNode task) {
        if (findById(task.id)) {
            System.out.println("插入失敗：新增的 Task ID " + task.id + " 已存在！");
            return false;
        }
        TaskNode current = head;
        while (current != null) {
            if (current.id.equals(existingId)) {
                task.next = current.next;
                current.next = task;
                size++;
                return true;
            }
            current = current.next;
        }
        System.out.println("插入失敗：找不到指定的 ID " + existingId);
        return false;
    }

    // 依 ID 刪除節點（需涵蓋刪除 head, middle, tail, 與找不到的情況）
    public boolean removeById(String id) {
        if (head == null) {
            System.out.println("刪除失敗：串列為空");
            return false;
        }

        // 刪除 head
        if (head.id.equals(id)) {
            head = head.next;
            size--;
            System.out.println("成功刪除 Head 節點 ID: " + id);
            return true;
        }

        // 刪除 middle 或 tail
        TaskNode current = head;
        while (current.next != null) {
            if (current.next.id.equals(id)) {
                current.next = current.next.next;
                size--;
                System.out.println("成功刪除節點 ID: " + id);
                return true;
            }
            current = current.next;
        }

        System.out.println("刪除失敗：找不到 ID " + id);
        return false;
    }

    public int size() { return size; }

    public void printAll() {
        System.out.print("鏈結串列內容 (Size " + size + "): ");
        TaskNode current = head;
        while (current != null) {
            System.out.print(current + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}

public class LinkedTaskListSystem {
    public static void main(String[] args) {
        TaskLinkedList list = new TaskLinkedList();

        // 1. 測試空 list 刪除
        list.removeById("T001");

        // 建立資料
        list.addLast(new TaskNode("T001", "Task 1"));
        list.addLast(new TaskNode("T002", "Task 2"));
        list.addLast(new TaskNode("T003", "Task 3"));
        list.addFirst(new TaskNode("T000", "Task 0"));
        list.printAll();

        // 2. 測試重複 ID 阻擋
        list.addLast(new TaskNode("T001", "Duplicate Task"));

        // 3. 測試 insertAfter
        list.insertAfter("T002", new TaskNode("T002.5", "Inserted Task"));
        list.printAll();

        // 4. 測試刪除 Head
        list.removeById("T000");
        list.printAll();

        // 5. 測試刪除 Middle
        list.removeById("T002.5");
        list.printAll();

        // 6. 測試刪除 Tail
        list.removeById("T003");
        list.printAll();

        // 7. 測試找不到 ID 刪除
        list.removeById("T999");
    }
}
