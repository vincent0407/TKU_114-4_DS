// 檔案名稱：EnrollmentSetsSystem.java

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Enrollment {
    private String studentId;
    private String courseCode;

    public Enrollment(String studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    // 正確 override equals()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return Objects.equals(studentId, that.studentId) &&
               Objects.equals(courseCode, that.courseCode);
    }

    // 正確 override hashCode()
    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseCode);
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "studentId='" + studentId + '\'' +
                ", courseCode='" + courseCode + '\'' +
                '}';
    }
}

public class EnrollmentSetSystem {

    public static void main(String[] args) {
        Set<Enrollment> enrollmentSet = new HashSet<>();

        System.out.println("=== 1. 新增報名測試 (輸出 boolean 描述) ===");
        
        // 1. 同一人加入不同課程 (應成功)
        boolean add1 = enrollmentSet.add(new Enrollment("S001", "CS101"));
        boolean add2 = enrollmentSet.add(new Enrollment("S001", "CS102"));
        System.out.println("S001 報名 CS101: " + add1); // true
        System.out.println("S001 報名 CS102: " + add2); // true

        // 2. 同一人重複加入同一課程 (應失敗)
        boolean add3 = enrollmentSet.add(new Enrollment("S001", "CS101"));
        System.out.println("S001 重複報名 CS101: " + add3); // false

        System.out.println("\n當前所有報名紀錄: " + enrollmentSet);

        System.out.println("\n=== 2. 使用新建但身份相同的 Object 測試 contains() 與 remove() ===");
        // 新建一個相同身份的物件 (記憶體位址不同，但 studentId 與 courseCode 相同)
        Enrollment sameEnrollment = new Enrollment("S001", "CS101");

        // 測試 contains()
        boolean containsResult = enrollmentSet.contains(sameEnrollment);
        System.out.println("contains(S001, CS101) 結果: " + containsResult); // true

        // 測試 remove() 及其輸出 boolean
        boolean removeResult = enrollmentSet.remove(sameEnrollment);
        System.out.println("remove(S001, CS101) 結果: " + removeResult);     // true

        // 驗證移除後的集合
        System.out.println("\n移除後的報名紀錄: " + enrollmentSet);
    }
}
