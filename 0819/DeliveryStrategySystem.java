// 配送方式介面
interface DeliveryMethod {
    double calculateShippingFee(double weight);
    String getEstimateDescription();
}

// 宅配
class HomeDelivery implements DeliveryMethod {
    @Override
    public double calculateShippingFee(double weight) {
        return 100 + weight * 10;
    }

    @Override
    public String getEstimateDescription() {
        return "宅配到府：預計 1~2 個工作天送達";
    }
}

// 超商取貨
class ConvenienceStoreDelivery implements DeliveryMethod {
    @Override
    public double calculateShippingFee(double weight) {
        return 60; // 固定運費
    }

    @Override
    public String getEstimateDescription() {
        return "超商取貨：預計 2~3 天指定門市取件";
    }
}

// 自取
class SelfPickup implements DeliveryMethod {
    @Override
    public double calculateShippingFee(double weight) {
        return 0; // 免運費
    }

    @Override
    public String getEstimateDescription() {
        return "現場自取：可於營業時間內至門市取貨";
    }
}

// 訂單服務（使用 Composition 組合 DeliveryMethod）
class OrderService {
    private DeliveryMethod deliveryMethod;

    public OrderService(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public void processOrder(double itemWeight) {
        double fee = deliveryMethod.calculateShippingFee(itemWeight);
        String estimate = deliveryMethod.getEstimateDescription();

        System.out.println("運費計算: " + fee + " 元");
        System.out.println("預估說明: " + estimate);
    }
}

public class DeliveryStrategySystem {
    public static void main(String[] args) {
        double parcelWeight = 3.5; // 包裹重量 3.5 kg

        System.out.println("=== 測試宅配配送 ===");
        OrderService order1 = new OrderService(new HomeDelivery());
        order1.processOrder(parcelWeight);

        System.out.println("\n=== 測試超商取貨 ===");
        OrderService order2 = new OrderService(new ConvenienceStoreDelivery());
        order2.processOrder(parcelWeight);

        System.out.println("\n=== 測試現場自取 ===");
        OrderService order3 = new OrderService(new SelfPickup());
        order3.processOrder(parcelWeight);
    }
}
