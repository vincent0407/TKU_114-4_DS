import java.util.*;

public class MetroTransferPath {
    public static class PathResult {
        public List<String> path;
        public int edgeCount;

        public PathResult(List<String> path, int edgeCount) {
            this.path = path;
            this.edgeCount = edgeCount;
        }

        @Override
        public String toString() {
            return "Path: " + path + ", Edge Count: " + edgeCount;
        }
    }

    public static PathResult findMinTransferPath(Map<String, List<String>> graph, String start, String end) {
        if (graph == null || !graph.containsKey(start) || !graph.containsKey(end)) {
            return new PathResult(Collections.emptyList(), -1);
        }

        Queue<String> queue = new LinkedList<>();
        Map<String, String> parentMap = new HashMap<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        boolean found = false;
        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(end)) {
                found = true;
                break;
            }

            for (String neighbor : graph.getOrDefault(current, Collections.emptyList())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                    queue.offer(neighbor);
                }
            }
        }

        if (!found) return new PathResult(Collections.emptyList(), -1);

        LinkedList<String> path = new LinkedList<>();
        String curr = end;
        while (curr != null) {
            path.addFirst(curr);
            curr = parentMap.get(curr);
        }

        return new PathResult(path, path.size() - 1);
    }

    public static void main(String[] args) {
        Map<String, List<String>> metro = new HashMap<>();
        metro.put("R10", Arrays.asList("R11", "G10"));
        metro.put("R11", Arrays.asList("R10", "R12"));
        metro.put("R12", Arrays.asList("R11"));
        metro.put("G10", Arrays.asList("R10"));

        System.out.println("--- 4. MetroTransferPath (一般案例) ---");
        System.out.println(findMinTransferPath(metro, "R10", "R12"));

        System.out.println("--- 4. MetroTransferPath (邊界案例: 無法到達) ---");
        System.out.println(findMinTransferPath(metro, "R10", "Unknown"));
    }
}
