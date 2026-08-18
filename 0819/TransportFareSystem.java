// 抽象類別 Transport
abstract class Transport {
    protected String routeName;

    public Transport(String routeName) {
        this.routeName = routeName;
    }

    // 宣告抽象方法，由子類別實作
    public abstract double calculateFare(int distance);

    public String getRouteName() {
        return routeName;
    }
}

// 公車子類別
class Bus extends Transport {
    public Bus(String routeName) {
        super(routeName);
    }

    @Override
    public double calculateFare(int distance) {
        // 假設公車基本票價 15 元，每公里增加 2 元
        return 15 + distance * 2;
    }
}

// 計程車子類別
class Taxi extends Transport {
    public Taxi(String routeName) {
        super(routeName);
    }

    @Override
    public double calculateFare(int distance) {
        // 假設計程車起跳價 85 元，每公里增加 10 元
        return 85 + distance * 10;
    }
}

public class TransportFareSystem {
    public static void main(String[] args) {
        // 以 Transport[] 保存至少四個物件
        Transport[] transports = new Transport[] {
            new Bus("藍15"),
            new Taxi("市區計程車 A"),
            new Bus("紅30"),
            new Taxi("長途計程車 B")
        };

        int distance = 10; // 假設行駛距離為 10 公里

        // 主程式不使用 instanceof，完全透過多型 (overridden method) 呼叫
        for (Transport t : transports) {
            System.out.println("路線: " + t.getRouteName() + " | 距離: " + distance + " km | 票價: " + t.calculateFare(distance) + " 元");
        }
    }
}
