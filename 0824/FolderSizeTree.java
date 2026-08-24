import java.util.ArrayList;
import java.util.List;

class FolderNode {
    String name;
    int ownSize;
    FolderNode left;
    FolderNode right;

    FolderNode(String name, int ownSize) {
        this.name = name;
        this.ownSize = ownSize;
    }
}

public class FolderSizeTree {

    private static FolderNode maxSubtreeNode = null;
    private static int maxSubtreeSize = -1;

    public static int calculateSubtreeSizes(FolderNode root, List<String> leafFolders) {
        if (root == null) return 0;

        int leftSize = calculateSubtreeSizes(root.left, leafFolders);
        int rightSize = calculateSubtreeSizes(root.right, leafFolders);

        int totalSubtreeSize = root.ownSize + leftSize + rightSize;

        // 記錄葉子資料夾
        if (root.left == null && root.right == null) {
            leafFolders.add(root.name + " (" + root.ownSize + " KB)");
        }

        // 追蹤最大 subtree
        if (totalSubtreeSize > maxSubtreeSize) {
            maxSubtreeSize = totalSubtreeSize;
            maxSubtreeNode = root;
        }

        return totalSubtreeSize;
    }

    public static void main(String[] args) {
        FolderNode root = new FolderNode("Root", 100);
        root.left = new FolderNode("Docs", 50);
        root.right = new FolderNode("Media", 200);
        root.left.left = new FolderNode("PDFs", 150);
        root.right.left = new FolderNode("Images", 300);
        root.right.right = new FolderNode("Videos", 500);

        List<String> leafFolders = new ArrayList<>();
        int totalSize = calculateSubtreeSizes(root, leafFolders);

        System.out.println("Total System Size: " + totalSize + " KB");
        if (maxSubtreeNode != null) {
            System.out.println("Max Subtree: " + maxSubtreeNode.name + " (Total Subtree Size: " + maxSubtreeSize + " KB)");
        }
        System.out.println("Leaf Folders: " + leafFolders);
    }
}
