import java.util.ArrayList;
import java.util.List;

public class BstRangeStatistics {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // 利用 BST 方向剪枝 (Pruning) 收集範圍內的值
    public static List<Integer> valuesBetween(TreeNode root, int low, int high) {
        List<Integer> result = new ArrayList<>();
        if (low > high) return result;
        valuesBetweenRec(root, low, high, result);
        return result;
    }

    private static void valuesBetweenRec(TreeNode node, int low, int high, List<Integer> res) {
        if (node == null) return;
        if (node.val > low) valuesBetweenRec(node.left, low, high, res);
        if (node.val >= low && node.val <= high) res.add(node.val);
        if (node.val < high) valuesBetweenRec(node.right, low, high, res);
    }

    // 利用 BST 剪枝計算數量
    public static int countBetween(TreeNode root, int low, int high) {
        if (root == null || low > high) return 0;
        if (root.val < low) return countBetween(root.right, low, high);
        if (root.val > high) return countBetween(root.left, low, high);
        return 1 + countBetween(root.left, low, high) + countBetween(root.right, low, high);
    }

    // 利用 BST 剪枝計算總和
    public static int sumBetween(TreeNode root, int low, int high) {
        if (root == null || low > high) return 0;
        if (root.val < low) return sumBetween(root.right, low, high);
        if (root.val > high) return sumBetween(root.left, low, high);
        return root.val + sumBetween(root.left, low, high) + sumBetween(root.right, low, high);
    }

    public static void testRange(TreeNode root, int low, int high) {
        System.out.printf("Range [%d, %d]:%n", low, high);
        System.out.println("  Values: " + valuesBetween(root, low, high));
        System.out.println("  Count : " + countBetween(root, low, high));
        System.out.println("  Sum   : " + sumBetween(root, low, high));
    }

    public static void main(String[] args) {
        /* 建立 BST:
                 20
                /  \
               10   30
              /  \
             5   15
        */
        TreeNode root = new TreeNode(20);
        root.left = new TreeNode(10);
        root.right = new TreeNode(30);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(15);

        System.out.println("=== 測試 1：一般範圍 [10, 25] ===");
        testRange(root, 10, 25);

        System.out.println("\n=== 測試 2：空範圍（無符合元素） [35, 50] ===");
        testRange(root, 35, 50);

        System.out.println("\n=== 測試 3：無效範圍 (low > high) [30, 10] ===");
        testRange(root, 30, 10);
    }
}
