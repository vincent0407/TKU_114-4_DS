import java.util.*;

public class NetworkComponents {
    public static class ComponentReport {
        public List<Set<String>> components = new ArrayList<>();
        public int count = 0;
        public Set<String> maxComponent = new HashSet<>();

        @Override
        public String toString() {
            return "Total Components: " + count + "\nComponents: " + components + "\nLargest Component: " + maxComponent;
        }
    }

    public static ComponentReport analyzeComponents(Map<String, List<String>> graph) {
        ComponentReport report = new ComponentReport();
        if (graph == null || graph.isEmpty()) return report;

        Set<String> visited = new HashSet<>();

        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                Set<String> component = new HashSet<>();
                bfs(graph, node, visited, component);
                report.components.add(component);
                if (component.size() > report.maxComponent.size()) {
                    report.maxComponent = component;
                }
            }
        }
        report.count = report.components.size();
        return report;
    }

    private static void bfs(Map<String, List<String>> graph, String start, Set<String> visited, Set<String> component) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            component.add(curr);
            for (String neighbor : graph.getOrDefault(curr, Collections.emptyList())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", Arrays.asList("B"));
        graph.put("B", Arrays.asList("A"));
        graph.put("C", Arrays.asList("D"));
        graph.put("D", Arrays.asList("C"));
        graph.put("E", Collections.emptyList());

        System.out.println("--- 5. NetworkComponents (一般案例) ---");
        System.out.println(analyzeComponents(graph));

        System.out.println("\n--- 5. NetworkComponents (邊界案例: 空圖) ---");
        System.out.println(analyzeComponents(new HashMap<>()));
    }
}
