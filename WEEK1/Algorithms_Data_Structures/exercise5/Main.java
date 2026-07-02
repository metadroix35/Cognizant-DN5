package exercise5;

public class Main {
    public static void main(String[] args) {

        TaskManagement manager = new TaskManagement();

        manager.addTask(101, "Complete Java Assignment", "Pending");
        manager.addTask(102, "Prepare Presentation", "In Progress");
        manager.addTask(103, "Submit Report", "Completed");

        manager.displayTasks();

        System.out.println("\nSearching Task:");

        manager.searchTask(102);

        System.out.println();

        manager.deleteTask(102);

        manager.displayTasks();
    }
}
