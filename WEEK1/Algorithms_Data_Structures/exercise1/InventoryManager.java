package exercise1;

import java.util.HashMap;

public class InventoryManager {
    private HashMap<Integer, Product> inventory = new HashMap<>();

    public void addProduct(Product product) {
        inventory.put(product.getProductId(), product);
        System.out.println("Product Added.");
    }

    public void updateProduct(int id, int quantity, double price) {

        if (inventory.containsKey(id)) {

            Product product = inventory.get(id);

            product.setQuantity(quantity);
            product.setPrice(price);

            System.out.println("Product Updated.");
        }
        else {
            System.out.println("Product Not Found.");
        }
    }

    public void deleteProduct(int id) {

        if (inventory.containsKey(id)) {

            inventory.remove(id);
            System.out.println("Product Deleted.");
        }
        else {
            System.out.println("Product Not Found.");
        }
    }

    public void displayProducts() {

        System.out.println("\nInventory:");

        for (Product product : inventory.values()) {
            System.out.println(product);
        }
    }
}
