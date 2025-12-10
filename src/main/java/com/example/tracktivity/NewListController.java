package com.example.tracktivity;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class NewListController {

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
    private void initialize() {
        ImageNotifications.setOnMouseClicked(e -> changeScene(e, "Notifications.fxml"));
        ImageProfile.setOnMouseClicked(e -> changeScene(e, "Profile.fxml"));
    }

    @FXML
    private void CreateListButton(ActionEvent event) {
        changeScene(event, "Checklist.fxml");
    }

    @FXML
    private void CancelButton(ActionEvent event) {
        changeScene(event, "Checklist.fxml");
    }
}
