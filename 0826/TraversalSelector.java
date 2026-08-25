class Node {
    String val;
    Node left, right;

    Node(String val) {
        this.val = val;
    }

    Node(String val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class TraversalSelector {

    // Preorder -> Prefix (前綴表示法)
    public static String preorderPrefix(Node root) {
        if (root == null) return "";
        return root.val + " " + preorderPrefix(root.left) + preorderPrefix(root.right);
    }

    // Inorder -> Infix (中綴表示法，明確加入括號)
    public static String inorderInfix(Node root) {
        if (root == null) return "";
        if (root.left == null && root.right == null) {
            return root.val;
        }
        return "(" + inorderInfix(root.left) + " " + root.val + " " + inorderInfix(root.right) + ")";
    }

    // Postorder -> Postfix (後綴表示法)
    public static String postorderPostfix(Node root) {
        if (root == null) return "";
        return postorderPostfix(root.left) + postorderPostfix(root.right) + root.val + " ";
    }

    public static void main(String[] args) {
        // 建立表達式樹 Expression Tree: (3 + (4 * 5))
        Node tree = new Node("+",
                new Node("3"),
                new Node("*", new Node("4"), new Node("5"))
        );

        System.out.println("Prefix  (Preorder) : " + preorderPrefix(tree).trim());
        System.out.println("Infix   (Inorder)  : " + inorderInfix(tree));
        System.out.println("Postfix (Postorder): " + postorderPostfix(tree).trim());
    }
}
