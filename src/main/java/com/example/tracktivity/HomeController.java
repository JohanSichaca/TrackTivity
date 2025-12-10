package com.example.tracktivity;

import Logic.Services.Schedulable;
import Logic.Services.TaskManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class HomeController {

    @FXML
    private ImageView ImageProfile;

    @FXML
    private ImageView ImageNotifications;

    @FXML private TableView<Schedulable> pendingTable;
    @FXML private TableView<Schedulable> completedTable;

    @FXML private TableColumn<Schedulable, String> pTaskCol;
    @FXML private TableColumn<Schedulable, String> pPriorityCol;
    @FXML private TableColumn<Schedulable, String> pExpirationCol;

    @FXML private TableColumn<Schedulable, String> cTaskCol;
    @FXML private TableColumn<Schedulable, String> cPriorityCol;
    @FXML private TableColumn<Schedulable, String> cExpirationCol;

    private ObservableList<Schedulable> pendingList = FXCollections.observableArrayList();
    private ObservableList<Schedulable> completedList = FXCollections.observableArrayList();

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
    private void CalendarButton(ActionEvent event){
        changeScene(event, "Calendar.fxml");
    }

    @FXML
    private void initialize() {
        ImageNotifications.setOnMouseClicked(e -> changeScene(e, "Notifications.fxml"));
        ImageProfile.setOnMouseClicked(e -> changeScene(e, "Profile.fxml"));

        TaskManager.loadFromFile();

        pendingList.clear();
        completedList.clear();

        for (Schedulable task : TaskManager.tasksList) {
            if (task.isStatus())
                completedList.add(task);
            else
                pendingList.add(task);
        }

        if (pendingTable != null)
            pendingTable.setItems(pendingList);

        if (completedTable != null)
            completedTable.setItems(completedList);

        if (pTaskCol != null) pTaskCol.setCellValueFactory(cell -> cell.getValue().taskNameProperty());
        if (pPriorityCol != null) pPriorityCol.setCellValueFactory(cell -> cell.getValue().priorityProperty());
        if (pExpirationCol != null) pExpirationCol.setCellValueFactory(cell -> cell.getValue().expirationDateProperty());

        if (cTaskCol != null) cTaskCol.setCellValueFactory(cell -> cell.getValue().taskNameProperty());
        if (cPriorityCol != null) cPriorityCol.setCellValueFactory(cell -> cell.getValue().priorityProperty());
        if (cExpirationCol != null) cExpirationCol.setCellValueFactory(cell -> cell.getValue().expirationDateProperty());
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
