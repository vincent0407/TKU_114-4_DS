package midterm_exam;

public class Q01_InventoryItem {
    private final String id;
    private final String name;
    private int stock;

    public Q01_InventoryItem(String id, String name, int stock) {
        // 1. id 或 name 為 null
        if (id == null || name == null) {
            throw new IllegalArgumentException("id and name cannot be null");
        }

        String trimmedId = id.trim();
        String trimmedName = name.trim();

        // 1. 去除前後空白後為空字串
        if (trimmedId.isEmpty() || trimmedName.isEmpty()) {
            throw new IllegalArgumentException("id and name cannot be empty");
        }

        // 2. id 與 name 儲存去除前後空白後的內容
        this.id = trimmedId;
        this.name = trimmedName;

        // 3. stock 小於 0 時以 0 儲存
        this.stock = Math.max(0, stock);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    // 4. restock(amount)
    public boolean restock(int amount) {
        if (amount > 0) {
            this.stock += amount;
            return true;
        }
        return false;
    }

    // 5. sell(amount)
    public boolean sell(int amount) {
        if (amount > 0 && this.stock >= amount) {
            this.stock -= amount;
            return true;
        }
        return false;
    }

    // 6. status()
    public String status() {
        return id + "|" + name + "|" + stock;
    }

    // 測試用 main 方法
    public static void main(String[] args) {
        Q01_InventoryItem item = new Q01_InventoryItem(" P100 ", " Keyboard ", 5);
        System.out.println(item.restock(3));
        System.out.println(item.sell(6));
        System.out.println(item.sell(3));
        System.out.println(item.status());
    }
}
