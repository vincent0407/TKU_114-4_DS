// 檔案名稱：ProductComparatorPractice.java

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// 1. 實作 Comparable 介面以定義 Natural Order (依 id 升冪)
class StoreProduct implements Comparable<StoreProduct> {
    private int id;
    private String name;
    private double price;
    private int stock;

    public StoreProduct(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    // Natural order: 依 id 升冪
    @Override
    public int compareTo(StoreProduct other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return String.format("StoreProduct[ID=%d, Name=%-10s, Price=%.1f, Stock=%d]",
                id, name, price, stock);
    }
}

public class ProductComparatorPractice {

    public static void main(String[] args) {
        // 建立原始商品清單 (至少五筆，包含同價與同庫存資料)
        List<StoreProduct> products = new ArrayList<>();
        products.add(new StoreProduct(105, "Keyboard", 1000.0, 50));
        products.add(new StoreProduct(102, "Mouse", 500.0, 100));
        products.add(new StoreProduct(101, "Monitor", 5000.0, 20));
        products.add(new StoreProduct(104, "Headset", 1000.0, 30));  // 同價 (1000.0)
        products.add(new StoreProduct(103, "Webcam", 1500.0, 100));  // 同庫存 (100)

        System.out.println("=== 0. 原始商品清單順序 ===");
        products.forEach(System.out::println);

        // 1. Natural Order: 依 id 升冪
        System.out.println("\n=== 1. Natural Order (依 id 升冪) ===");
        List<StoreProduct> copy1 = new ArrayList<>(products); // 建立 copy 保留原始順序
        Collections.sort(copy1); // 使用 Comparable 的 compareTo
        copy1.forEach(System.out::println);

        // 2. Comparator 一：依 price 升冪，同價時依 name (字典序)
        System.out.println("\n=== 2. Comparator 一 (依 price 升冪，同價時依 name) ===");
        List<StoreProduct> copy2 = new ArrayList<>(products);
        copy2.sort(Comparator.comparingDouble(StoreProduct::getPrice)
                             .thenComparing(StoreProduct::getName));
        copy2.forEach(System.out::println);

        // 3. Comparator 二：依 stock 降冪，同庫存時依 id (升冪)
        System.out.println("\n=== 3. Comparator 二 (依 stock 降冪，同庫存時依 id) ===");
        List<StoreProduct> copy3 = new ArrayList<>(products);
        copy3.sort(Comparator.comparingInt(StoreProduct::getStock).reversed()
                             .thenComparingInt(StoreProduct::getId));
        copy3.forEach(System.out::println);

        // 確認原始清單未受影響
        System.out.println("\n=== 驗證原始清單順序保持不變 ===");
        products.forEach(System.out::println);
    }
}
