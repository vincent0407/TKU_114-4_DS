public class BstRangeReport {
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

    public Integer findMin() {
        if (root == null) return null;
        Node curr = root;
        while (curr.left != null) curr = curr.left;
        return curr.val;
    }

    public Integer findMax() {
        if (root == null) return null;
        Node curr = root;
        while (curr.right != null) curr = curr.right;
        return curr.val;
    }

    public void printRange(int low, int high) {
        if (low > high) {
            int temp = low;
            low = high;
            high = temp;
        }
        System.out.print("Range [" + low + ", " + high + "]: ");
        printRangeRec(root, low, high);
        System.out.println();
    }

    private void printRangeRec(Node node, int low, int high) {
        if (node == null) return;
        if (low < node.val) printRangeRec(node.left, low, high);
        if (low <= node.val && node.val <= high) System.out.print(node.val + " ");
        if (high > node.val) printRangeRec(node.right, low, high);
    }

    public static void main(String[] args) {
        BstRangeReport bst = new BstRangeReport();
        int[] keys = {40, 20, 60, 10, 30, 50, 70};
        for (int k : keys) bst.insert(k);

        System.out.println("Min: " + bst.findMin());
        System.out.println("Max: " + bst.findMax());

        bst.printRange(20, 50);
        bst.printRange(50, 20); // 處理 low > high 的狀況
    }
}