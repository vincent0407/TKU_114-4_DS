import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderByLine {

    public static void printLevelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int level = 1;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            System.out.print("Level " + level + " (count: " + levelSize + "): ");

            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                System.out.print(current.val + " ");

                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }
            System.out.println();
            level++;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode('A');
        root.left = new TreeNode('B');
        root.right = new TreeNode('C');
        root.left.left = new TreeNode('D');
        root.left.right = new TreeNode('E');

        System.out.println("=== Level Order Output ===");
        printLevelOrder(root);

        System.out.println("\n=== Empty Tree Test ===");
        printLevelOrder(null);
    }
}
