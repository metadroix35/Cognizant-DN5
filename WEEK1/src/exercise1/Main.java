package exercise1;

public class Main {
    public static void main(String[] args) {

        InventoryManager manager = new InventoryManager();

        manager.addProduct(
                new Product(101,"Laptop",10,65000)
        );

        manager.addProduct(
                new Product(102,"Mouse",50,500)
        );

        manager.addProduct(
                new Product(103,"Keyboard",25,1500)
        );

        manager.displayProducts();

        manager.updateProduct(102,100,450);

        manager.displayProducts();

        manager.deleteProduct(103);

        manager.displayProducts();
    }
}

