import java.util.Enumeration;
import java.util.Vector;

public class ProductInventory {
    public Vector<Product> products;

    public ProductInventory() {
        products = new Vector<>();
    }

    public void addProduct(Product p) {
        if (products.contains(p)) {
            System.out.println("Product is already in the inventory");
            return;
        }
        products.add(p);
    }
    public boolean removeProduct(String productId){
        for(int i=0; i<products.size(); i++){
            if(productId.equals(products.get(i).productId)){
                products.remove(i);
                return true;
            }
        }
        return false;
    }

    public Product findProduct(String productId){
        for(int i=0; i<products.size(); i++){
            if(productId.equals(products.get(i).productId)){
                return products.get(i);
            }
        }
        return null;
    }
    public Vector<Product>getProductsByCategory(String category){
        Vector<Product> result = new Vector<>();
        for(int i=0; i<products.size(); i++){
            if(products.get(i).category.equals(category)){
                result.add(products.get(i));
            }
        }
        return result;
    }
    public Vector<Product> getLowStockProducts(int threshold){
        Vector<Product> result = new Vector<>();
        for(Product p : products){
            if (p.getQuantityInStock() < threshold){
                result.add(p);
            }
        }
        return result;
    }
    public double getTotalInventoryValue(){
        double total = 0;
        for(Product p : products){
            total += p.getPrice() * p.getQuantityInStock();
        }
        return total;
    }
    public void updateStock(String productId, int quantityChange){
        Product p = findProduct(productId);
        if(p != null){
            int newQuantity = p.getQuantityInStock() + quantityChange;
            if (newQuantity >=0){
                p.setQuantityInStock(newQuantity);
            }
            else{
                System.out.println("Stock cannot be under 0");
            }
        }
    }
    public void printAllProducts(){
        for(int i=0; i<products.size(); i++){
            System.out.println(products.get(i));

        }
    }
    public int getTotalProducts(){
        return products.size();
    }
    public void printCapacityInfo(){
        System.out.println("Size: " + products.size());
        System.out.println("Capacity: "+ products.capacity());
    }
    public void optimizeCapacity(){
        products.trimToSize();

    }
    public void ensureCapacity(int minCapacity){
        products.ensureCapacity(minCapacity);
    }
    public void printCapacityReport(){
        System.out.println("Capacity Report");
        System.out.println("Size: " + products.size());
        System.out.println("Capacity: " + products.capacity());
        double utilization = ((double)products.size()/products.capacity())*100;
        int freeSpace = products.capacity() - products.size();
        System.out.println("Utilization: " + utilization);
        System.out.println("Elements that can be added: " + freeSpace);
    }

    public void printProdutsUsingEnumeration(){
        Enumeration<Product> e = products.elements();
        while(e.hasMoreElements()){
            Product p = e.nextElement();
            System.out.println(p);
        }
    }
}
