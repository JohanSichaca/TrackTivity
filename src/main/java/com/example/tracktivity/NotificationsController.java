package com.example.tracktivity;

import Logic.Models.Notification;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.collections.FXCollections;

public class NotificationsController {

    @FXML
    private ImageView ImageHome;

    @FXML
    private ListView<Notification> listViewNotifications;

    @FXML
    private void initialize() {
        ImageHome.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
                Parent root = loader.load();

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setMaximized(true);
                stage.show();

                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        listViewNotifications.setItems(FXCollections.observableArrayList(Notification.loadTodayNotifications()));
    }
}
