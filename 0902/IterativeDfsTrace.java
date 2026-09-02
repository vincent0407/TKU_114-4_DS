import java.util.*;

public class IterativeDfsTrace {
    public static void traceDfs(Map<String, List<String>> graph, String start) {
        System.out.println("=== DFS Trace Start ===");
        if (graph == null || !graph.containsKey(start)) {
            System.out.println("圖為空或起點不存在！");
            return;
        }

        Stack<String> stack = new Stack<>();
        Set<String> visited = new LinkedHashSet<>();

        stack.push(start);
        System.out.println("Push: " + start + " | Stack: " + stack + " | Visited: " + visited);

        while (!stack.isEmpty()) {
            String current = stack.pop();
            System.out.println("Pop:  " + current + " | Stack: " + stack + " | Visited: " + visited);

            if (!visited.contains(current)) {
                visited.add(current);

                List<String> neighbors = graph.getOrDefault(current, Collections.emptyList());
                // 倒序入棧以保持正向訪問順序
                for (int i = neighbors.size() - 1; i >= 0; i--) {
                    String neighbor = neighbors.get(i);
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                        System.out.println("Push: " + neighbor + " | Stack: " + stack + " | Visited: " + visited);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", Arrays.asList("B", "C"));
        graph.put("B", Arrays.asList("D"));
        graph.put("C", Collections.emptyList());
        graph.put("D", Collections.emptyList());

        System.out.println("--- 2. IterativeDfsTrace (一般案例) ---");
        traceDfs(graph, "A");

        System.out.println("\n--- 2. IterativeDfsTrace (邊界案例) ---");
        traceDfs(new HashMap<>(), "A");
    }
}
