import java.util.Arrays;

// Immutable 類別設計：加上 final 關鍵字禁止繼承
final class InventorySnapshot {
    private final String warehouseId;
    private final int[] quantities;

    public InventorySnapshot(String warehouseId, int[] quantities) {
        this.warehouseId = warehouseId;
        // 邊界條件：收到 null 陣列時，建立長度為 0 的內部陣列
        if (quantities == null) {
            this.quantities = new int[0];
        } else {
            // Defensive Copy：複製傳入的陣列，避免外部修改影響內部資料
            this.quantities = Arrays.copyOf(quantities, quantities.length);
        }
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public int[] getQuantities() {
        // Defensive Copy：回傳複本而非內部陣列位址，保持 Immutable 特性
        return Arrays.copyOf(quantities, quantities.length);
    }

    // 回傳總數量
    public int totalQuantity() {
        int sum = 0;
        for (int q : quantities) {
            sum += q;
        }
        return sum;
    }

    // 回傳數量為 0 的品項數
    public int outOfStockCount() {
        int count = 0;
        for (int q : quantities) {
            if (q == 0) {
                count++;
            }
        }
        return count;
    }
}

public class InventorySnapshotPractice {
    public static void main(String[] args) {
        // 使用 {5, 0, 3, 0} 測試
        int[] testData = {5, 0, 3, 0};
        InventorySnapshot snapshot = new InventorySnapshot("WH-01", testData);

        // 驗證基本功能
        System.out.println("倉庫編號: " + snapshot.getWarehouseId());
        System.out.println("總數量 (應為 8): " + snapshot.totalQuantity());
        System.out.println("缺貨品項數 (應為 2): " + snapshot.outOfStockCount());

        // 測試 Defensive Copy (外部修改陣列不應影響 snapshot)
        testData[0] = 999;
        snapshot.getQuantities()[1] = 999;
        System.out.println("外部修改後總數量 (仍應為 8): " + snapshot.totalQuantity());

        // 測試邊界條件：傳入 null 陣列
        InventorySnapshot nullSnapshot = new InventorySnapshot("WH-NULL", null);
        System.out.println("Null 陣列測試 - 總數量 (應為 0): " + nullSnapshot.totalQuantity());
        System.out.println("Null 陣列測試 - 缺貨品項數 (應為 0): " + nullSnapshot.outOfStockCount());
    }
}
