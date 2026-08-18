class Equipment {
    private String id;
    private String name;
    private int availableCount;

    public Equipment(String id, String name, int availableCount) {
        // 空白 id 或 name 改為 Unknown
        this.id = (id == null || id.trim().isEmpty()) ? "Unknown" : id;
        this.name = (name == null || name.trim().isEmpty()) ? "Unknown" : name;
        // 負數量改為 0
        this.availableCount = (availableCount < 0) ? 0 : availableCount;
    }

    public boolean borrowOne() {
        if (this.availableCount > 0) {
            this.availableCount--;
            return true;
        }
        return false;
    }

    public void returnItems(int quantity) {
        if (quantity > 0) {
            this.availableCount += quantity;
        }
    }

    @Override
    public String toString() {
        return "設備編號: " + id + ", 名稱: " + name + ", 可借數量: " + availableCount;
    }
}

public class EquipmentInventory {
    public static void main(String[] args) {
        // 建立兩個設備（包含極端值測試：空白名稱與負數數量）
        Equipment eq1 = new Equipment("E01", "Laptop", 1);
        Equipment eq2 = new Equipment("", "   ", -5); // 會自動轉換為 Unknown 與 0

        System.out.println("=== 初始狀態 ===");
        System.out.println(eq1);
        System.out.println(eq2);

        System.out.println("\n=== 借用測試 ===");
        System.out.println("eq1 第一次借用: " + (eq1.borrowOne() ? "成功" : "失敗")); // 成功，剩 0
        System.out.println("eq1 第二次借用: " + (eq1.borrowOne() ? "成功" : "失敗")); // 失敗，保持 0
        System.out.println("eq2 借用測試: " + (eq2.borrowOne() ? "成功" : "失敗"));    // 失敗

        System.out.println("\n=== 歸還測試 ===");
        eq1.returnItems(3); // 正數，加入庫存
        eq1.returnItems(-2); // 負數，忽略
        System.out.println("eq1 歸還後狀態: " + eq1);
    }
}
