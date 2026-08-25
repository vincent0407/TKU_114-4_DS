import java.util.*;

class Order {
    int orderId;
    double amount;
    public Order(int orderId, double amount) {
        this.orderId = orderId;
        this.amount = amount;
    }
}

public class OrderBstSystem {
    private class Node {
        Order order;
        Node left, right;
        Node(Order order) { this.order = order; }
    }

    private Node root;

    public void add(Order order) { root = addRec(root, order); }
    private Node addRec(Node root, Order order) {
        if (root == null) return new Node(order);
        if (order.orderId < root.order.orderId) root.left = addRec(root.left, order);
        else if (order.orderId > root.order.orderId) root.right = addRec(root.right, order);
        return root;
    }

    public Order find(int orderId) {
        Node curr = root;
        while (curr != null) {
            if (orderId == curr.order.orderId) return curr.order;
            curr = (orderId < curr.order.orderId) ? curr.left : curr.right;
        }
        return null;
    }

    public void cancel(int orderId) { root = cancelRec(root, orderId); }
    private Node cancelRec(Node root, int orderId) {
        if (root == null) return null;
        if (orderId < root.order.orderId) root.left = cancelRec(root.left, orderId);
        else if (orderId > root.order.orderId) root.right = cancelRec(root.right, orderId);
        else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            Node min = root.right;
            while (min.left != null) min = min.left;
            root.order = min.order;
            root.right = cancelRec(root.right, min.order.orderId);
        }
        return root;
    }

    public boolean updateAmount(int orderId, double newAmount) {
        Order o = find(orderId);
        if (o == null) return false;
        o.amount = newAmount;
        return true;
    }

    public void rangeReport(int minId, int maxId) {
        rangeRec(root, minId, maxId);
    }
    private void rangeRec(Node node, int min, int max) {
        if (node == null) return;
        if (node.order.orderId > min) rangeRec(node.left, min, max);
        if (node.order.orderId >= min && node.order.orderId <= max) {
            System.out.println("ID: " + node.order.orderId + ", Amount: " + node.order.amount);
        }
        if (node.order.orderId < max) rangeRec(node.right, min, max);
    }

    public void summary() {
        int[] count = new int[1];
        double[] totalSum = new double[1];
        summaryRec(root, count, totalSum);
        System.out.println("Total Orders: " + count[0]);
        System.out.println("Total Amount: " + totalSum[0]);
        System.out.println("Average Amount: " + (count[0] == 0 ? 0 : totalSum[0] / count[0]));
    }

    private void summaryRec(Node node, int[] count, double[] sum) {
        if (node == null) return;
        summaryRec(node.left, count, sum);
        count[0]++;
        sum[0] += node.order.amount;
        summaryRec(node.right, count, sum);
    }
}
