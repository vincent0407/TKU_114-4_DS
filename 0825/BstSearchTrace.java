class Node {
    int val;
    Node left, right;
    Node(int val) { this.val = val; }
}

public class BstSearchTrace {
    private Node root;

    public void insert(int val) {
        root = insertRec(root, val);
    }

    private Node insertRec(Node root, int val) {
        if (root == null) return new Node(val);
        if (val < root.val) root.left = insertRec(root.left, val);
        else if (val > root.val) root.right = insertRec(root.right, val);
        return root;
    }

    public void search(int target) {
        System.out.println("=== Search Target: " + target + " ===");
        Node curr = root;
        int count = 0;

    while (curr != null) {
            count++;
            if (target == curr.val) {
                System.out.println("Count " + count + ": current = " + curr.val + ", Direction = FOUND");
                return;
            } else if (target < curr.val) {
                System.out.println("Count " + count + ": current = " + curr.val + ", Direction = LEFT");
                curr = curr.left;
            } else {
                System.out.println("Count " + count + ": current = " + curr.val + ", Direction = RIGHT");
                curr = curr.right;
            }
        }
        System.out.println("Count " + (count + 1) + ": NOT FOUND (Reached null)");
    }

    public static void main(String[] args) {
        BstSearchTrace bst = new BstSearchTrace();
        // 建立測試樹
        int[] keys = {50, 30, 70, 20, 40, 60, 80};
        for (int k : keys) bst.insert(k);

        bst.search(50); // Root
        bst.search(20); // Leaf
        bst.search(30); // Internal node
        bst.search(90); // Missing value
    }
}