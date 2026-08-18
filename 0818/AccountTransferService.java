class Account {
    private String accountNumber;
    private int balance;

    public Account(String accountNumber, int balance) {
        this.accountNumber = accountNumber;
        this.balance = (balance < 0) ? 0 : balance;
    }

    public String getAccountNumber() { return accountNumber; }
    public int getBalance() { return balance; }

    public void withdraw(int amount) {
        this.balance -= amount;
    }

    public void deposit(int amount) {
        this.balance += amount;
    }

    @Override
    public String toString() {
        return "帳號: " + accountNumber + ", 餘額: " + balance;
    }
}

class TransferService {
    public static boolean transfer(Account source, Account target, int amount) {
        // 1. 驗證來源與目標不是 null
        if (source == null || target == null) {
            return false;
        }

        // 2. 驗證來源與目標不是同一個物件
        if (source == target) {
            return false;
        }

        // 3. 驗證金額大於 0 且來源餘額足夠
        if (amount <= 0 || source.getBalance() < amount) {
            return false;
        }

        // 驗證全通過才進行扣款與存入（避免任一失敗時變更狀態）
        source.withdraw(amount);
        target.deposit(amount);
        return true;
    }
}

public class AccountTransferService {
    public static void main(String[] args) {
        Account acc1 = new Account("A001", 1000);
        Account acc2 = new Account("A002", 500);

        System.out.println("=== 1. 成功轉帳測試 (500) ===");
        System.out.println("結果: " + TransferService.transfer(acc1, acc2, 500));
        System.out.println(acc1);
        System.out.println(acc2);

        System.out.println("\n=== 2. 餘額不足轉帳測試 (1000) ===");
        System.out.println("結果: " + TransferService.transfer(acc1, acc2, 1000));
        System.out.println(acc1);

        System.out.println("\n=== 3. 同帳戶轉帳測試 ===");
        System.out.println("結果: " + TransferService.transfer(acc1, acc1, 100));

        System.out.println("\n=== 4. null 目標轉帳測試 ===");
        System.out.println("結果: " + TransferService.transfer(acc1, null, 100));
    }
}
