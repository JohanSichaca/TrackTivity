package com.example.tracktivity;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class CalendarController {

    @FXML
    private ImageView ImageProfile;

    @FXML
    private ImageView ImageNotifications;

    private void changeScene(javafx.event.Event event, String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void HomeButton(ActionEvent event){
        changeScene(event, "Home.fxml");
    }

    @FXML
    private void initialize() {
        ImageNotifications.setOnMouseClicked(e -> changeScene(e, "Notifications.fxml"));
        ImageProfile.setOnMouseClicked(e -> changeScene(e, "Profile.fxml"));
    }

    @FXML
    private void ChecklistButton(ActionEvent event) {
        changeScene(event, "Checklist.fxml");
    }

    @FXML
    private void EventsButton(ActionEvent event) {
        changeScene(event, "Events.fxml");
    }

    @FXML
    private void DashboardButton(ActionEvent event) {
        changeScene(event, "Dashboard.fxml");
    }

    @FXML
    private void AddTaskButton(ActionEvent event) {
        changeScene(event, "NewTask.fxml");
    }
}
