import java.util.Vector;

public class Order {
    private String orderID;
    private String customerName;
    private String orderDate;
    private Vector<OrderItem> orderItems;
    private String orderStatus;
    public Order(String orderID, String customerName, String orderDate) {
        this.orderID = orderID;
        this.customerName = customerName;
        this.orderDate = orderDate;
        this.orderItems = new Vector<>();
        this.orderStatus = "Pending";
    }

    public void addItem(OrderItem orderItem) {
        orderItems.add(orderItem);
    }

    public boolean removeItem(String productId) {
        for(int i=0; i<orderItems.size(); i++) {
            if(orderItems.get(i).getProductId().equals(productId)) {
                orderItems.remove(i);
                return true;
            }
        }
        return false;
    }

    public OrderItem findItem(String productId) {
        for (int i = 0; i < orderItems.size(); i++) {
            if (orderItems.get(i).getProductId().equals(productId)) {
                return orderItems.get(i);
            }
        }
        return null;
    }

    public double calculateTotal() {
        double total = 0;
        for (OrderItem orderItem : orderItems) {
            total += orderItem.calculateSubtotal();
        }
        return total;
    }
    public int getTotalItems(){
        int total = 0;
        for (OrderItem orderItem : orderItems) {
            total += orderItem.getQuantity();
        }
        return total;
    }
    public void updateStatus(String status) {
        this.orderStatus = status;
    }
    public String getStatus() {
        return orderStatus;
    }
    public String getCustomerName(){
        return customerName;
    }
    public String getOrderID(){
        return orderID;
    }
    public void printOrder() {
        System.out.println("Order ID: " + orderID);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Order Date: " + orderDate);
        System.out.println("Order Status: " + orderStatus);
        for (OrderItem orderItem : orderItems) {
            System.out.println(orderItem);
        }
        System.out.println("Total: $" + calculateTotal());
    }

    public Vector<OrderItem> getItems(){
        return new Vector<>(orderItems);
    }

}
