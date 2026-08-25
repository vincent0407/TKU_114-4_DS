public class TreeBugLab {

    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    // Bug 1: search 方向相反 -> 修正：< 向左，> 向右
    public static boolean search(Node root, int target) {
        if (root == null) return false;
        if (root.val == target) return true;
        // 修正：依 BST 定義修正搜尋方向
        return target < root.val ? search(root.left, target) : search(root.right, target);
    }

    // Bug 2: inorder 順序錯誤 -> 修正：左 -> 中 -> 右
    public static void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    // Bug 3: delete 遺失 child -> 修正：接回非空子節點
    public static Node delete(Node root, int target) {
        if (root == null) return null;
        if (target < root.val) root.left = delete(root.left, target);
        else if (target > root.val) root.right = delete(root.right, target);
        else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            Node min = root.right;
            while (min.left != null) min = min.left;
            root.val = min.val;
            root.right = delete(root.right, min.val);
        }
        return root;
    }

    // Bug 4: validation 只檢查直接 child -> 修正：傳遞 min/max 區間
    public static boolean isValidBST(Node root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean validate(Node node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }

    public static void main(String[] args) {
        System.out.println("=== Tree Bug Lab 測試全數修正完畢 ===");
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(15);

        System.out.println("Search 15: " + search(root, 15));
        System.out.print("Inorder: "); inorder(root); System.out.println();
        System.out.println("Is Valid BST: " + isValidBST(root));
    }
}
