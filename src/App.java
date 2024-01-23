import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    private TaskManager taskManager; // TaskManager instance to manage tasks
    private ListView<String> taskListView; // ListView to display tasks

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        taskManager = new TaskManager(); // Create an instance of TaskManager

        primaryStage.setTitle("Task Manager");

        // Create the main layout container (VBox)
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        // Text field for entering task description
        TextField taskDescriptionField = new TextField();
        taskDescriptionField.setPromptText("Enter task description");

        // Button to add a new task
        Button addButton = new Button("Add Task");
        addButton.setOnAction(e -> addTask(taskDescriptionField.getText()));

        // Button to mark the selected task as completed
        Button completeButton = new Button("Mark as Completed");
        completeButton.setOnAction(e -> markTaskAsCompleted());

        // ListView to display tasks
        taskListView = new ListView<>();
        taskListView.setPrefHeight(200);

        // Add UI components to the VBox
        vbox.getChildren().addAll(taskDescriptionField, addButton, completeButton, taskListView);

        // Create the main scene
        Scene scene = new Scene(vbox, 300, 300);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    // Method to add a new task
    private void addTask(String description) {
        if (!description.isEmpty()) {
            taskManager.addTask(description);
            updateTaskList();
        }
    }

    // Method to mark the selected task as completed
    private void markTaskAsCompleted() {
        int selectedIndex = taskListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            taskManager.markTaskAsCompleted(selectedIndex);
            updateTaskList();
        } else {
            showAlert("Select a task to mark as completed");
        }
    }

    // Method to update the task list in the ListView
    private void updateTaskList() {
        taskListView.getItems().clear();

        // Iterate through tasks and display them in the ListView
        for (TaskManager.Task task : taskManager.getTasks()) {
            String description = task.getDescription();
            String note = task.getNote();

            // Append note information for completed tasks
            if (task.isCompleted() && note != null) {
                description += " (Completed: " + note + ")";
            }

            taskListView.getItems().add(description);
        }
    }

    // Method to show an alert message
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Task Manager");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
