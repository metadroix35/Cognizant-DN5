package exercise4;

public class Main {
    public static void main(String[] args) {

        EmployeeManagement manager = new EmployeeManagement(10);

        manager.addEmployee(new Employee(101, "Alice", "Manager", 80000));
        manager.addEmployee(new Employee(102, "Bob", "Developer", 60000));
        manager.addEmployee(new Employee(103, "Charlie", "Tester", 50000));

        manager.displayEmployees();

        System.out.println("\nSearching Employee:");

        Employee employee = manager.searchEmployee(102);

        if (employee != null) {
            System.out.println(employee);
        } else {
            System.out.println("Employee Not Found.");
        }

        manager.deleteEmployee(102);

        manager.displayEmployees();
    }
}
