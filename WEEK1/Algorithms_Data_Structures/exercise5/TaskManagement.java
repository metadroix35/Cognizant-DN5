package exercise5;

public class TaskManagement {
    private Task head;

    // Add Task
    public void addTask(int id, String name, String status) {

        Task newTask = new Task(id, name, status);

        if (head == null) {
            head = newTask;
            return;
        }

        Task current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newTask;
    }

    // Search Task
    public void searchTask(int id) {

        Task current = head;

        while (current != null) {

            if (current.taskId == id) {
                System.out.println(current);
                return;
            }

            current = current.next;
        }

        System.out.println("Task Not Found.");
    }

    // Traverse Tasks
    public void displayTasks() {

        Task current = head;

        System.out.println("\nTask List:");

        while (current != null) {

            System.out.println(current);

            current = current.next;
        }
    }

    // Delete Task
    public void deleteTask(int id) {

        if (head == null) {
            System.out.println("List is Empty.");
            return;
        }

        if (head.taskId == id) {
            head = head.next;
            System.out.println("Task Deleted.");
            return;
        }

        Task current = head;

        while (current.next != null && current.next.taskId != id) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("Task Not Found.");
        } else {
            current.next = current.next.next;
            System.out.println("Task Deleted.");
        }
    }
}
