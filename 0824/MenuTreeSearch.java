class MenuNode {
    String name;
    MenuNode left;
    MenuNode right;

    MenuNode(String name) {
        this.name = name;
    }
}

public class MenuTreeSearch {

    public static boolean contains(MenuNode root, String target) {
        if (root == null) return false;
        if (root.name.equals(target)) return true;
        return contains(root.left, target) || contains(root.right, target);
    }

    public static int findDepth(MenuNode root, String target) {
        return findDepthHelper(root, target, 0);
    }

    private static int findDepthHelper(MenuNode node, String target, int depth) {
        if (node == null) return -1;
        if (node.name.equals(target)) return depth;

        int leftDepth = findDepthHelper(node.left, target, depth + 1);
        if (leftDepth != -1) return leftDepth;

        return findDepthHelper(node.right, target, depth + 1);
    }

    public static int countLeaves(MenuNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        return countLeaves(root.left) + countLeaves(root.right);
    }

    public static void displayPreorder(MenuNode root) {
        if (root == null) return;
        System.out.println(root.name);
        displayPreorder(root.left);
        displayPreorder(root.right);
    }

    public static void main(String[] args) {
        MenuNode root = new MenuNode("Main Menu");
        root.left = new MenuNode("Settings");
        root.right = new MenuNode("File");
        root.left.left = new MenuNode("Display");
        root.left.right = new MenuNode("Sound");

        System.out.println("--- Preorder Display ---");
        displayPreorder(root);

        System.out.println("\n--- Search Results ---");
        System.out.println("Contains 'Sound': " + contains(root, "Sound"));
        System.out.println("Depth of 'Sound': " + findDepth(root, "Sound"));
        System.out.println("Depth of 'Unknown': " + findDepth(root, "Unknown"));
        System.out.println("Leaf Count: " + countLeaves(root));
    }
}
