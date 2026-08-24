public class BinaryTreeStatistics {

    static class IntNode {
        int val;
        IntNode left;
        IntNode right;

        IntNode(int val) {
            this.val = val;
        }
    }

    public static int size(IntNode root) {
        if (root == null) return 0;
        return 1 + size(root.left) + size(root.right);
    }

    public static int sum(IntNode root) {
        if (root == null) return 0;
        return root.val + sum(root.left) + sum(root.right);
    }

    public static int maximum(IntNode root) {
        if (root == null) {
            throw new IllegalArgumentException("Tree is empty, no maximum value.");
        }
        int max = root.val;
        if (root.left != null) max = Math.max(max, maximum(root.left));
        if (root.right != null) max = Math.max(max, maximum(root.right));
        return max;
    }

    public static int leafCount(IntNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        return leafCount(root.left) + leafCount(root.right);
    }

    public static int height(IntNode root) {
        if (root == null) return 0;
        return 1 + Math.max(height(root.left), height(root.right));
    }

    public static boolean contains(IntNode root, int target) {
        if (root == null) return false;
        if (root.val == target) return true;
        return contains(root.left, target) || contains(root.right, target);
    }

    public static void main(String[] args) {
        IntNode root = new IntNode(-5);
        root.left = new IntNode(-10);
        root.right = new IntNode(-2);
        root.left.left = new IntNode(-15);

        System.out.println("Size: " + size(root));
        System.out.println("Sum: " + sum(root));
        System.out.println("Maximum: " + maximum(root));
        System.out.println("Leaf Count: " + leafCount(root));
        System.out.println("Height: " + height(root));
        System.out.println("Contains -2: " + contains(root, -2));

        try {
            maximum(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Empty Tree Maximum Exception: " + e.getMessage());
        }
    }
}
