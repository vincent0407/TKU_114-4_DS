package midterm_exam;

import java.util.ArrayList;
import java.util.List;

public class Q10_BstDirectory {

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

    public int size() { return count; }

    public List<Integer> searchPath(int target) {
        List<Integer> path = new ArrayList<>();
        Node curr = root;
        while (curr != null) {
            path.add(curr.value);
            if (target == curr.value) return path;
            else if (target < curr.value) curr = curr.left;
            else curr = curr.right;
        }
        return new ArrayList<>(); // 找不到回傳空 List
    }

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
        Q10_BstDirectory tree = new Q10_BstDirectory();
        for (int value : new int[]{50, 30, 70, 20, 40, 60, 80}) {
            tree.add(value);
        }
        System.out.println(tree.add(40));
        System.out.println(tree.searchPath(60));
        System.out.println(tree.searchPath(65));
        System.out.println(tree.inorder());
        System.out.println(tree.isValid());
    }
}
