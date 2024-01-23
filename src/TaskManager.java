import java.util.ArrayList;
import java.util.List;

// TaskManager class manages a list of tasks
public class TaskManager {
    private List<Task> tasks; // List to store Task objects

    // Constructor initializes the list of tasks
    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    // Add a new task to the list
    public void addTask(String description) {
        tasks.add(new Task(description));
    }

    // Mark a task at the specified index as completed and set completion note
    public void markTaskAsCompleted(int index) {
        Task task = tasks.get(index);
        task.setCompleted(true);
        task.setNote("Task completed on: " + java.time.LocalDate.now());
    }

    // Get a list of task descriptions
    public List<String> getTaskDescriptions() {
        List<String> descriptions = new ArrayList<>();
        for (Task task : tasks) {
            descriptions.add(task.getDescription());
        }
        return descriptions;
    }

    // Get the list of Task objects
    public List<Task> getTasks() {
        return tasks;
    }

    // Task class represents an individual task
    public static class Task {
        private String description; 
        private boolean completed; 
        private String note; 

        // Constructor initializes a task with a given description
        public Task(String description) {
            this.description = description;
            this.completed = false; // New tasks are not completed by default
        }

        // Get the description of the task
        public String getDescription() {
            return description;
        }

        // Check if the task is completed
        public boolean isCompleted() {
            return completed;
        }

        // Set the completion status of the task
        public void setCompleted(boolean completed) {
            this.completed = completed;
        }

        // Get the completion note for the task
        public String getNote() {
            return note;
        }

        // Set a note for the completed task
        public void setNote(String note) {
            this.note = note;
        }
    }
}
