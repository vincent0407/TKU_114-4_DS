import java.util.Arrays;
import java.util.List;

public class TraversalTestReport {

    public static void runTest(String testName, TreeNode root, List<String> expPre, List<String> expIn, List<String> expPost, List<String> expLevel) {
        List<String> actPre = TraversalResultCollector.preorder(root);
        List<String> actIn = TraversalResultCollector.inorder(root);
        List<String> actPost = TraversalResultCollector.postorder(root);
        List<String> actLevel = TraversalResultCollector.levelOrder(root);

        boolean preMatch = actPre.equals(expPre);
        boolean inMatch = actIn.equals(expIn);
        boolean postMatch = actPost.equals(expPost);
        boolean levelMatch = actLevel.equals(expLevel);

        boolean allMatch = preMatch && inMatch && postMatch && levelMatch;

        System.out.println("==========================================");
        System.out.println("Test Case: " + testName);
        System.out.println("Preorder   Match: " + preMatch + " (Act: " + actPre + ")");
        System.out.println("Inorder    Match: " + inMatch + " (Act: " + actIn + ")");
        System.out.println("Postorder  Match: " + postMatch + " (Act: " + actPost + ")");
        System.out.println("LevelOrder Match: " + levelMatch + " (Act: " + actLevel + ")");
        System.out.println("OVERALL RESULT: " + (allMatch ? "PASS" : "FAIL"));
        System.out.println("==========================================");
    }

    public static void main(String[] args) {
        // 1. Empty Tree
        runTest("Empty Tree", null, Arrays.asList(), Arrays.asList(), Arrays.asList(), Arrays.asList());

        // 2. Single-Node
        runTest("Single-Node", new TreeNode('A'), 
                Arrays.asList("A"), Arrays.asList("A"), Arrays.asList("A"), Arrays.asList("A"));

        // 3. Only-Left
        TreeNode onlyLeft = new TreeNode('A');
        onlyLeft.left = new TreeNode('B');
        runTest("Only-Left", onlyLeft, 
                Arrays.asList("A", "B"), Arrays.asList("B", "A"), Arrays.asList("B", "A"), Arrays.asList("A", "B"));

        // 4. Only-Right
        TreeNode onlyRight = new TreeNode('A');
        onlyRight.right = new TreeNode('C');
        runTest("Only-Right", onlyRight, 
                Arrays.asList("A", "C"), Arrays.asList("A", "C"), Arrays.asList("C", "A"), Arrays.asList("A", "C"));

        // 5. Complete Tree
        TreeNode complete = new TreeNode('1');
        complete.left = new TreeNode('2');
        complete.right = new TreeNode('3');
        runTest("Complete Tree", complete, 
                Arrays.asList("1", "2", "3"), Arrays.asList("2", "1", "3"), Arrays.asList("2", "3", "1"), Arrays.asList("1", "2", "3"));

        // 6. Irregular Tree
        TreeNode irregular = new TreeNode('X');
        irregular.left = new TreeNode('Y');
        irregular.left.right = new TreeNode('Z');
        runTest("Irregular Tree", irregular, 
                Arrays.asList("X", "Y", "Z"), Arrays.asList("Y", "Z", "X"), Arrays.asList("Z", "Y", "X"), Arrays.asList("X", "Y", "Z"));
    }
}
