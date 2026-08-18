// 抽象父類別 EmployeeBase
abstract class EmployeeBase {
    protected String id;
    protected String name;

    public EmployeeBase(String id, String name) {
        this.id = id;
        this.name = name;
        System.out.println("執行建構子: " + this.getClass().getSimpleName() + " (EmployeeBase)");
    }

    // 宣告抽象方法由子類別 override
    public abstract double calculatePay();
}

// 全職員工子類別
class FullTimeEmployee extends EmployeeBase {
    private double monthlySalary;

    public FullTimeEmployee(String id, String name, double monthlySalary) {
        super(id, name); // 使用 super(...) 呼叫父類別建構子
        // 邊界條件檢查：負數薪資轉為 0
        this.monthlySalary = Math.max(0, monthlySalary);
        System.out.println("執行建構子: " + this.getClass().getSimpleName() + " (FullTimeEmployee)");
    }

    @Override
    public double calculatePay() {
        return monthlySalary;
    }
}

// 兼職員工子類別
class PartTimeEmployee extends EmployeeBase {
    private double hourlyRate;
    private int hoursWorked;

    public PartTimeEmployee(String id, String name, double hourlyRate, int hoursWorked) {
        super(id, name); // 使用 super(...) 呼叫父類別建構子
        // 邊界條件檢查：負數時薪或時數轉為 0
        this.hourlyRate = Math.max(0, hourlyRate);
        this.hoursWorked = Math.max(0, hoursWorked);
        System.out.println("執行建構子: " + this.getClass().getSimpleName() + " (PartTimeEmployee)");
    }

    @Override
    public double calculatePay() {
        return hourlyRate * hoursWorked;
    }
}

public class EmployeeConstructorChain {
    public static void main(String[] args) {
        System.out.println("=== 建立 FullTimeEmployee 物件 ===");
        EmployeeBase ft = new FullTimeEmployee("F001", "Alice", 50000);
        System.out.println("計算薪資: " + ft.calculatePay());

        System.out.println("\n=== 建立 PartTimeEmployee 物件（包含負數邊界測試） ===");
        EmployeeBase pt = new PartTimeEmployee("P001", "Bob", -150, -20); // 負數轉為 0
        System.out.println("計算薪資: " + pt.calculatePay());
    }
}
