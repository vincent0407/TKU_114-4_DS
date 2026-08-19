// 檔案名稱：GenericRepositorySystem.java

import java.util.ArrayList;
import java.util.List;

// 泛型 Repository 類別
class Repository<T> {
    private List<T> items;

    public Repository() {
        this.items = new ArrayList<>();
    }

    // 新增資料
    public void add(T item) {
        if (item != null) {
            items.add(item);
        }
    }

    // 依索引取得資料
    public T get(int index) {
        if (index < 0 || index >= items.size()) {
            throw new IndexOutOfBoundsException("不合法的索引: " + index);
        }
        return items.get(index);
    }

    // 移除指定的資料物件 (回傳是否成功)
    public boolean remove(T item) {
        return items.remove(item);
    }

    // 依索引移除資料
    public T remove(int index) {
        if (index < 0 || index >= items.size()) {
            throw new IndexOutOfBoundsException("不合法的索引: " + index);
        }
        return items.remove(index);
    }

    // 取得當前總數量
    public int size() {
        return items.size();
    }

    // 完整輸出所有內容
    public void printAll() {
        System.out.println("Repository 內容 (共 " + size() + " 筆):");
        if (items.isEmpty()) {
            System.out.println("  (內容為空)");
        } else {
            for (int i = 0; i < items.size(); i++) {
                System.out.println("  [" + i + "] " + items.get(i));
            }
        }
    }
}

// 測試用商品類別 (可用於測試 Repository<Product>)
class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{name='" + name + "', price=" + price + "}";
    }
}

public class GenericRepositorySystem {

    public static void main(String[] args) {
        // ----------------------------------------------------
        // 1. 測試 Repository<String>
        // ----------------------------------------------------
        System.out.println("================ 1. 測試 Repository<String> ================");
        Repository<String> stringRepo = new Repository<>();
        stringRepo.add("Apple");
        stringRepo.add("Banana");
        stringRepo.add("Cherry");

        stringRepo.printAll();

        System.out.println("\n取得 index 1 的資料: " + stringRepo.get(1));

        System.out.println("移除 \"Banana\": " + stringRepo.remove("Banana"));
        stringRepo.printAll();

        // ----------------------------------------------------
        // 2. 測試 Repository<Product>
        // ----------------------------------------------------
        System.out.println("\n================ 2. 測試 Repository<Product> ================");
        Repository<Product> productRepo = new Repository<>();
        Product p1 = new Product("Laptop", 35000.0);
        Product p2 = new Product("Phone", 22000.0);
        Product p3 = new Product("Tablet", 15000.0);

        productRepo.add(p1);
        productRepo.add(p2);
        productRepo.add(p3);

        productRepo.printAll();

        System.out.println("\n移除 index 0 的商品: " + productRepo.remove(0));
        productRepo.printAll();

        System.out.println("當前數量 size(): " + productRepo.size());
    }
}
