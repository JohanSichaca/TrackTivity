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

public class NewTaskController {

    @FXML private TextField taskNameField;
    @FXML private TextField descriptionField;
    @FXML private TextField categoryField;
    @FXML private TextField priorityField;
    @FXML private TextField subjectField;
    @FXML private TextField expirationDateField;

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

        try {
            Parent root = FXMLLoader.load(getClass().getResource("Checklist.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cancelButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Checklist.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}