public class BstDeleteCases {
    private Node root;

    public void insert(int val) {
        root = insertRec(root, val);
    }

    private Node insertRec(Node node, int val) {
        if (node == null) return new Node(val);
        if (val < node.val) node.left = insertRec(node.left, val);
        else if (val > node.val) node.right = insertRec(node.right, val);
        return node;
    }

    public void delete(int key) {
        root = deleteRec(root, key);
        System.out.print("After deleting " + key + " | Inorder: ");
        printInorder(root);
        System.out.println(" | Size: " + getSize(root) + " | Valid BST: " + isValidBST(root, null, null));
    }

    private Node deleteRec(Node root, int key) {
        if (root == null) return null;

        if (key < root.val) {
            root.left = deleteRec(root.left, key);
        } else if (key > root.val) {
            root.right = deleteRec(root.right, key);
        } else {
            // Case 1: Leaf node & Case 2: Single-child node
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;

            // Case 3: Two-child node
            root.val = minValue(root.right);
            root.right = deleteRec(root.right, root.val);
        }

        return root;
    }

    private int minValue(Node root) {
        int min = root.val;
        while (root.left != null) {
            min = root.left.val;
            root = root.left;
        }
        return min;
    }

    private int getSize(Node node) {
        if (node == null) return 0;
        return 1 + getSize(node.left) + getSize(node.right);
    }

    private boolean isValidBST(Node node, Integer min, Integer max) {
        if (node == null) return true;
        if ((min != null && node.val <= min) || (max != null && node.val >= max)) return false;

        return isValidBST(node.left, min, node.val) && isValidBST(node.right, node.val, max);
    }

    private void printInorder(Node node) {
        if (node != null) {
            printInorder(node.left);
            System.out.print(node.val + " ");
            printInorder(node.right);
        }
    }

    public static void main(String[] args) {
        BstDeleteCases bst = new BstDeleteCases();
        int[] keys = {50, 30, 70, 20, 40, 60, 80};
        for (int k : keys) bst.insert(k);

        bst.delete(20); // Case 1: Leaf node
        bst.delete(30); // Case 2: Single-child node (40 remains)
        bst.delete(50); // Case 3: Two-child node (Root)
    }
}