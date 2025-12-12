package com.example.tracktivity;

import Logic.Services.Schedulable;
import Logic.Services.TaskManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Controls the New Task screen.
 * Allows users to create a new task.
 */
public class NewTaskController {

    @FXML private TextField taskNameField;
    @FXML private TextField descriptionField;
    @FXML private TextField categoryField;
    @FXML private TextField priorityField;
    @FXML private TextField subjectField;
    @FXML private TextField expirationDateField;

    /** Creates a new task and saves it. */
    @FXML
    private void createTaskButton(ActionEvent event) {
        Schedulable task = new Schedulable(
                taskNameField.getText(),
                descriptionField.getText(),
                categoryField.getText(),
                priorityField.getText(),
                subjectField.getText(),
                expirationDateField.getText()
        );

        TaskManager.tasksList.add(task);
        TaskManager.saveToFile();
        changeScene(event, "Checklist.fxml");
    }

    /** Cancels task creation and returns to Checklist screen. */
    @FXML
    private void cancelButton(ActionEvent event) {
        changeScene(event, "Checklist.fxml");
    }

    /** Changes the current scene. */
    private void changeScene(ActionEvent event, String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
