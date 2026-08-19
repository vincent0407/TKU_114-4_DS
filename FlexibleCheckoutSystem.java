// 策略 1：計價政策介面 (PricingPolicy)
interface PricingPolicy {
    double calculatePrice(double originalPrice);
}

// 原價策略
class RegularPricing implements PricingPolicy {
    @Override
    public double calculatePrice(double originalPrice) {
        return originalPrice;
    }
}

// VIP 八五折策略
class VipPricing implements PricingPolicy {
    @Override
    public double calculatePrice(double originalPrice) {
        return originalPrice * 0.85;
    }
}

// 滿 2000 折 300 策略
class ThresholdDiscountPricing implements PricingPolicy {
    @Override
    public double calculatePrice(double originalPrice) {
        if (originalPrice >= 2000) {
            return originalPrice - 300;
        }
        return originalPrice;
    }
}

// 策略 2：通知管道介面 (NotificationChannel)
interface NotificationChannel {
    String sendNotification(String orderId, double finalPrice);
}

// Email 通知
class EmailNotification implements NotificationChannel {
    @Override
    public String sendNotification(String orderId, double finalPrice) {
        return "Email 發送成功 [訂單: " + orderId + ", 金額: " + finalPrice + "]";
    }
}

// SMS 通知
class SmsNotification implements NotificationChannel {
    @Override
    public String sendNotification(String orderId, double finalPrice) {
        return "SMS 發送成功 [訂單: " + orderId + ", 金額: " + finalPrice + "]";
    }
}

// Console 通知
class ConsoleNotification implements NotificationChannel {
    @Override
    public String sendNotification(String orderId, double finalPrice) {
        return "Console 輸出成功 [訂單: " + orderId + ", 金額: " + finalPrice + "]";
    }
}

// 結帳結果保存類別
class CheckoutResult {
    private String orderId;
    private double originalPrice;
    private double finalPrice;
    private String notificationStatus;

    public CheckoutResult(String orderId, double originalPrice, double finalPrice, String notificationStatus) {
        this.orderId = orderId;
        this.originalPrice = originalPrice;
        this.finalPrice = finalPrice;
        this.notificationStatus = notificationStatus;
    }

    @Override
    public String toString() {
        return "訂單編號: " + orderId +
               " | 原價: " + originalPrice +
               " | 實付: " + finalPrice +
               " | 通知狀態: " + notificationStatus;
    }
}

// 結帳服務主類別
public class FlexibleCheckoutSystem {

    public static CheckoutResult checkout(String orderId, double originalPrice, PricingPolicy pricingPolicy, NotificationChannel channel) {
        // 1. 計算最終金額
        double finalPrice = pricingPolicy.calculatePrice(originalPrice);
        
        // 2. 發送通知並取得狀態
        String status = channel.sendNotification(orderId, finalPrice);

        // 3. 回傳 CheckoutResult 物件（不只回傳 boolean）
        return new CheckoutResult(orderId, originalPrice, finalPrice, status);
    }

    public static void main(String[] args) {
        // 建立 3 種 PricingPolicy 實例
        PricingPolicy regular = new RegularPricing();
        PricingPolicy vip = new VipPricing();
        PricingPolicy discount2000 = new ThresholdDiscountPricing();

        // 建立 3 種 NotificationChannel 實例
        NotificationChannel email = new EmailNotification();
        NotificationChannel sms = new SmsNotification();
        NotificationChannel console = new ConsoleNotification();

        System.out.println("=== 測試 6 種 Pricing / Channel 組合 ===\n");

        // 組合 1: 原價 + Email
        CheckoutResult r1 = checkout("ORD-001", 1000, regular, email);
        System.out.println(r1 + "\n");

        // 組合 2: VIP 八五折 + SMS
        CheckoutResult r2 = checkout("ORD-002", 1000, vip, sms);
        System.out.println(r2 + "\n");

        // 組合 3: 滿2000折300 + Console
        CheckoutResult r3 = checkout("ORD-003", 2500, discount2000, console);
        System.out.println(r3 + "\n");

        // 組合 4: VIP 八五折 + Console
        CheckoutResult r4 = checkout("ORD-004", 3000, vip, console);
        System.out.println(r4 + "\n");

        // 組合 5: 滿2000折300 + Email
        CheckoutResult r5 = checkout("ORD-005", 1500, discount2000, email); // 未達 2000 門檻測試
        System.out.println(r5 + "\n");

        // 組合 6: 原價 + SMS
        CheckoutResult r6 = checkout("ORD-006", 500, regular, sms);
        System.out.println(r6 + "\n");
    }
}
