import java.util.Scanner;
import java.util.Vector;
public class InventorySystemMain {
    public static void main(String[] args) {
        ProductInventory inventory = new ProductInventory();
        inventory.addProduct(new Product("P001", "Laptop", "Electronics", 999.99, 10, "TechCorp"));
        inventory.addProduct(new Product("P002", "T-Shirt", "Clothing", 19.99, 50, "FashionInc"));
        inventory.addProduct(new Product("P003", "Mouse", "Electronics", 29.99, 5, "TechCorp"));


        OrderManager orderManager = new OrderManager();
        Order order1 = new Order("O001", "Alice", "2024-01-15");
        order1.addItem(new OrderItem("P001", "Laptop", 1, 999.99));
        order1.addItem(new OrderItem("P003", "Mouse", 2, 29.99));
        orderManager.addOrder(order1);

        Order order2 = new Order("O002", "Bob", "2024-01-16");
        order2.addItem(new OrderItem("P002", "T-Shirt", 3, 19.99));
        orderManager.addOrder(order2);

        System.out.println("Finding product P002:");
        Product p = inventory.findProduct("P002");
        if (p != null) System.out.println(p);

        System.out.println("Removing product P003...");
        inventory.removeProduct("P003");
        inventory.printAllProducts();


        Vector<Product> lowStock = inventory.getLowStockProducts(10);
        System.out.println("\nLow stock products:");
        for (Product prod : lowStock){
            System.out.println(prod);
        }

        inventory.ensureCapacity(20);
        System.out.println("\nAfter ensuring capacity of 20:");
        inventory.printCapacityReport();

        inventory.optimizeCapacity();
        System.out.println("\nAfter optimizing capacity:");
        inventory.printCapacityReport();

        Vector<Integer> numbers = new Vector<>();
        numbers.add(10);
        numbers.add(50);
        numbers.add(30);
        System.out.println("\nNumbers vector before swap: " + numbers);
        VectorUtils.swap(numbers, 0, 2);
        System.out.println("Numbers vector after swap: " + numbers);

        System.out.println("Max number: " + VectorUtils.findMax(numbers));
        System.out.println("Count of 50: " + VectorUtils.countMatches(numbers, 50));

        System.out.println("Total revenue from delivered orders: $" + orderManager.getTotalRevenue());


        Scanner scan = new Scanner(System.in);
        boolean quit = false;
        while (!quit) {
            System.out.println("Inventory System Menu");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Find Product");
            System.out.println("4: List ALL Products");
            System.out.println("5: Create Order");
            System.out.println("6: View Order");
            System.out.println("7: Process Orders");
            System.out.println("8: Generate Report");
            System.out.println("9: Exit");
            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1 -> { // Add Product
                    System.out.print("Product ID: "); String id = scan.nextLine();
                    System.out.print("Name: "); String name = scan.nextLine();
                    System.out.print("Category: "); String category = scan.nextLine();
                    System.out.print("Price: "); double price = scan.nextDouble();
                    System.out.print("Quantity: "); int qty = scan.nextInt(); scan.nextLine();
                    System.out.print("Supplier: "); String supplier = scan.nextLine();
                    inventory.addProduct(new Product(id, name, category, price, qty, supplier));
                    System.out.println("Product added.");
                }
                case 2 -> { // Remove Product
                    System.out.print("Product ID to remove: "); String id = scan.nextLine();
                    if (inventory.removeProduct(id)) System.out.println("Product removed.");
                    else System.out.println("Product not found.");
                }
                case 3 -> { // Find Product
                    System.out.print("Product ID to find: "); String id = scan.nextLine();
                    p = inventory.findProduct(id);
                    if (p != null) System.out.println(p);
                    else System.out.println("Product not found.");
                }
                case 4 -> inventory.printAllProducts(); // List all
                case 5 -> { // Create Order
                    System.out.print("Order ID: "); String orderId = scan.nextLine();
                    System.out.print("Customer Name: "); String cust = scan.nextLine();
                    System.out.print("Order Date (YYYY-MM-DD): "); String date = scan.nextLine();
                    Order order = new Order(orderId, cust, date);

                    boolean addingItems = true;
                    while (addingItems) {
                        System.out.print("Product ID to add: "); String pid = scan.nextLine();
                        Product prod = inventory.findProduct(pid);
                        if (prod == null) {
                            System.out.println("Product not found.");
                            continue;
                        }
                        System.out.print("Quantity: "); int q = scan.nextInt(); scan.nextLine();
                        if(q > prod.getQuantityInStock()) {
                            System.out.println("Not enough stock. Available: " + prod.getQuantityInStock());
                            continue;
                        }
                        order.addItem(new OrderItem(prod.getProductId(), prod.getName(), q, prod.getPrice()));
                        inventory.updateStock(pid, -q); // Reduce stock
                        System.out.print("Add another item? (y/n): ");
                        addingItems = scan.nextLine().equalsIgnoreCase("y");
                    }
                    orderManager.addOrder(order);
                    System.out.println("Order created.");
                }
                case 6 -> orderManager.printAllOrders(); // View orders
                case 7 -> { // Process orders
                    Vector<Order> pending = orderManager.getPendingOrders();
                    if (pending.isEmpty()) System.out.println("No pending orders.");
                    else {
                        for (Order o : pending) {
                            o.updateStatus("Delivered"); // Simplified: mark as delivered
                            System.out.println("Order " + o.getOrderID() + " marked as Delivered.");
                        }
                    }
                }
                case 8 -> { // Generate report
                    System.out.println("=== Inventory Report ===");
                    System.out.println("Total products: " + inventory.getTotalProducts());
                    System.out.println("Low stock (<10): " + inventory.getLowStockProducts(10).size());
                    System.out.println("Total inventory value: $" + inventory.getTotalInventoryValue());
                    System.out.println("Orders Report");
                    System.out.println("Total orders: " + orderManager.orderCount());
                    System.out.println("Pending orders: " + orderManager.getPendingOrders().size());
                    System.out.println("Total revenue (delivered orders): $" + orderManager.getTotalRevenue());
                    System.out.println("Capacity Info");
                    inventory.printCapacityReport();
                }
                case 9 -> quit = true;
                default -> System.out.println("Invalid choice.");
            }
        }
        scan.close();
        System.out.println("Exiting");
    }
}
