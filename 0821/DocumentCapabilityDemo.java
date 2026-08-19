// 匯出介面
interface Exportable {
    void exportFile();
}

// 壓縮介面
interface Compressible {
    void compressFile();
}

// 同時實作兩個介面的 BackupDocument 類別
class BackupDocument implements Exportable, Compressible {
    @Override
    public void exportFile() {
        System.out.println("執行文件匯出功能...");
    }

    @Override
    public void compressFile() {
        System.out.println("執行文件壓縮功能...");
    }
}

public class DocumentCapabilityDemo {
    public static void main(String[] args) {
        // 建立物件
        BackupDocument doc = new BackupDocument();

        // 使用不同 interface reference 引用同一物件
        Exportable exportableRef = doc;
        Compressible compressibleRef = doc;

        System.out.println("--- 驗證指標指向同一物件 ---");
        System.out.println("exportableRef 與 doc 指向同一物件: " + (exportableRef == doc));
        System.out.println("compressibleRef 與 doc 指向同一物件: " + (compressibleRef == doc));

        System.out.println("\n--- 驗證可見的 method 不同 ---");
        // exportableRef 只能看到 Exportable 介面定義的方法
        exportableRef.exportFile();
        // exportableRef.compressFile(); // 這行若取消註解會編譯錯誤！

        // compressibleRef 只能看到 Compressible 介面定義的方法
        compressibleRef.compressFile();
        // compressibleRef.exportFile(); // 這行若取消註解會編譯錯誤！
    }
}
