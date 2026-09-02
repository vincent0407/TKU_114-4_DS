import java.util.*;

public class DirectedReachability {
    public static boolean isReachable(Map<String, List<String>> graph, String from, String to) {
        if (graph == null || !graph.containsKey(from) || !graph.containsKey(to)) {
            return false;
        }
        if (from.equals(to)) return true;

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.offer(from);
        visited.add(from);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(to)) return true;

            for (String neighbor : graph.getOrDefault(current, Collections.emptyList())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        return false;
    }

    public static Map<String, Boolean> batchCheck(Map<String, List<String>> graph, List<String[]> queries) {
        Map<String, Boolean> results = new LinkedHashMap<>();
        if (queries == null) return results;

        for (String[] pair : queries) {
            String key = pair[0] + " -> " + pair[1];
            results.put(key, isReachable(graph, pair[0], pair[1]));
        }
        return results;
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", Arrays.asList("B"));
        graph.put("B", Arrays.asList("C"));
        graph.put("C", Collections.emptyList());

        List<String[]> queries = Arrays.asList(
            new String[]{"A", "C"},
            new String[]{"C", "A"},
            new String[]{"A", "X"}
        );

        System.out.println("--- 3. DirectedReachability (測試結果) ---");
        System.out.println(batchCheck(graph, queries));
    }
}
