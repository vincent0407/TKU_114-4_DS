public class BstInvariantChecker {

    public static boolean isValidBST(Node node, Long min, Long max) {
        if (node == null) return true;
        if ((min != null && node.val <= min) || (max != null && node.val >= max)) {
            return false;
        }
        return isValidBST(node.left, min, (long) node.val) && 
               isValidBST(node.right, (long) node.val, max);
    }

    public static Node buildValidBST() {
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(15);
        root.left.left = new Node(2);
        root.left.right = new Node(7);
        root.right.left = new Node(12);
        root.right.right = new Node(20);
        return root;
    }

    public static Node buildInvalidBST() {
        // 建立包含至少三層深度的違規 Tree
        // 違規點：15 的左子樹右節點為 18（18 > 15 成立，但 18 > 10 Root 也成立，若將其擺在 root.left 下則違規）
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(15);
        
        root.left.left = new Node(2);
        root.left.right = new Node(12); // 錯誤：12 在 5 的右側，但 12 大於根節點 10！
        root.left.right.left = new Node(6);
        return root;
    }

    public static void main(String[] args) {
        Node validTree = buildValidBST();
        Node invalidTree = buildInvalidBST();

        System.out.println("Valid Tree Verification: " + isValidBST(validTree, null, null));
        System.out.println("Invalid Tree Verification: " + isValidBST(invalidTree, null, null));
    }
}