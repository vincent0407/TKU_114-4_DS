import java.util.*;

public class ListImplementationLab {

    // 撰寫只接收 List<Integer> 的通用方法
    public static void testListOperations(List<Integer> list, String listType) {
        System.out.println("=== 測試 " + listType + " ===");

        // 1. 尾端新增
        list.add(10);
        list.add(20);
        list.add(30);

        // 2. 指定位置插入
        list.add(1, 15); // 在索引 1 插入 15

        // 3. 搜尋
        int searchTarget = 20;
        int index = list.indexOf(searchTarget);
        System.out.println("搜尋 " + searchTarget + " 的索引位置: " + index);

        // 4. 刪除
        list.remove(Integer.valueOf(10)); // 刪除值為 10 的元素

        // 5. 總和
        int sum = 0;
        for (int num : list) {
            sum += num;
        }
        System.out.println("列表元素: " + list);
        System.out.println("總和: " + sum + "\n");
    }

    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        testListOperations(arrayList, "ArrayList");
        testListOperations(linkedList, "LinkedList");

        /*
         * 內部成本差異說明：
         * 1. 尾端新增 (add)：ArrayList 均攤時間複雜度為 O(1)（若需擴容則為 O(n)）；LinkedList 為 O(1)。
         * 2. 指定位置插入/刪除 (add/remove)：ArrayList 需要移動後續元素，成本 O(n)；LinkedList 搜尋指定點需要 O(n)，但指標修改為 O(1)。
         * 3. 搜尋/隨機存取 (get/indexOf)：ArrayList 支援 O(1) 隨機存取；LinkedList 需從頭/尾走訪，成本為 O(n)。
         * 4. 記憶體開銷：ArrayList 使用連續陣列，較省空間；LinkedList 每個節點皆需額外儲存 Prev/Next 指標，記憶體開銷較大。
         */
    }
}
