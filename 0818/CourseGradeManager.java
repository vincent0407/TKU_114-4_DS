class CourseGrade {
    private String studentId;
    private String name;
    private double assignment; // 平時
    private double midterm;    // 期中
    private double finalExam;  // 期末
    private double attendance; // 出席

    public CourseGrade(String studentId, String name, double assignment, double midterm, double finalExam, double attendance) {
        this.studentId = studentId;
        this.name = name;
        this.assignment = clamp(assignment);
        this.midterm = clamp(midterm);
        this.finalExam = clamp(finalExam);
        this.attendance = clamp(attendance);
    }

    private double clamp(double score) {
        if (score < 0) return 0;
        if (score > 100) return 100;
        return score;
    }

    // 計算總分：平時 50%、期中 20%、期末 20%、出席 10%
    public double calculateFinalScore() {
        return (assignment * 0.5) + (midterm * 0.2) + (finalExam * 0.2) + (attendance * 0.1);
    }

    // 等級判定
    public String getLevel() {
        double score = calculateFinalScore();
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }

    public String getName() { return name; }

    @Override
    public String toString() {
        return "學號: " + studentId + ", 姓名: " + name + 
               ", 總分: " + String.format("%.2f", calculateFinalScore()) + 
               ", 等級: " + getLevel();
    }
}

public class CourseGradeManager {
    public static void main(String[] args) {
        CourseGrade[] grades = {
            new CourseGrade("S01", "Alice", 90, 85, 88, 100),
            new CourseGrade("S02", "Bob", 50, 40, 55, 60),
            new CourseGrade("S03", "Charlie", 70, 75, 80, 90),
            new CourseGrade("S04", "David", 30, 20, 40, 50),
            new CourseGrade("S05", "Emma", 95, 92, 96, 90)
        };

        System.out.println("=== 所有學生成績彙整 ===");
        double totalSum = 0;
        CourseGrade topStudent = grades[0];

        for (CourseGrade g : grades) {
            System.out.println(g);
            double score = g.calculateFinalScore();
            totalSum += score;
            if (score > topStudent.calculateFinalScore()) {
                topStudent = g;
            }
        }

        System.out.println("\n=== 統計數據 ===");
        System.out.println("班級平均分數: " + String.format("%.2f", (totalSum / grades.length)));
        System.out.println("最高分學生: " + topStudent.getName() + " (" + String.format("%.2f", topStudent.calculateFinalScore()) + "分)");

        System.out.println("\n=== 不合格名單 (等級 F / <60分) ===");
        for (CourseGrade g : grades) {
            if (g.calculateFinalScore() < 60) {
                System.out.println(g.getName() + " - " + String.format("%.2f", g.calculateFinalScore()) + "分");
            }
        }
    }
}
