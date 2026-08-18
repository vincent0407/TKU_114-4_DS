import java.util.Arrays;

// 介面 ReportExporter
interface ReportExporter {
    void export(String title, int[] values);
}

// CSV 匯出實作
class CsvExporter implements ReportExporter {
    @Override
    public void export(String title, int[] values) {
        String data = (values == null) ? "無資料" : Arrays.toString(values);
        System.out.println("[CSV 格式] 標題: " + title + " | 資料: " + data);
    }
}

// JSON 匯出實作
class JsonExporter implements ReportExporter {
    @Override
    public void export(String title, int[] values) {
        String data = (values == null) ? "null" : Arrays.toString(values);
        System.out.println("[JSON 格式] {\"title\": \"" + title + "\", \"values\": " + data + "}");
    }
}

// Text 匯出實作（預設/不支援 format 的備用方案）
class TextExporter implements ReportExporter {
    @Override
    public void export(String title, int[] values) {
        String data = (values == null) ? "無資料" : Arrays.toString(values);
        System.out.println("[TEXT 格式] 報表名稱: " + title + "\n內容數據: " + data);
    }
}

public class ReportExporterFactory {

    // 工廠方法：根據 format 回傳對應的 ReportExporter
    public static ReportExporter createExporter(String format) {
        if (format == null) {
            return new TextExporter();
        }

        return switch (format.toLowerCase().trim()) {
            case "csv" -> new CsvExporter();
            case "json" -> new JsonExporter();
            case "text" -> new TextExporter();
            default -> new TextExporter(); // 不支援的 format 回傳 TextExporter
        };
    }

    // 匯出報表：只依賴 ReportExporter 介面，不使用 instanceof
    public static void exportReport(ReportExporter exporter, String title, int[] values) {
        if (exporter != null) {
            exporter.export(title, values);
        }
    }

    public static void main(String[] args) {
        int[] sampleData = {10, 20, 30, 40};

        // 測試各種格式
        String[] formats = {"csv", "json", "text", "xml", "pdf"}; // xml 與 pdf 為不支援格式

        System.out.println("=== 報表輸出測試 ===");
        for (String fmt : formats) {
            ReportExporter exporter = createExporter(fmt);
            exportReport(exporter, "月度銷售報表 (" + fmt + ")", sampleData);
            System.out.println("-----------------------------------");
        }

        // 邊界條件測試：values 為 null 時不發生例外
        System.out.println("=== 邊界測試 (values 為 null) ===");
        ReportExporter nullDataExporter = createExporter("json");
        exportReport(nullDataExporter, "空資料報表", null);
    }
}
