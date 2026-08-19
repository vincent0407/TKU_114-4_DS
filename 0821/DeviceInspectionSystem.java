// 父類別 Device
class Device {
    private String name;

    public Device(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void runDiagnostic() {
        System.out.println("[" + name + "] 執行基本設備診斷...");
    }
}

// Laptop 子類別
class Laptop extends Device {
    public Laptop(String name) {
        super(name);
    }

    @Override
    public void runDiagnostic() {
        System.out.println("[" + getName() + "] 執行筆記型電腦硬體與電池檢測...");
    }
}

// Printer 子類別（唯一擁有 cleanPrintHead() 的類別）
class Printer extends Device {
    public Printer(String name) {
        super(name);
    }

    @Override
    public void runDiagnostic() {
        System.out.println("[" + getName() + "] 執行印表機墨水與紙路檢測...");
    }

    public void cleanPrintHead() {
        System.out.println("[" + getName() + "] 執行印表機噴頭清潔程序...");
    }
}

// Router 子類別
class Router extends Device {
    public Router(String name) {
        super(name);
    }

    @Override
    public void runDiagnostic() {
        System.out.println("[" + getName() + "] 執行路由器網路連線與封包檢測...");
    }
}

public class DeviceInspectionSystem {
    public static void main(String[] args) {
        // 使用 Device[] 保存至少 4 個物件
        Device[] devices = new Device[] {
            new Laptop("MacBook Pro"),
            new Printer("Epson 印表機 A"),
            new Router("ASUS 路由器"),
            new Printer("HP 印表機 B")
        };

        System.out.println("=== 開始設備檢測程序 ===");
        for (Device d : devices) {
            // 1. 以多型 (polymorphism) 執行 runDiagnostic()
            d.runDiagnostic();

            // 2. 使用 Pattern Matching for instanceof (Java 16+)
            // 僅針對 Printer 執行清潔，且不對每個型態都寫轉型 (cast)
            if (d instanceof Printer printer) {
                printer.cleanPrintHead();
            }
            System.out.println("-----------------------------------");
        }
    }
}
