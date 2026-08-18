// 介面 MessageSender
interface MessageSender {
    void send(String receiver, String message);
}

// 實作類別：EmailSender
class EmailSender implements MessageSender {
    @Override
    public void send(String receiver, String message) {
        System.out.println("[Email] 發送至 " + receiver + ": " + message);
    }
}

// 實作類別：SmsSender
class SmsSender implements MessageSender {
    @Override
    public void send(String receiver, String message) {
        System.out.println("[SMS] 發送至 " + receiver + ": " + message);
    }
}

// 實作類別：ConsoleSender
class ConsoleSender implements MessageSender {
    @Override
    public void send(String receiver, String message) {
        System.out.println("[Console] 發送至 " + receiver + ": " + message);
    }
}

public class MessageSenderSystem {
    // 依賴 MessageSender 介面，新增 sender 時無需修改此 notify 方法
    public static void notify(MessageSender sender, String receiver, String message) {
        // 空白 receiver 或 message 檢查與處理
        if (receiver == null || receiver.trim().isEmpty()) {
            System.out.println("錯誤：接收者 (receiver) 不能為空白！");
            return;
        }
        if (message == null || message.trim().isEmpty()) {
            System.out.println("錯誤：訊息內容 (message) 不能為空白！");
            return;
        }

        sender.send(receiver, message);
    }

    public static void main(String[] args) {
        MessageSender email = new EmailSender();
        MessageSender sms = new SmsSender();
        MessageSender console = new ConsoleSender();

        // 正常發送測試
        notify(email, "user@example.com", "歡迎訂閱服務！");
        notify(sms, "0912345678", "您的驗證碼為 1234");
        notify(console, "SystemAdmin", "系統維護公告");

        // 空白防呆測試
        notify(email, "", "這是一則測試訊息");
        notify(sms, "0912345678", "  ");
    }
}
