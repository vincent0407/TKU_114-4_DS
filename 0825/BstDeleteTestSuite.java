public class BstDeleteTestSuite {
    private class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    private Node root;

    public void insert(int val) { root = insertRec(root, val); }
    private Node insertRec(Node root, int val) {
        if (root == null) return new Node(val);
        if (val < root.val) root.left = insertRec(root.left, val);
        else if (val > root.val) root.right = insertRec(root.right, val);
        return root;
    }

    public void delete(int val) { root = deleteRec(root, val); }
    private Node deleteRec(Node root, int val) {
        if (root == null) return null;
        if (val < root.val) root.left = deleteRec(root.left, val);
        else if (val > root.val) root.right = deleteRec(root.right, val);
        else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            Node min = root.right;
            while (min.left != null) min = min.left;
            root.val = min.val;
            root.right = deleteRec(root.right, min.val);
        }
        return root;
    }

    public static void main(String[] args) {
        BstDeleteTestSuite bst = new BstDeleteTestSuite();

        // 1. Test empty
        bst.delete(10);

        // 2. Test missing
        bst.insert(50);
        bst.delete(99);

        // 3. Test single root
        bst.delete(50); // empty now

        // 4. Test root with one child
        bst.insert(50);
        bst.insert(30);
        bst.delete(50); // root becomes 30

        // 5. Test root with two children
        bst.insert(20);
        bst.insert(40);
        bst.delete(30); // deletes node with 2 children

        // 6. 測試連續刪除到 empty
        bst.delete(20);
        bst.delete(40);
        System.out.println("Test completed successfully, root is null: " + (bst.root == null));
    }
}
