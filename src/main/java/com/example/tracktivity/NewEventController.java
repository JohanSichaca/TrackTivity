package com.example.tracktivity;

import Logic.Services.Event;
import Logic.Services.EventManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class NewEventController {

    @FXML private TextField nameField;
    @FXML private TextField descriptionField;
    @FXML private TextField dateField;
    @FXML private TextField startTimeField;
    @FXML private TextField endTimeField;

    @FXML
    private void createEventButton(ActionEvent event) {

        Event evt = new Event(
                nameField.getText(),
                descriptionField.getText(),
                dateField.getText(),
                startTimeField.getText(),
                endTimeField.getText()
        );

        EventManager.eventsList.add(evt);
        EventManager.saveToFile();

        changeScene(event, "Events.fxml");
    }

    @FXML
    private void cancelButton(ActionEvent event) {
        changeScene(event, "Events.fxml");
    }

    private void changeScene(ActionEvent event, String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
