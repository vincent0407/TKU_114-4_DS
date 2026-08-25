class Student {
    String studentId;
    String name;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    @Override
    public String toString() {
        return "[" + studentId + ", " + name + "]";
    }
}

class StudentNode {
    Student student;
    StudentNode left, right;

    public StudentNode(Student student) {
        this.student = student;
    }
}

public class StudentBstIndex {
    private StudentNode root;

    public boolean insert(Student s) {
        if (s == null || s.studentId == null) return false;
        int oldSize = size();
        root = insertRec(root, s);
        return size() > oldSize;
    }

    private StudentNode insertRec(StudentNode node, Student s) {
        if (node == null) return new StudentNode(s);
        int cmp = s.studentId.compareTo(node.student.studentId);
        if (cmp < 0) {
            node.left = insertRec(node.left, s);
        } else if (cmp > 0) {
            node.right = insertRec(node.right, s);
        } else {
            System.out.println("Insert Failed: Duplicate studentId " + s.studentId);
        }
        return node;
    }

    public Student search(String studentId) {
        StudentNode curr = root;
        while (curr != null) {
            int cmp = studentId.compareTo(curr.student.studentId);
            if (cmp == 0) return curr.student;
            else if (cmp < 0) curr = curr.left;
            else curr = curr.right;
        }
        return null;
    }

    public void delete(String studentId) {
        root = deleteRec(root, studentId);
    }

    private StudentNode deleteRec(StudentNode node, String studentId) {
        if (node == null) return null;
        int cmp = studentId.compareTo(node.student.studentId);
        if (cmp < 0) {
            node.left = deleteRec(node.left, studentId);
        } else if (cmp > 0) {
            node.right = deleteRec(node.right, studentId);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            StudentNode minNode = findMin(node.right);
            node.student = minNode.student;
            node.right = deleteRec(node.right, minNode.student.studentId);
        }
        return node;
    }

    private StudentNode findMin(StudentNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public int size() {
        return sizeRec(root);
    }

    private int sizeRec(StudentNode node) {
        if (node == null) return 0;
        return 1 + sizeRec(node.left) + sizeRec(node.right);
    }

    public void inorder() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(StudentNode node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.print(node.student + " ");
            inorderRec(node.right);
        }
    }

    public static void main(String[] args) {
        StudentBstIndex idx = new StudentBstIndex();
        idx.insert(new Student("102", "Alice"));
        idx.insert(new Student("101", "Bob"));
        idx.insert(new Student("103", "Charlie"));
        idx.insert(new Student("101", "Duplicate")); // 重複處理測試

        System.out.print("Current Students: ");
        idx.inorder();

        System.out.println("Search 102: " + idx.search("102"));
        idx.delete("102");
        System.out.print("After Deleting 102: ");
        idx.inorder();
    }
}