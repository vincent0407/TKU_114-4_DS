public class ThreeTraversalPractice {

    public static void preorder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        preorder(root.left);
        preorder(root.right);
    }

    public static void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    public static void postorder(TreeNode root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.val + " ");
    }

    public static void main(String[] args) {
        // 建立 M(F(B,null), T(R,Z)) 樹結構
        TreeNode m = new TreeNode('M');
        TreeNode f = new TreeNode('F');
        TreeNode b = new TreeNode('B');
        TreeNode t = new TreeNode('T');
        TreeNode r = new TreeNode('R');
        TreeNode z = new TreeNode('Z');

        m.left = f;
        m.right = t;
        f.left = b; // f.right 為 null
        t.left = r;
        t.right = z;

        System.out.print("Preorder:  ");
        preorder(m);
        System.out.println();

        System.out.print("Inorder:   ");
        inorder(m);
        System.out.println();

        System.out.print("Postorder: ");
        postorder(m);
        System.out.println();
    }
}
