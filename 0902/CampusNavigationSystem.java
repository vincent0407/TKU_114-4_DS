import java.util.*;

public class CampusNavigationSystem {
    private Map<String, String> locations = new HashMap<>(); // ID -> Location Name
    private Map<String, List<String>> adjacencyList = new HashMap<>(); // ID -> Neighbor IDs

    public void addLocation(String id, String name) {
        locations.put(id, name);
        adjacencyList.putIfAbsent(id, new ArrayList<>());
    }

    public void addRoad(String id1, String id2) {
        if (locations.containsKey(id1) && locations.containsKey(id2)) {
            adjacencyList.get(id1).add(id2);
            adjacencyList.get(id2).add(id1);
        }
    }

    public List<String> findShortestPath(String startId, String endId) {
        if (!locations.containsKey(startId) || !locations.containsKey(endId)) {
            return Collections.emptyList();
        }

        Queue<String> queue = new LinkedList<>();
        Map<String, String> parentMap = new HashMap<>();
        Set<String> visited = new HashSet<>();

        queue.offer(startId);
        visited.add(startId);

        boolean found = false;
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            if (curr.equals(endId)) {
                found = true;
                break;
            }

            for (String neighbor : adjacencyList.getOrDefault(curr, Collections.emptyList())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parentMap.put(neighbor, curr);
                    queue.offer(neighbor);
                }
            }
        }

        if (!found) return Collections.emptyList();

        LinkedList<String> pathNames = new LinkedList<>();
        String curr = endId;
        while (curr != null) {
            pathNames.addFirst(locations.get(curr));
            curr = parentMap.get(curr);
        }
        return pathNames;
    }

    public static void main(String[] args) {
        CampusNavigationSystem nav = new CampusNavigationSystem();
        nav.addLocation("L1", "校門");
        nav.addLocation("L2", "圖書館");
        nav.addLocation("L3", "教學大樓");
        nav.addRoad("L1", "L2");
        nav.addRoad("L2", "L3");

        System.out.println("--- 1. CampusNavigationSystem (一般案例) ---");
        System.out.println("最短路徑: " + nav.findShortestPath("L1", "L3"));

        System.out.println("--- 1. CampusNavigationSystem (邊界案例: 不存在地點) ---");
        System.out.println("最短路徑: " + nav.findShortestPath("L1", "L99"));
    }
}
