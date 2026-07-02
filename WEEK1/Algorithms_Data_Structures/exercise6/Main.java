package exercise6;

public class Main {
    public static void main(String[] args) {

        // Books sorted alphabetically by title
        Book[] books = {

                new Book(101, "Clean Code", "Robert Martin"),
                new Book(102, "Data Structures", "Mark Allen"),
                new Book(103, "Java Programming", "Herbert Schildt"),
                new Book(104, "Operating Systems", "Galvin"),
                new Book(105, "Python Basics", "John Zelle")
        };

        System.out.println("Linear Search:");

        Book book1 = SearchLib.linearSearch(books, "Java Programming");

        if (book1 != null)
            System.out.println(book1);
        else
            System.out.println("Book Not Found");

        System.out.println("\nBinary Search:");

        Book book2 = SearchLib.binarySearch(books, "Operating Systems");

        if (book2 != null)
            System.out.println(book2);
        else
            System.out.println("Book Not Found");
    }
}
