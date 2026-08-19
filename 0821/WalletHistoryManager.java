class Transaction {
    private int sequence;
    private String type; // "DEPOSIT", "WITHDRAW", "TRANSFER_IN", "TRANSFER_OUT"
    private int amount;

    public Transaction(int sequence, String type, int amount) {
        this.sequence = sequence;
        this.type = type;
        this.amount = amount;
    }

    public int getSequence() { return sequence; }
    public String getType() { return type; }
    public int getAmount() { return amount; }

    @Override
    public String toString() {
        return "#" + sequence + " | 類型: " + type + " | 金額: $" + amount;
    }
}

class Wallet {
    private String walletId;
    private int balance;
    private Transaction[] transactions;
    private int txCount;
    private int seqCounter;

    public Wallet(String walletId, int initialBalance, int maxCapacity) {
        this.walletId = walletId;
        this.balance = (initialBalance < 0) ? 0 : initialBalance;
        this.transactions = new Transaction[maxCapacity];
        this.txCount = 0;
        this.seqCounter = 1;
    }

    public String getWalletId() { return walletId; }
    public int getBalance() { return balance; }

    // 檢查陣列是否已滿
    public boolean isFull() {
        return txCount >= transactions.length;
    }

    private void addTransaction(String type, int amount) {
        transactions[txCount++] = new Transaction(seqCounter++, type, amount);
    }

    public boolean deposit(int amount) {
        if (amount <= 0 || isFull()) return false;
        balance += amount;
        addTransaction("DEPOSIT", amount);
        return true;
    }

    public boolean withdraw(int amount) {
        if (amount <= 0 || balance < amount || isFull()) return false;
        balance -= amount;
        addTransaction("WITHDRAW", amount);
        return true;
    }

    // 3. 轉帳給另一個錢包，兩邊同時留下記錄（4. 陣列滿時不得修改餘額）
    public boolean transferTo(Wallet target, int amount) {
        if (target == null || target == this) return false;
        if (amount <= 0 || this.balance < amount) return false;
        if (this.isFull() || target.isFull()) return false;

        this.balance -= amount;
        target.balance += amount;

        this.addTransaction("TRANSFER_OUT", amount);
        target.addTransaction("TRANSFER_IN", amount);
        return true;
    }

    // 1. 根據序號找交易
    public Transaction findTransaction(int sequence) {
        for (int i = 0; i < txCount; i++) {
            if (transactions[i].getSequence() == sequence) {
                return transactions[i];
            }
        }
        return null;
    }

    // 2. 計算指定交易類型的總金額
    public int totalByType(String type) {
        int sum = 0;
        for (int i = 0; i < txCount; i++) {
            if (transactions[i].getType().equalsIgnoreCase(type)) {
                sum += transactions[i].getAmount();
            }
        }
        return sum;
    }

    // 5. 輸出完整 statement
    public void printStatement() {
        System.out.println("========== 錢包對帳單 (" + walletId + ") ==========");
        System.out.println("當前餘額: $" + balance);
        System.out.println("歷史交易明細:");
        if (txCount == 0) {
            System.out.println(" (無交易紀錄)");
        } else {
            for (int i = 0; i < txCount; i++) {
                System.out.println(" " + transactions[i]);
            }
        }
        System.out.println("==========================================");
    }
}

public class WalletHistoryManager {
    public static void main(String[] args) {
        // 容量限制設為 5 筆
        Wallet w1 = new Wallet("W01", 1000, 5);
        Wallet w2 = new Wallet("W02", 500, 5);

        // 基本操作與轉帳
        w1.deposit(500);
        w1.withdraw(200);
        w1.transferTo(w2, 300);

        // 5. 輸出兩個錢包的完整 statement
        w1.printStatement();
        w2.printStatement();

        // 1. 測試 findTransaction
        System.out.println("查找 w1 序號 2 的交易: " + w1.findTransaction(2));
        System.out.println("查找 w1 序號 99 的交易: " + w1.findTransaction(99));

        // 2. 測試 totalByType
        System.out.println("w1 提款總金額 (WITHDRAW): $" + w1.totalByType("WITHDRAW"));
        System.out.println("w1 轉出總金額 (TRANSFER_OUT): $" + w1.totalByType("TRANSFER_OUT"));
    }
}
