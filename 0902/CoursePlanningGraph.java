import java.util.*;

public class CoursePlanningGraph {
    private Map<String, List<String>> graph = new HashMap<>();

    public void addPrerequisite(String course, String prereq) {
        graph.putIfAbsent(prereq, new ArrayList<>());
        graph.putIfAbsent(course, new ArrayList<>());
        graph.get(prereq).add(course); // prereq -> course (修完 prereq 才能修 course)
    }

    public Set<String> getAffectedCourses(String course) {
        Set<String> affected = new LinkedHashSet<>();
        if (!graph.containsKey(course)) return affected;

        dfs(course, affected);
        affected.remove(course); // 移除自身，只列出受影響的後續課程
        return affected;
    }

    private void dfs(String curr, Set<String> visited) {
        visited.add(curr);
        for (String next : graph.getOrDefault(curr, Collections.emptyList())) {
            if (!visited.contains(next)) {
                dfs(next, visited);
            }
        }
    }

    public static void main(String[] args) {
        CoursePlanningGraph cpg = new CoursePlanningGraph();
        cpg.addPrerequisite("CS102", "CS101");
        cpg.addPrerequisite("CS201", "CS102");
        cpg.addPrerequisite("CS301", "CS201");

        System.out.println("--- 3. CoursePlanningGraph (一般案例) ---");
        System.out.println("CS101 變更後受影響的課程: " + cpg.getAffectedCourses("CS101"));

        System.out.println("--- 3. CoursePlanningGraph (邊界案例: 無後續課程/不存在) ---");
        System.out.println("CS301 變更後受影響的課程: " + cpg.getAffectedCourses("CS301"));
        System.out.println("X99 變更後受影響的課程: " + cpg.getAffectedCourses("X99"));
    }
}
