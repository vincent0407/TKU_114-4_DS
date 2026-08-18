class Customer {
    private String id;
    private String name;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() { return name; }
    public String getId() { return id; }
}

class OrderItem {
    private String productName;
    private double price;
    private int quantity;

    public OrderItem(String productName, double price, int quantity) {
        this.productName = productName;
        this.price = (price < 0) ? 0 : price;
        this.quantity = (quantity < 0) ? 0 : quantity;
    }

    public double getSubtotal() { return price * quantity; }
    public int getQuantity() { return quantity; }
    public String getProductName() { return productName; }
}

class CustomerOrder {
    private Customer customer;
    private OrderItem[] items;

    public CustomerOrder(Customer customer, OrderItem[] items) {
        this.customer = customer;
        this.items = (items == null) ? new OrderItem[0] : items;
    }

    public double calculateTotal() {
        double total = 0;
        for (OrderItem item : items) {
            if (item != null) total += item.getSubtotal();
        }
        return total;
    }

    public int totalItemCount() {
        int count = 0;
        for (OrderItem item : items) {
            if (item != null) count += item.getQuantity();
        }
        return count;
    }

    public String summary() {
        StringBuilder sb = new StringBuilder();
        sb.append("顧客: ").append(customer.getName()).append(" (ID: ").append(customer.getId()).append(")\n");
        sb.append("訂單明細:\n");
        for (OrderItem item : items) {
            if (item != null) {
                sb.append(" - ").append(item.getProductName())
                  .append(" x ").append(item.getQuantity())
                  .append(" = $").append(item.getSubtotal()).append("\n");
            }
        }
        sb.append("品項總數量: ").append(totalItemCount()).append("\n");
        sb.append("訂單總金額: $").append(calculateTotal());
        return sb.toString();
    }
}

public class CustomerOrderSystem {
    public static void main(String[] args) {
        Customer customer = new Customer("C001", "Bob");
        OrderItem[] items = {
            new OrderItem("滑鼠", 500, 2),
            new OrderItem("鍵盤", 1200, 1),
            new OrderItem("螢幕", 4500, 1)
        };

        CustomerOrder order = new CustomerOrder(customer, items);
        System.out.println(order.summary());
    }
}
