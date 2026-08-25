class CountNode {
    int key;
    int count;
    CountNode left, right;

    CountNode(int key) {
        this.key = key;
        this.count = 1;
    }
}

public class BstDuplicateCounter {
    private CountNode root;

    public void insert(int key) {
        root = insertRec(root, key);
    }

    private CountNode insertRec(CountNode node, int key) {
        if (node == null) return new CountNode(key);

        if (key == node.key) {
            node.count++;
        } else if (key < node.key) {
            node.left = insertRec(node.left, key);
        } else {
            node.right = insertRec(node.right, key);
        }
        return node;
    }

    public void inorder() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(CountNode node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.print(node.key + "(" + node.count + ") ");
            inorderRec(node.right);
        }
    }

    public static void main(String[] args) {
        BstDuplicateCounter bst = new BstDuplicateCounter();
        int[] data = {10, 20, 10, 30, 20, 10, 5};
        for (int val : data) bst.insert(val);

        System.out.print("Inorder Output: ");
        bst.inorder();
    }
}