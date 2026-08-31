import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class LowestKPriceTracker {

    public static List<Integer> getLowestKPrices(Integer[] prices, int k) {
        if (k <= 0 || prices == null) {
            return new ArrayList<>();
        }

        // 使用 Max Heap (PriorityQueue 預設是 Min Heap，需傳入 Collections.reverseOrder())
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (Integer price : prices) {
            if (price == null || price < 0) {
                continue; // 忽略 null 與負數
            }

            if (maxHeap.size() < k) {
                maxHeap.add(price);
            } else if (price < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.add(price);
            }
        }

        List<Integer> result = new ArrayList<>(maxHeap);
        Collections.sort(result); // 依價格遞增排列
        return result;
    }

    public static void main(String[] args) {
        Integer[] prices = {100, 250, null, -50, 80, 500, 150, 30, 90};
        int k = 4;

        List<Integer> lowestK = getLowestKPrices(prices, k);
        System.out.println("最低 " + k + " 個價格為：" + lowestK);
    }
}
