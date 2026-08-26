package midterm_exam;

import java.util.ArrayList;
import java.util.List;

public class Q04_NotificationRouter {

    // 介面 Channel
    public interface Channel {
        String name();
        boolean supports(String destination);
        String send(String destination, String message);
    }

    // 1. EmailChannel 實作
    public static class EmailChannel implements Channel {
        @Override
        public String name() {
            return "EMAIL";
        }

        @Override
        public boolean supports(String destination) {
            if (destination == null) {
                return false;
            }
            // 包含 @ 且 @ 不在開頭或結尾
            int atIndex = destination.indexOf('@');
            return atIndex > 0 && atIndex < destination.length() - 1;
        }

        @Override
        public String send(String destination, String message) {
            // 3. send() 回傳 CHANNEL|destination|message
            return name() + "|" + destination + "|" + message;
        }
    }

    // 2. SmsChannel 實作
    public static class SmsChannel implements Channel {
        @Override
        public String name() {
            return "SMS";
        }

        @Override
        public boolean supports(String destination) {
            if (destination == null) {
                return false;
            }
            // 去除 - 後恰好 10 個數字
            String cleaned = destination.replace("-", "");
            return cleaned.matches("\\d{10}");
        }

        @Override
        public String send(String destination, String message) {
            // 3. send() 回傳 CHANNEL|destination|message
            return name() + "|" + destination + "|" + message;
        }
    }

    // 4. & 5. route 方法
    public static List<String> route(List<Channel> channels, String destination, String message) {
        // 5. channels, destination 或 message 為 null 時回傳 empty List
        if (channels == null || destination == null || message == null) {
            return new ArrayList<>();
        }

        List<String> results = new ArrayList<>();
        // 依 channels 順序處理
        for (Channel channel : channels) {
            // List 中的 null channel 要略過
            if (channel != null && channel.supports(destination)) {
                results.add(channel.send(destination, message));
            }
        }
        return results;
    }

    // 測試用 main 方法
    public static void main(String[] args) {
        var channels = List.of(
            new Q04_NotificationRouter.EmailChannel(),
            new Q04_NotificationRouter.SmsChannel()
        );

        System.out.println(Q04_NotificationRouter.route(channels, "a@b.com", "Ready"));
        System.out.println(Q04_NotificationRouter.route(channels, "0912-345-678", "Go"));
    }
}
