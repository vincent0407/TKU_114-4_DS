import java.util.*;

public class BstShapeExperiment {
    private static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    private Node root;
    private int comparisons = 0;

    public void insert(int val) { root = insertRec(root, val); }
    private Node insertRec(Node root, int val) {
        if (root == null) return new Node(val);
        if (val < root.val) root.left = insertRec(root.left, val);
        else if (val > root.val) root.right = insertRec(root.right, val);
        return root;
    }

    public int search(int val) {
        Node curr = root;
        int count = 0;
        while (curr != null) {
            count++;
            if (val == curr.val) break;
            curr = (val < curr.val) ? curr.left : curr.right;
        }
        return count;
    }

    public int getHeight() { return height(root); }
    private int height(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public static void main(String[] args) {
        int[] sorted = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        
        int[] random = sorted.clone();
        List<Integer> list = new ArrayList<>();
        for (int i : random) list.add(i);
        Collections.shuffle(list);

        int[] balanced = {8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15};

        runExperiment("遞增順序 (最差)", sorted);
        runExperiment("隨機順序", list.stream().mapToInt(i->i).toArray());
        runExperiment("平衡順序 (最佳)", balanced);
    }

    private static void runExperiment(String label, int[] arr) {
        BstShapeExperiment tree = new BstShapeExperiment();
        for (int x : arr) tree.insert(x);

        int totalComparisons = 0;
        for (int x : arr) {
            totalComparisons += tree.search(x);
        }

        System.out.println("=== " + label + " ===");
        System.out.println("Tree Height: " + tree.getHeight());
        System.out.println("Total Search Comparisons: " + totalComparisons);
    }
}
