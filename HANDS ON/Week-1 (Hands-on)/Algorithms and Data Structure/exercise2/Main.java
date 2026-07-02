package exercise2;

public class Main {
    public static void main(String[] args) {

        Product[] products = {

                new Product(101, "Keyboard", "Electronics"),
                new Product(102, "Laptop", "Electronics"),
                new Product(103, "Mouse", "Electronics"),
                new Product(104, "Phone", "Electronics"),
                new Product(105, "Speaker", "Electronics")
        };

        System.out.println("Linear Search:");

        Product linearResult =
                Search.linearSearch(products, "Mouse");

        if (linearResult != null) {
            System.out.println(linearResult);
        }
        else {
            System.out.println("Product Not Found");
        }

        System.out.println("\nBinary Search:");

        Product binaryResult =
                Search.binarySearch(products, "Phone");

        if (binaryResult != null) {
            System.out.println(binaryResult);
        }
        else {
            System.out.println("Product Not Found");
        }
    }
}
