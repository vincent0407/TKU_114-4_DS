import java.util.*;

public class TopSellingProducts {

    public static class Product implements Comparable<Product> {
        String id;
        int sales;

        public Product(String id, int sales) {
            this.id = id;
            this.sales = sales;
        }

        @Override
        public int compareTo(Product other) {
            if (this.sales != other.sales) {
                return Integer.compare(this.sales, other.sales); // 銷量小的在最上面 (Min-Heap 淘汰用)
            }
            return other.id.compareTo(this.id); // 銷量相同時，字典序較大者在 Top (排序會被淘汰)
        }

        @Override
        public String toString() {
            return id + " (sales: " + sales + ")";
        }
    }

    public static List<Product> getTopKProducts(List<Product> inputProducts, int k) {
        if (k <= 0 || inputProducts == null || inputProducts.isEmpty()) {
            return new ArrayList<>();
        }

        // 1. 合併重複商品 id 的銷量
        Map<String, Integer> mergedMap = new HashMap<>();
        for (Product p : inputProducts) {
            mergedMap.put(p.id, mergedMap.getOrDefault(p.id, 0) + p.sales);
        }

        // 2. 維護大小為 K 的 Min-Heap
        PriorityQueue<Product> minHeap = new PriorityQueue<>();

        for (Map.Entry<String, Integer> entry : mergedMap.entrySet()) {
            Product current = new Product(entry.getKey(), entry.getValue());
            if (minHeap.size() < k) {
                minHeap.add(current);
            } else {
                if (current.compareTo(minHeap.peek()) > 0) {
                    minHeap.poll();
                    minHeap.add(current);
                }
            }
        }

        // 3. 取出並依要求排序（銷量高優先，銷量相同字典序小優先）
        List<Product> result = new ArrayList<>(minHeap);
        result.sort((p1, p2) -> {
            if (p1.sales != p2.sales) {
                return Integer.compare(p2.sales, p1.sales);
            }
            return p1.id.compareTo(p2.id);
        });

        return result;
    }

    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("P100", 50),
            new Product("P200", 80),
            new Product("P100", 30), // 合併後 P100 銷量為 80
            new Product("P300", 80), // 銷量與 P100 相同，P100 字典序較優先
            new Product("P400", 120),
            new Product("P500", 10)
        );

        List<Product> topK = getTopKProducts(products, 3);
        System.out.println("Top-K 熱門商品：");
        for (Product p : topK) {
            System.out.println(p);
        }
    }
}
