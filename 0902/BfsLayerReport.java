import java.util.*;

public class BfsLayerReport {
    public static Map<String, Integer> getShortestEdgeCounts(Map<String, List<String>> graph, String start) {
        Map<String, Integer> distances = new LinkedHashMap<>();
        if (graph == null || !graph.containsKey(start)) {
            return distances;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        distances.put(start, 0);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            int currentDist = distances.get(current);

            List<String> neighbors = graph.getOrDefault(current, Collections.emptyList());
            for (String neighbor : neighbors) {
                if (!distances.containsKey(neighbor)) {
                    distances.put(neighbor, currentDist + 1);
                    queue.offer(neighbor);
                }
            }
        }
        return distances;
    }

    public static void main(String[] args) {
        // 一般案例
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", Arrays.asList("B", "C"));
        graph.put("B", Arrays.asList("D"));
        graph.put("C", Arrays.asList("D", "E"));
        graph.put("D", Collections.emptyList());
        graph.put("E", Collections.emptyList());

        System.out.println("--- 1. BfsLayerReport (一般案例) ---");
        System.out.println(getShortestEdgeCounts(graph, "A"));

        // 邊界案例
        System.out.println("--- 1. BfsLayerReport (邊界案例) ---");
        System.out.println(getShortestEdgeCounts(new HashMap<>(), "X"));
    }
}
