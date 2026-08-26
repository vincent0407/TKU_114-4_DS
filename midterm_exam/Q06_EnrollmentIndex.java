package midterm_exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Q06_EnrollmentIndex {

    // 1. 類別內部使用 Map<String, Set<String>> 管理 courseCode 與 studentId
    private final Map<String, java.util.Set<String>> courseMap = new HashMap<>();

    // 2. Code 或 id 為 null、blank 時，enroll() 與 drop() 回傳 false
    public boolean enroll(String courseCode, String studentId) {
        if (courseCode == null || courseCode.isBlank() || studentId == null || studentId.isBlank()) {
            return false;
        }

        courseMap.putIfAbsent(courseCode, new HashSet<>());
        java.util.Set<String> students = courseMap.get(courseCode);

        // 3. 重複選課不增加資料並回傳 false
        if (students.contains(studentId)) {
            return false;
        }

        students.add(studentId);
        return true;
    }

    public boolean drop(String courseCode, String studentId) {
        if (courseCode == null || courseCode.isBlank() || studentId == null || studentId.isBlank()) {
            return false;
        }

        if (!courseMap.containsKey(courseCode)) {
            return false;
        }

        java.util.Set<String> students = courseMap.get(courseCode);
        boolean removed = students.remove(studentId);

        // 4. Drop 成功後若該課程已無人選課，從 Map 移除該 courseCode
        if (removed && students.isEmpty()) {
            courseMap.remove(courseCode);
        }

        return removed;
    }

    public int courseSize(String courseCode) {
        if (courseCode == null || !courseMap.containsKey(courseCode)) {
            return 0;
        }
        return courseMap.get(courseCode).size();
    }

    // 5. & 6. 依字典順序輸出且不暴露內部 collection
    public List<String> studentsOf(String courseCode) {
        if (courseCode == null || !courseMap.containsKey(courseCode)) {
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>(courseMap.get(courseCode));
        Collections.sort(result); // 字典順序排序
        return result;
    }

    // 雙向查詢：找出該學生選修的所有課程
    public List<String> coursesOf(String studentId) {
        List<String> result = new ArrayList<>();
        if (studentId == null || studentId.isBlank()) {
            return result;
        }

        for (Map.Entry<String, java.util.Set<String>> entry : courseMap.entrySet()) {
            if (entry.getValue().contains(studentId)) {
                result.add(entry.getKey());
            }
        }

        Collections.sort(result); // 字典順序排序
        return result;
    }

    // 回傳每門課的選課人數統計 Map（按字典順序排序的 Map）
    public Map<String, Integer> summary() {
        Map<String, Integer> resultMap = new java.util.TreeMap<>(); // TreeMap 自動按 key 字典順序排序
        for (Map.Entry<String, java.util.Set<String>> entry : courseMap.entrySet()) {
            resultMap.put(entry.getKey(), entry.getValue().size());
        }
        return resultMap;
    }

    // 測試用 main 方法
    public static void main(String[] args) {
        Q06_EnrollmentIndex index = new Q06_EnrollmentIndex();
        index.enroll("DS", "S02");
        index.enroll("DS", "S01");
        index.enroll("JAVA", "S01");

        System.out.println(index.studentsOf("DS"));
        System.out.println(index.coursesOf("S01"));
        System.out.println(index.summary());
    }
}
