import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class OrgNode {
    String name;
    OrgNode left;
    OrgNode right;

    OrgNode(String name) {
        this.name = name;
    }
}

public class OrganizationTreeReport {

    public static String findParent(OrgNode root, String target) {
        if (root == null || root.name.equals(target)) return null;
        return findParentHelper(root, target);
    }

    private static String findParentHelper(OrgNode node, String target) {
        if (node == null) return null;

        if ((node.left != null && node.left.name.equals(target)) ||
            (node.right != null && node.right.name.equals(target))) {
            return node.name;
        }

        String leftResult = findParentHelper(node.left, target);
        if (leftResult != null) return leftResult;

        return findParentHelper(node.right, target);
    }

    public static int findDepth(OrgNode root, String target) {
        return findDepthHelper(root, target, 0);
    }

    private static int findDepthHelper(OrgNode node, String target, int depth) {
        if (node == null) return -1;
        if (node.name.equals(target)) return depth;

        int leftDepth = findDepthHelper(node.left, target, depth + 1);
        if (leftDepth != -1) return leftDepth;

        return findDepthHelper(node.right, target, depth + 1);
    }

    public static List<String> pathFromRoot(OrgNode root, String target) {
        List<String> path = new ArrayList<>();
        if (!findPathHelper(root, target, path)) {
            return new ArrayList<>(); // 找不到時傳回空集合
        }
        return path;
    }

    private static boolean findPathHelper(OrgNode node, String target, List<String> path) {
        if (node == null) return false;

        path.add(node.name);
        if (node.name.equals(target)) return true;

        if (findPathHelper(node.left, target, path) || findPathHelper(node.right, target, path)) {
            return true;
        }

        path.remove(path.size() - 1);
        return false;
    }

    public static void printByLevel(OrgNode root) {
        if (root == null) {
            System.out.println("Organization tree is empty.");
            return;
        }

        Queue<OrgNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            System.out.print("Level " + level + ": ");
            for (int i = 0; i < size; i++) {
                OrgNode current = queue.poll();
                System.out.print(current.name + " ");
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }
            System.out.println();
            level++;
        }
    }

    public static void main(String[] args) {
        OrgNode ceo = new OrgNode("CEO");
        ceo.left = new OrgNode("VP Sales");
        ceo.right = new OrgNode("VP Tech");
        ceo.left.left = new OrgNode("Sales Manager");
        ceo.right.left = new OrgNode("Dev Lead");
        ceo.right.right = new OrgNode("QA Lead");

        System.out.println("Parent of 'Dev Lead': " + findParent(ceo, "Dev Lead"));
        System.out.println("Parent of 'CEO': " + findParent(ceo, "CEO"));
        System.out.println("Parent of 'Ghost': " + findParent(ceo, "Ghost"));

        System.out.println("Depth of 'Dev Lead': " + findDepth(ceo, "Dev Lead"));
        System.out.println("Depth of 'Ghost': " + findDepth(ceo, "Ghost"));

        System.out.println("Path to 'Dev Lead': " + pathFromRoot(ceo, "Dev Lead"));
        System.out.println("Path to 'Ghost': " + pathFromRoot(ceo, "Ghost"));

        System.out.println("\n--- Level Order Hierarchy ---");
        printByLevel(ceo);
    }
}
