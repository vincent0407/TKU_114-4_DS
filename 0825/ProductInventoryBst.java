class Product {
    int id;
    String name;
    int stock;
    public Product(int id, String name, int stock) {
        this.id = id; this.name = name; this.stock = stock;
    }
}

public class ProductInventoryBst {
    private class Node {
        Product product;
        Node left, right;
        Node(Product p) { this.product = p; }
    }

    private Node root;

    public void insert(Product p) { root = insertRec(root, p); }
    private Node insertRec(Node root, Product p) {
        if (root == null) return new Node(p);
        if (p.id < root.product.id) root.left = insertRec(root.left, p);
        else if (p.id > root.product.id) root.right = insertRec(root.right, p);
        return root;
    }

    public Product search(int id) {
        Node curr = root;
        while (curr != null) {
            if (id == curr.product.id) return curr.product;
            curr = (id < curr.product.id) ? curr.left : curr.right;
        }
        return null;
    }

    public boolean addStock(int id, int amount) {
        Product p = search(id);
        if (p == null) return false;
        p.stock += amount;
        return true;
    }

    public boolean reduceStock(int id, int amount) {
        Product p = search(id);
        if (p == null || p.stock < amount) return false;
        p.stock -= amount;
        return true;
    }

    public void delete(int id) { root = deleteRec(root, id); }
    private Node deleteRec(Node root, int id) {
        if (root == null) return null;
        if (id < root.product.id) root.left = deleteRec(root.left, id);
        else if (id > root.product.id) root.right = deleteRec(root.right, id);
        else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            Node minNode = root.right;
            while (minNode.left != null) minNode = minNode.left;
            root.product = minNode.product;
            root.right = deleteRec(root.right, minNode.product.id);
        }
        return root;
    }

    public void inorderReport() { inorder(root); }
    private void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.println("ID: " + root.product.id + ", Name: " + root.product.name + ", Stock: " + root.product.stock);
            inorder(root.right);
        }
    }
}
