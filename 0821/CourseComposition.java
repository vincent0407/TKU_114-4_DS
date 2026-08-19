class Instructor {
    private String id;
    private String name;

    public Instructor(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

class Course {
    private String courseCode;
    private String title;
    private Instructor instructor; // Composition: 引用 Instructor 物件

    public Course(String courseCode, String title, Instructor instructor) {
        this.courseCode = courseCode;
        this.title = title;
        this.instructor = instructor;
    }

    public String summary() {
        // 透過 composition 取得授課者名稱，不重複保存 instructorName
        return "課程代碼: " + courseCode + " | 課程名稱: " + title + 
               " | 授課教師: " + (instructor != null ? instructor.getName() : "無");
    }
}

public class CourseComposition {
    public static void main(String[] args) {
        // 建立一個 Instructor 物件
        Instructor teacher = new Instructor("T001", "張教授");

        // 建立至少兩門課，共用同一個 instructor
        Course course1 = new Course("CS101", "物件導向程式設計", teacher);
        Course course2 = new Course("CS102", "資料結構", teacher);

        // 印出課程完整資訊
        System.out.println(course1.summary());
        System.out.println(course2.summary());
    }
}
