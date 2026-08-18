class DigitalWallet {
    private String walletId;
    private String owner;
    private double balance;
    private int transactionCount;

    public DigitalWallet(String walletId, String owner, double initialBalance) {
        this.walletId = walletId;
        this.owner = owner;
        this.balance = (initialBalance < 0) ? 0 : initialBalance;
        this.transactionCount = 0;
    }

    // 儲值
    public boolean deposit(double amount) {
        if (amount <= 0) return false;
        this.balance += amount;
        this.transactionCount++;
        return true;
    }

    // 付款
    public boolean pay(double amount) {
        if (amount <= 0 || amount > this.balance) return false;
        this.balance -= amount;
        this.transactionCount++;
        return true;
    }

    // 退款
    public boolean refund(double amount) {
        if (amount <= 0) return false;
        this.balance += amount;
        this.transactionCount++;
        return true;
    }

    @Override
    public String toString() {
        return "錢包ID: " + walletId + ", 持有者: " + owner + ", 餘額: " + balance + ", 總交易次數: " + transactionCount;
    }
}

public class DigitalWalletSystem {
    public static void main(String[] args) {
        DigitalWallet wallet = new DigitalWallet("W101", "Alex", 1000);
        System.out.println("初始狀態: " + wallet);

        System.out.println("正常儲值 500: " + wallet.deposit(500));
        System.out.println("正常付款 300: " + wallet.pay(300));
        System.out.println("餘額不足付款 3000 (應失敗): " + wallet.pay(3000));
        System.out.println("負數金額付款 -100 (應失敗): " + wallet.pay(-100));
        System.out.println("正常退款 200: " + wallet.refund(200));

        System.out.println("最終狀態: " + wallet);
    }
}
