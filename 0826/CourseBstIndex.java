import java.util.ArrayList;
import java.util.List;

class Course {
    String courseCode;
    String title;
    int credit; // 限制在 1 到 6

    public Course(String courseCode, String title, int credit) {
        if (credit < 1 || credit > 6) {
            throw new IllegalArgumentException("Credit 必須介於 1 到 6 之間");
        }
        this.courseCode = courseCode;
        this.title = title;
        this.credit = credit;
    }

    @Override
    public String toString() {
        return String.format("[%s] %-15s | 學分: %d", courseCode, title, credit);
    }
}

public class CourseBstIndex {

    static class Node {
        Course course;
        Node left, right;
        Node(Course course) { this.course = course; }
    }

    private Node root;

    public boolean add(Course course) {
        if (find(course.courseCode) != null) return false; // 重複 code 不可加入
        root = addRec(root, course);
        return true;
    }

    private Node addRec(Node node, Course course) {
        if (node == null) return new Node(course);
        int cmp = course.courseCode.compareTo(node.course.courseCode);
        if (cmp < 0) node.left = addRec(node.left, course);
        else if (cmp > 0) node.right = addRec(node.right, course);
        return node;
    }

    public Course find(String courseCode) {
        return findRec(root, courseCode);
    }

    private Course findRec(Node node, String courseCode) {
        if (node == null) return null;
        int cmp = courseCode.compareTo(node.course.courseCode);
        if (cmp < 0) return findRec(node.left, courseCode);
        if (cmp > 0) return findRec(node.right, courseCode);
        return node.course;
    }

    public boolean updateCredit(String courseCode, int newCredit) {
        if (newCredit < 1 || newCredit > 6) return false;
        Course c = find(courseCode);
        if (c != null) {
            c.credit = newCredit;
            return true;
        }
        return false;
    }

    public boolean remove(String courseCode) {
        if (find(courseCode) == null) return false;
        root = removeRec(root, courseCode);
        return true;
    }

    private Node removeRec(Node node, String courseCode) {
        if (node == null) return null;
        int cmp = courseCode.compareTo(node.course.courseCode);
        if (cmp < 0) node.left = removeRec(node.left, courseCode);
        else if (cmp > 0) node.right = removeRec(node.right, courseCode);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node min = node.right;
            while (min.left != null) min = min.left;
            node.course = min.course;
            node.right = removeRec(node.right, min.course.courseCode);
        }
        return node;
    }

    // 範圍查詢：Code Range Query (利用剪枝)
    public List<Course> codeRangeQuery(String startCode, String endCode) {
        List<Course> result = new ArrayList<>();
        rangeRec(root, startCode, endCode, result);
        return result;
    }

    private void rangeRec(Node node, String start, String end, List<Course> res) {
        if (node == null) return;
        if (node.course.courseCode.compareTo(start) > 0) rangeRec(node.left, start, end, res);
        if (node.course.courseCode.compareTo(start) >= 0 && node.course.courseCode.compareTo(end) <= 0) {
            res.add(node.course);
        }
        if (node.course.courseCode.compareTo(end) < 0) rangeRec(node.right, start, end, res);
    }

    public void inorderReport() {
        inorderRec(root);
    }

    private void inorderRec(Node node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.println(node.course);
            inorderRec(node.right);
        }
    }

    public static void main(String[] args) {
        CourseBstIndex index = new CourseBstIndex();
        index.add(new Course("CS101", "Intro to CS", 3));
        index.add(new Course("CS201", "Data Structures", 4));
        index.add(new Course("CS301", "Algorithms", 3));
        index.add(new Course("MATH101", "Calculus", 4));

        System.out.println("=== 課程排序報表 (Inorder) ===");
        index.inorderReport();

        System.out.println("\n=== 範圍查詢 [CS100 ~ CS250] ===");
        List<Course> list = index.codeRangeQuery("CS100", "CS250");
        for (Course c : list) System.out.println(c);
    }
}
