import java.util.Vector;
import java.util.ArrayList;
import java.util.Random;

public class VectorComparisonDemo {
    public static void main(String[] args) {
        int numProducts = 10000;
        Vector<Product> vector = new Vector<>();
        ArrayList<Product> arrayList = new ArrayList<>();


        long start = System.nanoTime();
        for (int i = 0; i < numProducts; i++) {
            vector.add(new Product("P" + i, "Product" + i, "Category", 10.0, 5, "Supplier"));
        }
        long end = System.nanoTime();
        System.out.println("Time to add 10,000 products to Vector: " + (end - start)/1_000_000.0 + " ms");

        start = System.nanoTime();
        for (int i = 0; i < numProducts; i++) {
            arrayList.add(new Product("P" + i, "Product" + i, "Category", 10.0, 5, "Supplier"));
        }
        end = System.nanoTime();
        System.out.println("Time to add 10,000 products to ArrayList: " + (end - start)/1_000_000.0 + " ms");

        Random rand = new Random();
        int accessCount = 1000;

        start = System.nanoTime();
        for (int i = 0; i < accessCount; i++) {
            vector.get(rand.nextInt(numProducts));
        }
        end = System.nanoTime();
        System.out.println("Time to access 1,000 random elements in Vector: " + (end - start)/1_000_000.0 + " ms");

        start = System.nanoTime();
        for (int i = 0; i < accessCount; i++) {
            arrayList.get(rand.nextInt(numProducts));
        }
        end = System.nanoTime();
        System.out.println("Time to access 1,000 random elements in ArrayList: " + (end - start)/1_000_000.0 + " ms");

        Runtime runtime = Runtime.getRuntime();
        long vectorMemory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Vector approximate memory used: " + vectorMemory/1024 + " KB");

        long arrayMemory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("ArrayList approximate memory used: " + arrayMemory/1024 + " KB");
    }
}

