import java.util.Objects;

public class Product {
    public String productId;
    public String name;
    public String category;
    public double price;
    public int quantityInStock;
    public String supplier;

    public Product(String productId, String name, String category, double price, int quantityInStock, String supplier) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.supplier = supplier;
    }

    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getQuantityInStock() {
        return quantityInStock;
    }
    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
    public String getSupplier() {
        return supplier;
    }
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "Product [productId = " + productId + ", name = " + name + ", category = " + category + ", price = " + price + ", quantityInStock = " + quantityInStock + ", supplier = " + supplier + "]";
    }
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj instanceof Product){
            Product p = (Product)obj;
        }
        return false;
    }
    @Override
    public int hashCode(){
        return Objects.hash(productId);
    }



}

