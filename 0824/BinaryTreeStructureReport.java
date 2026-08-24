class TreeNode {
    char val;
    TreeNode left;
    TreeNode right;

    TreeNode(char val) {
        this.val = val;
    }
}

public class BinaryTreeStructureReport {

    public static int size(TreeNode root) {
        if (root == null) return 0;
        return 1 + size(root.left) + size(root.right);
    }

    public static int leafCount(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        return leafCount(root.left) + leafCount(root.right);
    }

    public static int height(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(height(root.left), height(root.right));
    }

    public static void printLeaves(TreeNode root) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            System.out.print(root.val + " ");
            return;
        }
        printLeaves(root.left);
        printLeaves(root.right);
    }

    public static void report(TreeNode root) {
        if (root == null) {
            System.out.println("Tree is Empty.");
            return;
        }
        System.out.println("Root: " + root.val);
        System.out.print("Leaves: ");
        printLeaves(root);
        System.out.println();
        System.out.println("Size: " + size(root));
        System.out.println("Leaf Count: " + leafCount(root));
        System.out.println("Height: " + height(root));
    }

    public static void main(String[] args) {
        // 建立 7 個 node 的樹
        TreeNode a = new TreeNode('A');
        TreeNode b = new TreeNode('B');
        TreeNode c = new TreeNode('C');
        TreeNode d = new TreeNode('D');
        TreeNode e = new TreeNode('E');
        TreeNode f = new TreeNode('F');
        TreeNode g = new TreeNode('G');

        a.left = b; a.right = c;
        b.left = d; b.right = e;
        c.left = f; c.right = g;

        System.out.println("=== 7-Node Tree ===");
        report(a);

        System.out.println("\n=== Empty Tree ===");
        report(null);

        System.out.println("\n=== Single-Node Tree ===");
        report(new TreeNode('X'));
    }
}
