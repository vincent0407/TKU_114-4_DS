import java.util.ArrayList;
import java.util.List;

class Book {
    String isbn;
    String title;
    String author;
    boolean available;

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    @Override
    public String toString() {
        return String.format("[ISBN: %s] %-15s | 作者: %-10s | 狀態: %s",
                isbn, title, author, available ? "可借閱" : "已借出");
    }
}

public class LibraryBookBst {

    static class Node {
        Book book;
        Node left, right;
        Node(Book book) { this.book = book; }
    }

    private Node root;

    public boolean add(Book book) {
        if (find(book.isbn) != null) return false;
        root = addRec(root, book);
        return true;
    }

    private Node addRec(Node n, Book book) {
        if (n == null) return new Node(book);
        int cmp = book.isbn.compareTo(n.book.isbn);
        if (cmp < 0) n.left = addRec(n.left, book);
        else if (cmp > 0) n.right = addRec(n.right, book);
        return n;
    }

    public Book find(String isbn) { return findRec(root, isbn); }
    private Book findRec(Node n, String isbn) {
        if (n == null) return null;
        int cmp = isbn.compareTo(n.book.isbn);
        if (cmp < 0) return findRec(n.left, isbn);
        if (cmp > 0) return findRec(n.right, isbn);
        return n.book;
    }

    public boolean borrowBook(String isbn) {
        Book b = find(isbn);
        if (b != null && b.available) {
            b.available = false;
            return true;
        }
        return false;
    }

    public boolean returnBook(String isbn) {
        Book b = find(isbn);
        if (b != null && !b.available) {
            b.available = true;
            return true;
        }
        return false;
    }

    public boolean remove(String isbn) {
        Book b = find(isbn);
        if (b == null || !b.available) return false; // 借出中的書不得刪除
        root = removeRec(root, isbn);
        return true;
    }

    private Node removeRec(Node n, String isbn) {
        if (n == null) return null;
        int cmp = isbn.compareTo(n.book.isbn);
        if (cmp < 0) n.left = removeRec(n.left, isbn);
        else if (cmp > 0) n.right = removeRec(n.right, isbn);
        else {
            if (n.left == null) return n.right;
            if (n.right == null) return n.left;
            Node min = n.right;
            while (min.left != null) min = min.left;
            n.book = min.book;
            n.right = removeRec(n.right, min.book.isbn);
        }
        return n;
    }

    public List<Book> rangeQuery(String startIsbn, String endIsbn) {
        List<Book> res = new ArrayList<>();
        rangeRec(root, startIsbn, endIsbn, res);
        return res;
    }

    private void rangeRec(Node n, String start, String end, List<Book> res) {
        if (n == null) return;
        if (n.book.isbn.compareTo(start) > 0) rangeRec(n.left, start, end, res);
        if (n.book.isbn.compareTo(start) >= 0 && n.book.isbn.compareTo(end) <= 0) res.add(n.book);
        if (n.book.isbn.compareTo(end) < 0) rangeRec(n.right, start, end, res);
    }

    public void inorderReport() { inorderRec(root); }
    private void inorderRec(Node n) {
        if (n != null) {
            inorderRec(n.left);
            System.out.println(n.book);
            inorderRec(n.right);
        }
    }

    public static void main(String[] args) {
        LibraryBookBst lib = new LibraryBookBst();
        lib.add(new Book("978-01","Java Core","Oracle"));
        lib.add(new Book("978-03","Algorithms","Sedgewick"));
        lib.add(new Book("978-02","Data Structure","Weiss"));

        System.out.println("=== 館藏 Inorder 報表 ===");
        lib.inorderReport();

        System.out.println("\n借出 978-02:");
        lib.borrowBook("978-02");
        System.out.println("嘗試刪除借出中的 978-02 狀態: " + lib.remove("978-02"));

        System.out.println("歸還 978-02:");
        lib.returnBook("978-02");
        System.out.println("嘗試刪除歸還後的 978-02 狀態: " + lib.remove("978-02"));
    }
}
