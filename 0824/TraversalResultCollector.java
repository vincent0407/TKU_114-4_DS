import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TraversalResultCollector {

    public static List<String> preorder(TreeNode root) {
        List<String> result = new ArrayList<>();
        preorderHelper(root, result);
        return result;
    }

    private static void preorderHelper(TreeNode node, List<String> result) {
        if (node == null) return;
        result.add(String.valueOf(node.val));
        preorderHelper(node.left, result);
        preorderHelper(node.right, result);
    }

    public static List<String> inorder(TreeNode root) {
        List<String> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private static void inorderHelper(TreeNode node, List<String> result) {
        if (node == null) return;
        inorderHelper(node.left, result);
        result.add(String.valueOf(node.val));
        inorderHelper(node.right, result);
    }

    public static List<String> postorder(TreeNode root) {
        List<String> result = new ArrayList<>();
        postorderHelper(root, result);
        return result;
    }

    private static void postorderHelper(TreeNode node, List<String> result) {
        if (node == null) return;
        postorderHelper(node.left, result);
        postorderHelper(node.right, result);
        result.add(String.valueOf(node.val));
    }

    public static List<String> levelOrder(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            result.add(String.valueOf(current.val));
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
        return result;
    }

    public static void printReport(String testName, TreeNode root) {
        System.out.println("=== " + testName + " ===");
        System.out.println("Preorder:   " + preorder(root));
        System.out.println("Inorder:    " + inorder(root));
        System.out.println("Postorder:  " + postorder(root));
        System.out.println("LevelOrder: " + levelOrder(root));
        System.out.println();
    }

    public static void main(String[] args) {
        // 1. Empty Tree
        printReport("Empty Tree", null);

        // 2. Single Node
        printReport("Single Node", new TreeNode('A'));

        // 3. Left-Skewed Tree
        TreeNode leftSkewed = new TreeNode('A');
        leftSkewed.left = new TreeNode('B');
        leftSkewed.left.left = new TreeNode('C');
        printReport("Left-Skewed Tree", leftSkewed);

        // 4. Complete Tree
        TreeNode complete = new TreeNode('A');
        complete.left = new TreeNode('B');
        complete.right = new TreeNode('C');
        complete.left.left = new TreeNode('D');
        complete.left.right = new TreeNode('E');
        printReport("Complete Tree", complete);
    }
}
