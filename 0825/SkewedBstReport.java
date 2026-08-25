public class SkewedBstReport {

    public static Node createSkewedTree(int[] sortedData) {
        Node root = null;
        for (int val : sortedData) {
            root = insert(root, val);
        }
        return root;
    }

    public static Node createBalancedTree(int[] sortedData, int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        Node node = new Node(sortedData[mid]);
        node.left = createBalancedTree(sortedData, start, mid - 1);
        node.right = createBalancedTree(sortedData, mid + 1, end);
        return node;
    }

    private static Node insert(Node node, int val) {
        if (node == null) return new Node(val);
        if (val < node.val) node.left = insert(node.left, val);
        else node.right = insert(node.right, val);
        return node;
    }

    public static int getSize(Node node) {
        if (node == null) return 0;
        return 1 + getSize(node.left) + getSize(node.right);
    }

    public static int getHeight(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    public static int searchCount(Node root, int target) {
        int count = 0;
        Node curr = root;
        while (curr != null) {
            count++;
            if (target == curr.val) return count;
            else if (target < curr.val) curr = curr.left;
            else curr = curr.right;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] sortedData = {1, 2, 3, 4, 5, 6, 7};

        Node skewedRoot = createSkewedTree(sortedData);
        Node balancedRoot = createBalancedTree(sortedData, 0, sortedData.length - 1);

        int target = 7;

        System.out.println("=== Skewed Tree ===");
        System.out.println("Size: " + getSize(skewedRoot));
        System.out.println("Height: " + getHeight(skewedRoot));
        System.out.println("Search Count for " + target + ": " + searchCount(skewedRoot, target));

        System.out.println("\n=== Balanced Tree ===");
        System.out.println("Size: " + getSize(balancedRoot));
        System.out.println("Height: " + getHeight(balancedRoot));
        System.out.println("Search Count for " + target + ": " + searchCount(balancedRoot, target));
    }
}