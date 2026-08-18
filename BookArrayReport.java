class Book {
    private String id;
    private String title;
    private double price;
    private int stock;

    public Book(String id, String title, double price, int stock) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public double getTotalValue() {
        return price * stock;
    }

    @Override
    public String toString() {
        return "書號: " + id + ", 書名: " + title + ", 價格: " + price + ", 庫存: " + stock;
    }
}

public class BookArrayReport {
    public static void main(String[] args) {
        // 以 Book[] 保存至少四本書
        Book[] books = {
            new Book("B001", "Java 程式設計入門", 500.0, 5),
            new Book("B002", "資料結構演算法", 650.0, 2),
            new Book("B003", "物件導向導論", 420.0, 1),
            new Book("B004", "系統架構設計", 800.0, 8)
        };

        // 1. 輸出所有書籍
        System.out.println("=== 1. 所有書籍清單 ===");
        for (Book book : books) {
            System.out.println(book);
        }

        // 2. 計算庫存總價值 (price * stock)
        double totalInventoryValue = 0;
        for (Book book : books) {
            totalInventoryValue += book.getTotalValue();
        }
        System.out.println("\n=== 2. 庫存總價值 ===");
        System.out.println("庫存總價值: $" + totalInventoryValue);

        // 3. 找出價格最高的書
        Book maxPriceBook = books[0];
        for (int i = 1; i < books.length; i++) {
            if (books[i].getPrice() > maxPriceBook.getPrice()) {
                maxPriceBook = books[i];
            }
        }
        System.out.println("\n=== 3. 價格最高的書籍 ===");
        System.out.println(maxPriceBook);

        // 4. 輸出庫存小於或等於 3 的書
        System.out.println("\n=== 4. 庫存 <= 3 的書籍 ===");
        for (Book book : books) {
            if (book.getStock() <= 3) {
                System.out.println(book);
            }
        }
    }
}
