class Member {
    String memberId;
    String name;
    String email;

    public Member(String memberId, String name, String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email 不得為空");
        }
        this.memberId = memberId;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("[ID: %s, Name: %s, Email: %s]", memberId, name, email);
    }
}

public class MemberBstIndex {

    static class Node {
        Member member;
        Node left, right;
        Node(Member member) { this.member = member; }
    }

    private Node root;

    public boolean add(Member member) {
        if (find(member.memberId) != null) return false; // ID 不可重複
        root = addRec(root, member);
        return true;
    }

    private Node addRec(Node node, Member member) {
        if (node == null) return new Node(member);
        int cmp = member.memberId.compareTo(node.member.memberId);
        if (cmp < 0) node.left = addRec(node.left, member);
        else if (cmp > 0) node.right = addRec(node.right, member);
        return node;
    }

    public Member find(String memberId) {
        return findRec(root, memberId);
    }

    private Member findRec(Node node, String memberId) {
        if (node == null) return null;
        int cmp = memberId.compareTo(node.member.memberId);
        if (cmp < 0) return findRec(node.left, memberId);
        if (cmp > 0) return findRec(node.right, memberId);
        return node.member;
    }

    public boolean updateEmail(String memberId, String newEmail) {
        if (newEmail == null || newEmail.trim().isEmpty()) return false;
        Member m = find(memberId);
        if (m != null) {
            m.email = newEmail;
            return true;
        }
        return false;
    }

    public boolean remove(String memberId) {
        if (find(memberId) == null) return false;
        root = removeRec(root, memberId);
        return true;
    }

    private Node removeRec(Node node, String memberId) {
        if (node == null) return null;
        int cmp = memberId.compareTo(node.member.memberId);
        if (cmp < 0) node.left = removeRec(node.left, memberId);
        else if (cmp > 0) node.right = removeRec(node.right, memberId);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node min = node.right;
            while (min.left != null) min = min.left;
            node.member = min.member;
            node.right = removeRec(node.right, min.member.memberId);
        }
        return node;
    }

    public void inorderReport() {
        inorderRec(root);
    }

    private void inorderRec(Node node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.println(node.member);
            inorderRec(node.right);
        }
    }

    public static void main(String[] args) {
        MemberBstIndex index = new MemberBstIndex();
        index.add(new Member("M002", "Bob", "bob@mail.com"));
        index.add(new Member("M001", "Alice", "alice@mail.com"));
        index.add(new Member("M003", "Charlie", "charlie@mail.com"));

        System.out.println("=== Inorder 會員報表 ===");
        index.inorderReport();

        System.out.println("\n更新 Email:");
        index.updateEmail("M001", "alice_new@mail.com");
        System.out.println(index.find("M001"));
    }
}
