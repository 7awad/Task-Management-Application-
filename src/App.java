/**
 * This Java program represents a simple Task Manager application.
 * Users can add tasks, mark tasks as completed, and view the list of tasks.
 * Input validation is implemented to handle user input errors gracefully.
 *
 * The program consists of two classes: Task and TaskManager. The Task class
 * represents an individual task with a description and completion status,
 * while the TaskManager class manages a list of tasks and provides
 * functionality to interact with them.
 *
 * The TaskManagerApp class contains the main method to run the Task Manager application.
 *
 * by: Jawad
 */

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Task {
    private String description;
    private boolean completed;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markAsCompleted() {
        this.completed = true;
    }

    @Override
    public String toString() {
        return "[" + (completed ? "X" : " ") + "] " + description;
    }
}

class TaskManager {
    private ArrayList<Task> tasks;
    private Scanner scanner;

    public TaskManager() {
        this.tasks = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void addTask(String description) {
        Task task = new Task(description);
        tasks.add(task);
        System.out.println("Task added: " + description);
    }

    public void markTaskAsCompleted(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.markAsCompleted();
            System.out.println("Task marked as completed: " + task.getDescription());
        } else {
            System.out.println("Invalid task index");
        }
    }

    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found");
        } else {
            System.out.println("Tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + ". " + tasks.get(i));
            }
        }
    }

    public void startTaskManager() {
        int choice;
        do {
            try {
                System.out.println("\nTask Manager Menu:");
                System.out.println("1. Add Task");
                System.out.println("2. Mark Task as Completed");
                System.out.println("3. Display Tasks");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter task description: ");
                        scanner.nextLine(); // Consume the newline character
                        String description = scanner.nextLine();
                        addTask(description);
                        break;
                    case 2:
                        System.out.print("Enter task index to mark as completed: ");
                        int index = scanner.nextInt();
                        markTaskAsCompleted(index);
                        break;
                    case 3:
                        displayTasks();
                        break;
                    case 0:
                        System.out.println("Exiting Task Manager. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume the invalid input
                choice = -1; // Set choice to -1 to force re-entering the loop
            }
        } while (choice != 0);
    }
}
