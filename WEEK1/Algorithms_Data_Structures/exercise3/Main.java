package exercise3;

public class Main {
    public static void main(String[] args) {

        Order[] orders = {

                new Order(101, "Alice", 5000),
                new Order(102, "Bob", 2500),
                new Order(103, "Charlie", 7000),
                new Order(104, "David", 3500),
                new Order(105, "Eva", 1000)
        };

        System.out.println("Original Orders");

        Sorting.display(orders);

        System.out.println("\nBubble Sort");

        Sorting.bubbleSort(orders);

        Sorting.display(orders);

        Order[] orders2 = {

                new Order(101, "Alice", 5000),
                new Order(102, "Bob", 2500),
                new Order(103, "Charlie", 7000),
                new Order(104, "David", 3500),
                new Order(105, "Eva", 1000)
        };

        System.out.println("\nQuick Sort");

        Sorting.quickSort(orders2, 0, orders2.length - 1);

        Sorting.display(orders2);
    }
}
