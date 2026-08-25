import java.util.*;

public class TreeShapeComparison {

    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    private Node root;
    private long totalComparisons = 0;

    public void add(int val) {
        root = addRec(root, val);
    }

    private Node addRec(Node n, int val) {
        if (n == null) return new Node(val);
        if (val < n.val) n.left = addRec(n.left, val);
        else if (val > n.val) n.right = addRec(n.right, val);
        return n;
    }

    public long searchWithCount(int target) {
        long[] count = new long[1];
        searchRec(root, target, count);
        return count[0];
    }

    private boolean searchRec(Node n, int target, long[] count) {
        if (n == null) return false;
        count[0]++;
        if (n.val == target) return true;
        return target < n.val ? searchRec(n.left, target, count) : searchRec(n.right, target, count);
    }

    public int height() { return heightRec(root); }
    private int heightRec(Node n) { return n == null ? 0 : 1 + Math.max(heightRec(n.left), heightRec(n.right)); }

    public static void buildBalanced(List<Integer> sorted, TreeShapeComparison tree, int start, int end) {
        if (start > end) return;
        int mid = (start + end) / 2;
        tree.add(sorted.get(mid));
        buildBalanced(sorted, tree, start, mid - 1);
        buildBalanced(sorted, tree, mid + 1, end);
    }

    public static void main(String[] args) {
        List<Integer> ascKeys = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        List<Integer> descKeys = new ArrayList<>(ascKeys);
        Collections.reverse(descKeys);

        // 1. 升冪建立
        TreeShapeComparison ascTree = new TreeShapeComparison();
        for (int k : ascKeys) ascTree.add(k);

        // 2. 降冪建立
        TreeShapeComparison descTree = new TreeShapeComparison();
        for (int k : descKeys) descTree.add(k);

        // 3. 接近平衡建立
        TreeShapeComparison balTree = new TreeShapeComparison();
        buildBalanced(ascKeys, balTree, 0, ascKeys.size() - 1);

        // 統計比較次數
        TreeShapeComparison[] trees = {ascTree, descTree, balTree};
        String[] names = {"升冪 (Ascending)", "降冪 (Descending)", "接近平衡 (Balanced)"};

        System.out.printf("%-18s | %-6s | %-20s | %-20s%n", "樹結構類型", "Height", "15 個 Key 總比較次數", "Missing Key(99) 比較次數");
        System.out.println("-----------------------------------------------------------------------------");

        for (int i = 0; i < 3; i++) {
            long totalFoundComp = 0;
            for (int k : ascKeys) {
                totalFoundComp += trees[i].searchWithCount(k);
            }
            long missingComp = trees[i].searchWithCount(99);

            System.out.printf("%-18s | %-6d | %-20d | %-20d%n",
                    names[i], trees[i].height(), totalFoundComp, missingComp);
        }
    }
}
