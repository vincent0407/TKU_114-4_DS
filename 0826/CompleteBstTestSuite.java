import java.util.ArrayList;
import java.util.List;

public class CompleteBstTestSuite {

    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    private Node root;
    private static int totalTests = 0;
    private static int passedTests = 0;

    private static void check(String description, boolean condition) {
        totalTests++;
        if (condition) {
            passedTests++;
            System.out.printf("[PASS] %s%n", description);
        } else {
            System.out.printf("[FAIL] %s%n", description);
        }
    }

    public boolean add(int val) {
        if (find(val)) return false;
        root = addRec(root, val);
        return true;
    }

    private Node addRec(Node n, int val) {
        if (n == null) return new Node(val);
        if (val < n.val) n.left = addRec(n.left, val);
        else if (val > n.val) n.right = addRec(n.right, val);
        return n;
    }

    public boolean find(int val) { return findRec(root, val); }
    private boolean findRec(Node n, int val) {
        if (n == null) return false;
        if (val == n.val) return true;
        return val < n.val ? findRec(n.left, val) : findRec(n.right, val);
    }

    public boolean remove(int val) {
        if (!find(val)) return false;
        root = removeRec(root, val);
        return true;
    }

    private Node removeRec(Node n, int val) {
        if (n == null) return null;
        if (val < n.val) n.left = removeRec(n.left, val);
        else if (val > n.val) n.right = removeRec(n.right, val);
        else {
            if (n.left == null) return n.right;
            if (n.right == null) return n.left;
            Node min = n.right;
            while (min.left != null) min = min.left;
            n.val = min.val;
            n.right = removeRec(n.right, min.val);
        }
        return n;
    }

    public List<Integer> rangeQuery(int low, int high) {
        List<Integer> res = new ArrayList<>();
        rangeRec(root, low, high, res);
        return res;
    }

    private void rangeRec(Node n, int low, int high, List<Integer> res) {
        if (n == null) return;
        if (n.val > low) rangeRec(n.left, low, high, res);
        if (n.val >= low && n.val <= high) res.add(n.val);
        if (n.val < high) rangeRec(n.right, low, high, res);
    }

    public boolean isValidBST() { return validate(root, Long.MIN_VALUE, Long.MAX_VALUE); }
    private boolean validate(Node n, long min, long max) {
        if (n == null) return true;
        if (n.val <= min || n.val >= max) return false;
        return validate(n.left, min, n.val) && validate(n.right, n.val, max);
    }

    public static void main(String[] args) {
        CompleteBstTestSuite bst = new CompleteBstTestSuite();

        // 1. Empty & Basic Invariant
        check("Empty Tree - find 10 is false", !bst.find(10));
        check("Empty Tree - remove 10 is false", !bst.remove(10));
        check("Empty Tree - rangeQuery is empty", bst.rangeQuery(1, 100).isEmpty());
        check("Empty Tree - isValidBST is true", bst.isValidBST());

        // 2. Root Insertion & Duplicate
        check("Add root 50", bst.add(50));
        check("Duplicate 50 fails", !bst.add(50));
        check("Find root 50", bst.find(50));
        check("Tree with root isValidBST", bst.isValidBST());

        // 3. Building Structure
        check("Add 30", bst.add(30));
        check("Add 70", bst.add(70));
        check("Add 20 (Leaf)", bst.add(20));
        check("Add 40", bst.add(40));
        check("Add 60", bst.add(60));
        check("Add 80", bst.add(80));
        check("Add 10 (One child chain node)", bst.add(10));

        // 4. Missing Keys & Range
        check("Find missing 99 is false", !bst.find(99));
        check("Remove missing 99 fails", !bst.remove(99));
        check("Range [20, 60] count is 5", bst.rangeQuery(20, 60).size() == 5);
        check("Range [100, 200] is empty", bst.rangeQuery(100, 200).isEmpty());

        // 5. Delete Operations (Leaf, One Child, Two Children)
        check("Remove Leaf 10", bst.remove(10));
        check("Find 10 after remove is false", !bst.find(10));
        check("Remove One-Child node 20", bst.remove(20));
        check("Find 20 after remove is false", !bst.find(20));
        check("Remove Two-Children node 30", bst.remove(30));
        check("Find 30 after remove is false", !bst.find(30));
        check("Remove Root 50", bst.remove(50));

        // 6. Invariant Integrity Check
        check("Final tree isValidBST invariant maintained", bst.isValidBST());

        System.out.println("=================================");
        System.out.printf("Total Assertions: %d, Passed: %d%n", totalTests, passedTests);
    }
}
