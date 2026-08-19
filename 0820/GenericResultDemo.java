// 檔案名稱：GenericResultDemo.java

// 泛型結果包裝類別
class Result<T> {
    private boolean success;
    private String message;
    private T data;

    public Result(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        // 失敗時 data 自動設為 null 或傳入 null
        this.data = success ? data : null;
    }

    // 成功時的靜態工廠方法 (Convenience Method)
    public static <T> Result<T> success(T data, String message) {
        return new Result<>(true, message, data);
    }

    // 失敗時的靜態工廠方法，確保 data 為 null
    public static <T> Result<T> fail(String message) {
        return new Result<>(false, message, null);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

public class GenericResultDemo {
    public static void main(String[] args) {
        // 1. 建立 Result<String>
        Result<String> stringResult = Result.success("Hello Java Generics!", "取得成功");
        // 取出資料不需要強轉 (cast)
        String strData = stringResult.getData(); 
        System.out.println("String Result: " + strData);

        // 2. 建立 Result<Integer>
        Result<Integer> intResult = Result.success(100, "計算成功");
        Integer intData = intResult.getData();
        System.out.println("Integer Result: " + intData);

        // 3. 處理失敗狀況，確認 data == null
        Result<String> failResult = Result.fail("帳號或密碼錯誤");
        System.out.println("Fail Result Message: " + failResult.getMessage());
        System.out.println("Fail Result Data (應為 null): " + failResult.getData());

        // 編譯階段類型檢查範例：
        // String text = intResult.getData(); // 這行會在編譯階段直接報錯！
    }
}
