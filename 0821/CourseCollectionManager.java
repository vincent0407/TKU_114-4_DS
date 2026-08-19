// 檔案名稱：CourseCollectionManager.java

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

// 學生紀錄類別
class StudentRecord {
    private String studentId;
    private String name;
    private int score;
    private Set<String> tags; // 課程標籤 (Set 確保不重複)

    public StudentRecord(String studentId, String name, int score, Set<String> tags) {
        this.studentId = studentId;
        this.name = name;
        this.score = score;
        // 清理並過濾空白 tag
        this.tags = new HashSet<>();
        if (tags != null) {
            for (String tag : tags) {
                if (tag != null && !tag.trim().isEmpty()) {
                    this.tags.add(tag.trim());
                }
            }
        }
    }

    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public Set<String> getTags() { return tags; }

    // 依分數轉換為等級 (A, B, C, D, F)
    public String getGrade() {
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }

    @Override
    public String toString() {
        return String.format("StudentRecord[ID=%s, Name=%-6s, Score=%3d, Grade=%s, Tags=%s]",
                studentId, name, score, getGrade(), tags);
    }
}

public class CourseCollectionManager {

    // 三個內部集合維護系統資料
    private List<StudentRecord> studentList = new ArrayList<>();
    private Set<String> studentIdSet = new HashSet<>();
    private Map<String, StudentRecord> studentMap = new HashMap<>();

    // 新增學生紀錄 (同步維護 List, Set, Map)
    public boolean addStudent(StudentRecord record) {
        if (record == null || studentIdSet.contains(record.getStudentId())) {
            return false; // 重複學號不加入
        }
        studentList.add(record);
        studentIdSet.add(record.getStudentId());
        studentMap.put(record.getStudentId(), record);
        return true;
    }

    // 1. updateScore(String studentId, int score)
    public boolean updateScore(String studentId, int score) {
        StudentRecord record = studentMap.get(studentId);
        if (record != null) {
            record.setScore(score); // 物件屬性修改後，List 與 Map 指向同物件皆會更新
            return true;
        }
        return false;
    }

    // 2. findByTag(String tag)
    public List<StudentRecord> findByTag(String tag) {
        List<StudentRecord> result = new ArrayList<>();
        if (tag == null || tag.trim().isEmpty()) return result;

        String cleanTag = tag.trim();
        for (StudentRecord record : studentList) {
            if (record.getTags().contains(cleanTag)) {
                result.add(record);
            }
        }
        return result;
    }

    // 3. scoreDistribution() 回傳 Map<String, Integer> 統計 A, B, C, D, F
    public Map<String, Integer> scoreDistribution() {
        Map<String, Integer> distribution = new LinkedHashMap<>();
        // 預設填入 0 次數
        distribution.put("A", 0);
        distribution.put("B", 0);
        distribution.put("C", 0);
        distribution.put("D", 0);
        distribution.put("F", 0);

        for (StudentRecord record : studentList) {
            String grade = record.getGrade();
            distribution.put(grade, distribution.get(grade) + 1);
        }
        return distribution;
    }

    // 4. top(int count) 回傳排名前 count 名 (count 大於人數時回傳所有資料)
    public List<StudentRecord> top(int count) {
        if (count <= 0) return new ArrayList<>();

        // 依分數降冪排序，同分時依 ID 排序
        return studentList.stream()
                .sorted(Comparator.comparingInt(StudentRecord::getScore).reversed()
                        .thenComparing(StudentRecord::getStudentId))
                .limit(count)
                .collect(Collectors.toList());
    }

    // 5. removeBelow(int minimum) 後，List、Set 與 Map 必須保持一致
    public void removeBelow(int minimum) {
        // 使用 Iterator 安全從 List 中移除，並同步更新 Set 與 Map
        Iterator<StudentRecord> iterator = studentList.iterator();
        while (iterator.hasNext()) {
            StudentRecord record = iterator.next();
            if (record.getScore() < minimum) {
                studentIdSet.remove(record.getStudentId()); // 移除 Set 中的學號
                studentMap.remove(record.getStudentId());   // 移除 Map 中的 Key
                iterator.remove();                          // 移除 List 中的物件
            }
        }
    }

    // 顯示當前所有集合數量以驗證一致性
    public void printStatus() {
        System.out.println("當前數量 -> List: " + studentList.size() 
                + ", Set: " + studentIdSet.size() 
                + ", Map: " + studentMap.size());
    }

    // 測試主程式
    public static void main(String[] args) {
        CourseCollectionManager manager = new CourseCollectionManager();

        // 至少 6 筆測試資料，包含同分與空白/重複 tag
        manager.addStudent(new StudentRecord("S001", "Alice", 85, Set.of("Java", "DS", "")));
        manager.addStudent(new StudentRecord("S002", "Bob", 92, Set.of("Python", "  ", "DS")));
        manager.addStudent(new StudentRecord("S003", "Charlie", 58, Set.of("Java")));
        manager.addStudent(new StudentRecord("S004", "David", 85, Set.of("Math", "Java"))); // 同分 (85)
        manager.addStudent(new StudentRecord("S005", "Eve", 45, Set.of("Python")));
        manager.addStudent(new StudentRecord("S006", "Frank", 73, Set.of("Math")));

        System.out.println("=== 初始狀態驗證 ===");
        manager.printStatus();

        // 1. 測試 updateScore
        System.out.println("\n=== 1. 測試 updateScore ===");
        System.out.println("更新 S003 分數至 65: " + manager.updateScore("S003", 65));

        // 2. 測試 findByTag
        System.out.println("\n=== 2. 測試 findByTag('Java') ===");
        List<StudentRecord> javaStudents = manager.findByTag("Java");
        javaStudents.forEach(System.out::println);

        // 3. 測試 scoreDistribution
        System.out.println("\n=== 3. 測試 scoreDistribution ===");
        Map<String, Integer> dist = manager.scoreDistribution();
        dist.forEach((grade, count) -> System.out.println("等級 " + grade + ": " + count + " 人"));

        // 4. 測試 top
        System.out.println("\n=== 4. 測試 top(3) 排名前 3 名 ===");
        List<StudentRecord> top3 = manager.top(3);
        top3.forEach(System.out::println);

        // 5. 測試 removeBelow 並驗證一致性
        System.out.println("\n=== 5. 測試 removeBelow(60) ===");
        System.out.println("清理前狀態：");
        manager.printStatus();
        
        manager.removeBelow(60); // 移除低於 60 分者 (S005)

        System.out.println("清理後狀態 (應維持一致)：");
        manager.printStatus();
    }
}
