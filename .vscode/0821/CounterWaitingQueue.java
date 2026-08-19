import java.util.ArrayDeque;
import java.util.Deque;

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

public class CounterWaitingQueue {
    private Deque<Customer> queue = new ArrayDeque<>();

    // 加入佇列 (Enqueue)
    public void addCustomer(Customer customer) {
        queue.addLast(customer);
        System.out.println("顧客 " + customer + " 加入等候隊列");
    }

    // 查看下一位顧客
    public Customer peekNext() {
        if (queue.isEmpty()) {
            System.out.println("當前無人等候");
            return null;
        }
        return queue.peekFirst();
    }

    // 服務下一位顧客 (Dequeue)
    public Customer serveNext() {
        if (queue.isEmpty()) {
            System.out.println("佇列為空，無法提供服務");
            return null;
        }
        Customer served = queue.pollFirst();
        System.out.println("正在服務顧客: " + served);
        return served;
    }

    // 顯示等候人數與隊列狀態
    public void displayStatus() {
        System.out.println("當前等候人數: " + queue.size() + " | 隊列內容: " + queue);
    }

    public static void main(String[] args) {
        CounterWaitingQueue counter = new CounterWaitingQueue();

        // 測試空隊列處理
        counter.serveNext();
        counter.peekNext();

        // 加入顧客
        counter.addCustomer(new Customer("Alice"));
        counter.addCustomer(new Customer("Bob"));
        counter.addCustomer(new Customer("Charlie"));

        counter.displayStatus();

        // 查看與服務
        System.out.println("下一位準備服務: " + counter.peekNext());
        counter.serveNext();

        counter.displayStatus();
    }
}
