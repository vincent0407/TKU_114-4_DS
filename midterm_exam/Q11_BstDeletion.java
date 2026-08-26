package midterm_exam;

import java.util.ArrayList;
import java.util.List;

public class Q11_BstDeletion {

    private static class Node {
        int value;
        Node left, right;
        Node(int value) { this.value = value; }
    }

    private Node root;
    private int count = 0;

    public boolean add(int value) {
        if (contains(value)) return false;
        root = addHelper(root, value);
        count++;
        return true;
    }

    private Node addHelper(Node node, int value) {
        if (node == null) return new Node(value);
        if (value < node.value) node.left = addHelper(node.left, value);
        else if (value > node.value) node.right = addHelper(node.right, value);
        return node;
    }

    public boolean contains(int value) {
        Node curr = root;
        while (curr != null) {
            if (value == curr.value) return true;
            else if (value < curr.value) curr = curr.left;
            else curr = curr.right;
        }
        return false;
    }

    public boolean remove(int value) {
        if (!contains(value)) return false;
        root = removeHelper(root, value);
        count--;
        return true;
    }

    private Node removeHelper(Node node, int value) {
        if (node == null) return null;

        if (value < node.value) {
            node.left = removeHelper(node.left, value);
        } else if (value > node.value) {
            node.right = removeHelper(node.right, value);
        } else {
            // Case 1 & 2: Leaf or Single child
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // Case 3: Two children
            Node minNode = getMin(node.right);
            node.value = minNode.value;
            node.right = removeHelper(node.right, minNode.value);
        }
        return node;
    }

    private Node getMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public int size() { return count; }

    public List<Integer> inorder() {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private void inorderHelper(Node node, List<Integer> result) {
        if (node == null) return;
        inorderHelper(node.left, result);
        result.add(node.value);
        inorderHelper(node.right, result);
    }

    public boolean isValid() {
        return isValidHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidHelper(Node node, long min, long max) {
        if (node == null) return true;
        if (node.value <= min || node.value >= max) return false;
        return isValidHelper(node.left, min, node.value) &&
               isValidHelper(node.right, node.value, max);
    }

    public static void main(String[] args) {
        Q11_BstDeletion tree = new Q11_BstDeletion();
        for (int value : new int[]{50, 30, 70, 20, 40, 60, 80}) {
            tree.add(value);
        }
        System.out.println(tree.remove(20));
        System.out.println(tree.remove(30));
        System.out.println(tree.remove(50));
        System.out.println(tree.remove(999));
        System.out.println(tree.inorder());
        System.out.println(tree.size());
        System.out.println(tree.isValid());
    }
}
