import java.util.ArrayList;
import java.util.List;

public class BstOperationAudit {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    private TreeNode root;

    public boolean add(int val) {
        int initialSize = size();
        root = insertRec(root, val);
        boolean success = size() > initialSize;
        audit("ADD " + val, success);
        return success;
    }

    private TreeNode insertRec(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) root.left = insertRec(root.left, val);
        else if (val > root.val) root.right = insertRec(root.right, val);
        return root; // 重複值不新增
    }

    public boolean remove(int val) {
        int initialSize = size();
        root = deleteRec(root, val);
        boolean success = size() < initialSize;
        audit("REMOVE " + val, success);
        return success;
    }

    private TreeNode deleteRec(TreeNode root, int val) {
        if (root == null) return null;
        if (val < root.val) root.left = deleteRec(root.left, val);
        else if (val > root.val) root.right = deleteRec(root.right, val);
        else {
            // Case 1 & 2: 葉節點 或 只有一個子節點
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            // Case 3: 兩個子節點 (找右子樹最小值替代)
            root.val = minValue(root.right);
            root.right = deleteRec(root.right, root.val);
        }
        return root;
    }

    private int minValue(TreeNode root) {
        int minV = root.val;
        while (root.left != null) {
            minV = root.left.val;
            root = root.left;
        }
        return minV;
    }

    public List<Integer> inorder() {
        List<Integer> res = new ArrayList<>();
        inorderRec(root, res);
        return res;
    }

    private void inorderRec(TreeNode root, List<Integer> res) {
        if (root != null) {
            inorderRec(root.left, res);
            res.add(root.val);
            inorderRec(root.right, res);
        }
    }

    public int size() { return sizeRec(root); }
    private int sizeRec(TreeNode n) { return n == null ? 0 : 1 + sizeRec(n.left) + sizeRec(n.right); }

    public int height() { return heightRec(root); }
    private int heightRec(TreeNode n) { return n == null ? 0 : 1 + Math.max(heightRec(n.left), heightRec(n.right)); }

    public boolean isValid() { return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE); }
    private boolean isValidBST(TreeNode n, long min, long max) {
        if (n == null) return true;
        if (n.val <= min || n.val >= max) return false;
        return isValidBST(n.left, min, n.val) && isValidBST(n.right, n.val, max);
    }

    private void audit(String op, boolean result) {
        System.out.printf("Op: %-10s | Result: %-5b | Inorder: %-18s | Size: %d | Height: %d | Valid: %b%n",
                op, result, inorder().toString(), size(), height(), isValid());
    }

    public static void main(String[] args) {
        BstOperationAudit bst = new BstOperationAudit();
        
        // 新增與 Duplicate 測試
        bst.add(50);
        bst.add(30);
        bst.add(70);
        bst.add(20);
        bst.add(40);
        bst.add(60);
        bst.add(80);
        bst.add(50); // Duplicate 測試

        // Missing 刪除測試
        bst.remove(99); 

        // 三種 Delete Case 測試
        bst.remove(20); // Case 1: 刪除 Leaf
        bst.remove(30); // Case 2: 刪除單一子節點 (40)
        bst.remove(50); // Case 3: 刪除雙子節點 Root
    }
}
