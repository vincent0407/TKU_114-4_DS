import java.util.ArrayDeque;
import java.util.Deque;

public class BrowserBackStack {
    private Deque<String> historyStack = new ArrayDeque<>();

    // 造訪新頁面
    public void visit(String url) {
        historyStack.push(url);
        System.out.println("訪問頁面: " + url);
    }

    // 返回上一頁
    public String back() {
        if (historyStack.isEmpty()) {
            System.out.println("無歷史紀錄，無法返回");
            return null;
        }
        String popped = historyStack.pop();
        System.out.println("返回離開: " + popped);
        return popped;
    }

    // 查看當前頁面
    public String current() {
        if (historyStack.isEmpty()) {
            return "無頁面 (Stack 為空)";
        }
        return historyStack.peek();
    }

    public static void main(String[] args) {
        BrowserBackStack browser = new BrowserBackStack();

        // 測試連續操作（至少 5 個操作，包含空 stack 邊界測試）
        System.out.println("當前頁面: " + browser.current()); // 測試 1：空 stack 測試
        browser.back();                                      // 測試 2：空 stack 返回不拋出例外

        browser.visit("https://google.com");                 // 測試 3
        browser.visit("https://github.com");                 // 測試 4
        System.out.println("當前頁面: " + browser.current()); // 測試 5

        browser.visit("https://stackoverflow.com");          // 測試 6
        browser.back();                                      // 測試 7
        System.out.println("當前頁面: " + browser.current());
    }
}
