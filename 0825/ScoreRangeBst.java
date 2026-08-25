import java.util.ArrayList;
import java.util.List;

class StudentScore implements Comparable<StudentScore> {
    int score;
    String studentId;

    public StudentScore(int score, String studentId) {
        this.score = score;
        this.studentId = studentId;
    }

    @Override
    public int compareTo(StudentScore o) {
        if (this.score != o.score) return Integer.compare(this.score, o.score);
        return this.studentId.compareTo(o.studentId); // 複合順序
    }
}

public class ScoreRangeBst {
    private class Node {
        StudentScore key;
        Node left, right;
        Node(StudentScore key) { this.key = key; }
    }

    private Node root;

    public void insert(StudentScore key) { root = insertRec(root, key); }
    private Node insertRec(Node root, StudentScore key) {
        if (root == null) return new Node(key);
        int cmp = key.compareTo(root.key);
        if (cmp < 0) root.left = insertRec(root.left, key);
        else if (cmp > 0) root.right = insertRec(root.right, key);
        return root;
    }

    public List<StudentScore> rangeSearch(int minScore, int maxScore) {
        List<StudentScore> result = new ArrayList<>();
        rangeRec(root, minScore, maxScore, result);
        return result;
    }

    private void rangeRec(Node node, int min, int max, List<StudentScore> res) {
        if (node == null) return;
        if (node.key.score > min) rangeRec(node.left, min, max, res);
        if (node.key.score >= min && node.key.score <= max) res.add(node.key);
        if (node.key.score < max) rangeRec(node.right, min, max, res);
    }
}

