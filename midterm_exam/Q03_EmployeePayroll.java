package midterm_exam;

import java.util.List;

public class Q03_EmployeePayroll {

    // 抽象父類別 Employee
    public static abstract class Employee {
        protected String id;
        protected String name;

        public Employee(String id, String name) {
            // 1. id, name 不得為 null 或 blank
            if (id == null || id.isBlank() || name == null || name.isBlank()) {
                throw new IllegalArgumentException("id and name cannot be null or blank");
            }
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public abstract int monthlyPay();

        // 5. summary() 回傳 id|name|monthlyPay，使用多型呼叫 monthlyPay()
        public String summary() {
            return id + "|" + name + "|" + monthlyPay();
        }
    }

    // 月薪員工 SalariedEmployee
    public static class SalariedEmployee extends Employee {
        private final int salary;

        public SalariedEmployee(String id, String name, int salary) {
            super(id, name);
            // 2. salary 小於 0 時以 0 計算
            this.salary = Math.max(0, salary);
        }

        @Override
        public int monthlyPay() {
            return salary;
        }
    }

    // 時薪員工 HourlyEmployee
    public static class HourlyEmployee extends Employee {
        private final int hours;
        private final int hourlyRate;

        public HourlyEmployee(String id, String name, int hours, int hourlyRate) {
            super(id, name);
            // 3. hours 與 hourlyRate 小於 0 時各以 0 計算
            this.hours = Math.max(0, hours);
            this.hourlyRate = Math.max(0, hourlyRate);
        }

        @Override
        public int monthlyPay() {
            // 4. 160小時內按原時薪；超過160小時的部分按 1.5 倍計算，轉為整數
            if (hours <= 160) {
                return hours * hourlyRate;
            } else {
                int regularPay = 160 * hourlyRate;
                int overtimeHours = hours - 160;
                double overtimePay = overtimeHours * hourlyRate * 1.5;
                return regularPay + (int) overtimePay;
            }
        }
    }

    // 6. 加總所有非 null Employee；List 為 null 時回傳 0
    public static int totalPayroll(List<Employee> employees) {
        if (employees == null) {
            return 0;
        }
        int total = 0;
        for (Employee emp : employees) {
            if (emp != null) {
                total += emp.monthlyPay();
            }
        }
        return total;
    }

    // 測試用 main 方法
    public static void main(String[] args) {
        var employees = java.util.List.of(
            new Q03_EmployeePayroll.SalariedEmployee("E1", "Amy", 50000),
            new Q03_EmployeePayroll.HourlyEmployee("E2", "Bo", 170, 200)
        );

        System.out.println(employees.get(0).summary());
        System.out.println(employees.get(1).summary());
        System.out.println(Q03_EmployeePayroll.totalPayroll(employees));
    }
}
