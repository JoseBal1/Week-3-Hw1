import java.util.Vector;

public class OrderManager {
    private Vector<Order> orders;
    public OrderManager() {
        orders = new Vector<>();
    }
    public void addOrder(Order order) {
        orders.add(order);
    }

    public Order findOrder(String orderId){
        for (Order order : orders) {
            if(orderId.equals(order.getOrderID())){
                return order;
            }
        }
        return null;
    }

    public Vector<Order> getOrdersByStatus(String status) {
        Vector<Order> result = new Vector<>();
        for (Order order : orders) {
            if(order.getStatus().equals(status)){
                result.add(order);
            }
        }
        return result;
    }

    public Vector<Order> getOrdersByCustomer(String customer) {
        Vector<Order> result = new Vector<>();
        for (Order order : orders) {
            if(order.getCustomerName().equals(customer)){
                result.add(order);
            }
        }
        return result;

    }
    public double calculateTotalRevenue() {
        double total = 0;
        for (Order order : orders) {
            if (order.getStatus().equals("Delivered")) {
                total += order.calculateTotal();
            }
        }
        return total;
    }
    public double getTotalRevenue() {
        return calculateTotalRevenue();
    }
    public void cancelOrder(String orderId) {
        Order order = findOrder(orderId);
        if (order != null) {
            order.updateStatus("Cancelled");
            System.out.println("Order Cancelled");

        }
    }
    public void printAllOrders() {
        for (Order order : orders) {
            order.printOrder();
            System.out.println();
        }
    }
    public Vector<Order> getPendingOrders(){
        return getOrdersByStatus("Pending");
    }
    public int orderCount() {
        return orders.size();
    }

    public static void main(String[] args) {
        OrderManager orderManager = new OrderManager();

        Order order1 = new Order("O001", "Alice", "2024-01-15");
        order1.addItem(new OrderItem("P001", "Laptop", 1, 999.99));
        order1.addItem(new OrderItem("P003", "Mouse", 2, 29.99));
        orderManager.addOrder(order1);

        Order order2 = new Order("O002", "Bob", "2024-01-16");
        order2.addItem(new OrderItem("P002", "T-Shirt", 3, 19.99));
        orderManager.addOrder(order2);

        orderManager.printAllOrders();
        System.out.println("Total revenue: $" + orderManager.getTotalRevenue());
    }
}
