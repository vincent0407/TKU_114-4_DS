// 抽象父類別 Employee
abstract class Employee {
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // 抽象方法：計算薪資
    public abstract double calculatePay();
}

// 月薪員工
class SalariedEmployee extends Employee {
    private double monthlySalary;

    public SalariedEmployee(String name, double monthlySalary) {
        super(name);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculatePay() {
        return monthlySalary;
    }
}

// 時薪員工
class HourlyEmployee extends Employee {
    private double hourlyRate;
    private int hoursWorked;

    public HourlyEmployee(String name, double hourlyRate, int hoursWorked) {
        super(name);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculatePay() {
        return hourlyRate * hoursWorked;
    }
}

// 業務員工（底薪 + 業績抽成）
class CommissionEmployee extends Employee {
    private double baseSalary;
    private double salesAmount;
    private double commissionRate;

    public CommissionEmployee(String name, double baseSalary, double salesAmount, double commissionRate) {
        super(name);
        this.baseSalary = baseSalary;
        this.salesAmount = salesAmount;
        this.commissionRate = commissionRate;
    }

    @Override
    public double calculatePay() {
        return baseSalary + (salesAmount * commissionRate);
    }
}

public class PayrollPolymorphismSystem {
    public static void main(String[] args) {
        // 使用 Employee[] 保存至少三個不同子類別物件
        Employee[] employees = new Employee[] {
            new SalariedEmployee("Alice", 50000),
            new HourlyEmployee("Bob", 200, 160),
            new CommissionEmployee("Charlie", 30000, 200000, 0.1)
        };

        double totalPay = 0;
        Employee highestPaid = employees[0];

        for (Employee emp : employees) {
            double pay = emp.calculatePay();
            System.out.println("員工: " + emp.getName() + " | 薪資: " + pay);
            
            totalPay += pay;
            if (pay > highestPaid.calculatePay()) {
                highestPaid = emp;
            }
        }

        System.out.println("-----------------------------------");
        System.out.println("薪資總額: " + totalPay);
        System.out.println("最高薪資員工: " + highestPaid.getName() + " (" + highestPaid.calculatePay() + " 元)");
    }
}
