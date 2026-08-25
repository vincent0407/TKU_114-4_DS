import java.util.ArrayList;
import java.util.List;

public class DirectoryTreeReport {

    static class FileNode {
        String name;
        boolean isDirectory;
        long size; // 檔案大小，若為 directory 則由後序走訪計算
        List<FileNode> children;

        FileNode(String name, boolean isDirectory, long size) {
            this.name = name;
            this.isDirectory = isDirectory;
            this.size = isDirectory ? 0 : size;
            this.children = new ArrayList<>();
        }
    }

    static class StatReport {
        int totalNodes = 0;
        int fileCount = 0;
        int dirCount = 0;
        int height = 0;
        String maxFileName = "";
        long maxFileSize = -1;
    }

    // 後序走訪 (Postorder) 計算目錄容量與統計資訊
    public static long calculateAndReport(FileNode node, StatReport report, int currentDepth) {
        if (node == null) return 0;

        report.totalNodes++;
        report.height = Math.max(report.height, currentDepth);

        if (!node.isDirectory) {
            report.fileCount++;
            if (node.size > report.maxFileSize) {
                report.maxFileSize = node.size;
                report.maxFileName = node.name;
            }
            return node.size;
        }

        report.dirCount++;
        long currentDirTotalSize = 0;

        // Postorder: 先遞迴計算所有子節點/目錄
        for (FileNode child : node.children) {
            currentDirTotalSize += calculateAndReport(child, report, currentDepth + 1);
        }

        node.size = currentDirTotalSize; // 更新目錄總容量
        return node.size;
    }

    public static void main(String[] args) {
        FileNode root = new FileNode("root", true, 0);
        FileNode docs = new FileNode("docs", true, 0);
        FileNode f1 = new FileNode("resume.pdf", false, 500);
        FileNode f2 = new FileNode("photo.png", false, 2048);
        FileNode f3 = new FileNode("video.mp4", false, 10500);

        root.children.add(docs);
        root.children.add(f3);
        docs.children.add(f1);
        docs.children.add(f2);

        StatReport report = new StatReport();
        calculateAndReport(root, report, 1);

        System.out.println("=== 檔案系統統計報表 ===");
        System.out.println("Root 總容量 (KB): " + root.size);
        System.out.println("Total Nodes    : " + report.totalNodes);
        System.out.println("File Count     : " + report.fileCount);
        System.out.println("Directory Count: " + report.dirCount);
        System.out.println("Tree Height    : " + report.height);
        System.out.println("Max File       : " + report.maxFileName + " (" + report.maxFileSize + " KB)");
    }
}
