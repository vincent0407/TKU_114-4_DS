import java.util.ArrayList;
import java.util.List;

class Order {
    String orderId;
    String customer;
    double amount; // 不得為負數
    String status; // NEW, PROCESSING, CANCELLED, COMPLETED

    public Order(String orderId, String customer, double amount, String status) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount 不得為負數");
        }
        this.orderId = orderId;
        this.customer = customer;
        this.amount = amount;
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("[ID: %s] 客戶: %-8s | 金額: $%-8.2f | 狀態: %s",
                orderId, customer, amount, status);
    }
}

public class OrderManagementBst {

    static class Node {
        Order order;
        Node left, right;
        Node(Order order) { this.order = order; }
    }

    private Node root;

    public boolean add(Order order) {
        if (find(order.orderId) != null) return false;
        root = addRec(root, order);
        return true;
    }

    private Node addRec(Node n, Order order) {
        if (n == null) return new Node(order);
        int cmp = order.orderId.compareTo(n.order.orderId);
        if (cmp < 0) n.left = addRec(n.left, order);
        else if (cmp > 0) n.right = addRec(n.right, order);
        return n;
    }

    public Order find(String orderId) { return findRec(root, orderId); }
    private Order findRec(Node n, String orderId) {
        if (n == null) return null;
        int cmp = orderId.compareTo(n.order.orderId);
        if (cmp < 0) return findRec(n.left, orderId);
        if (cmp > 0) return findRec(n.right, orderId);
        return n.order;
    }

    public boolean updateStatus(String orderId, String newStatus) {
        Order o = find(orderId);
        if (o != null) {
            o.status = newStatus;
            return true;
        }
        return false;
    }

    public boolean cancelOrder(String orderId) {
        return updateStatus(orderId, "CANCELLED");
    }

    public boolean remove(String orderId) {
        Order o = find(orderId);
        if (o == null || !"CANCELLED".equals(o.status)) return false; // 只有 CANCELLED 訂單可以 remove
        root = removeRec(root, orderId);
        return true;
    }

    private Node removeRec(Node n, String orderId) {
        if (n == null) return null;
        int cmp = orderId.compareTo(n.order.orderId);
        if (cmp < 0) n.left = removeRec(n.left, orderId);
        else if (cmp > 0) n.right = removeRec(n.right, orderId);
        else {
            if (n.left == null) return n.right;
            if (n.right == null) return n.left;
            Node min = n.right;
            while (min.left != null) min = min.left;
            n.order = min.order;
            n.right = removeRec(n.right, min.order.orderId);
        }
        return n;
    }

    public List<Order> idRangeReport(String startId, String endId) {
        List<Order> res = new ArrayList<>();
        rangeRec(root, startId, endId, res);
        return res;
    }

    private void rangeRec(Node n, String start, String end, List<Order> res) {
        if (n == null) return;
        if (n.order.orderId.compareTo(start) > 0) rangeRec(n.left, start, end, res);
        if (n.order.orderId.compareTo(start) >= 0 && n.order.orderId.compareTo(end) <= 0) res.add(n.order);
        if (n.order.orderId.compareTo(end) < 0) rangeRec(n.right, start, end, res);
    }

    public double totalAmount() { return totalAmountRec(root); }
    private double totalAmountRec(Node n) {
        if (n == null) return 0.0;
        return n.order.amount + totalAmountRec(n.left) + totalAmountRec(n.right);
    }

    public static void main(String[] args) {
        OrderManagementBst sys = new OrderManagementBst();
        sys.add(new Order("ORD-002", "Alice", 150.0, "NEW"));
        sys.add(new Order("ORD-001", "Bob", 300.5, "PROCESSING"));
        sys.add(new Order("ORD-003", "Charlie", 200.0, "NEW"));

        System.out.println("=== 訂單總金額 ===");
        System.out.printf("Total Amount: $%.2f%n", sys.totalAmount());

        System.out.println("\n=== 嘗試刪除未取消的訂單 ORD-001 ===");
        System.out.println("Remove Result: " + sys.remove("ORD-001"));

        System.out.println("\n=== 取消 ORD-001 後進行刪除 ===");
        sys.cancelOrder("ORD-001");
        System.out.println("Remove Result: " + sys.remove("ORD-001"));

        System.out.println("\n=== ID 範圍報表 [ORD-002 ~ ORD-003] ===");
        List<Order> list = sys.idRangeReport("ORD-002", "ORD-003");
        for (Order o : list) System.out.println(o);
    }
}
