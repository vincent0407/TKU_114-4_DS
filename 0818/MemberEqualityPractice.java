import java.util.Objects;

class LibraryMember {
    private String memberId;
    private String name;
    private String email;

    public LibraryMember(String memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
    }

    // 1. Override toString() 輸出所有欄位
    @Override
    public String toString() {
        return "會員編號: " + memberId + ", 姓名: " + name + ", Email: " + email;
    }

    // 2. Override equals()，只使用 memberId 判斷身分
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false; // 安全邊界條件：與 null 比較回傳 false，防止 ClassCastException
        LibraryMember other = (LibraryMember) obj;
        return Objects.equals(this.memberId, other.memberId);
    }

    // 2. Override hashCode()，配合 equals 只使用 memberId 計算
    @Override
    public int hashCode() {
        return Objects.hash(memberId);
    }
}

public class MemberEqualityPractice {
    public static void main(String[] args) {
        // 3. 建立兩個 id 相同但 email 不同的物件
        LibraryMember m1 = new LibraryMember("M001", "張三", "zhang3@example.com");
        LibraryMember m2 = new LibraryMember("M001", "張三", "zhang3_new@example.com");

        System.out.println("m1 資訊: " + m1);
        System.out.println("m2 資訊: " + m2);
        System.out.println("----------------------------------------");

        // 4. 輸出 == 與 equals() 的比較結果
        System.out.println("m1 == m2 比較結果 (記憶體位址): " + (m1 == m2));
        System.out.println("m1.equals(m2) 比較結果 (memberId): " + m1.equals(m2));

        // 邊界條件驗證：與 null 比較必須回傳 false，不可發生例外
        System.out.println("m1.equals(null) 比較結果: " + m1.equals(null));
    }
}
