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

/**
 * Controls the Dashboard screen.
 * Displays pending and completed tasks.
 */
public class DashboardController {

    @FXML private ImageView ImageProfile;
    @FXML private ImageView ImageNotifications;

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

    /**
     * Changes the current scene.
     */
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

    /** Opens Home screen. */
    @FXML private void HomeButton(ActionEvent event){ changeScene(event, "Home.fxml"); }

    /** Initializes the dashboard. */
    @FXML
    private void initialize() {
        ImageNotifications.setOnMouseClicked(e -> changeScene(e, "Notifications.fxml"));

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

    /** Opens Calendar screen. */
    @FXML private void CalendarButton(ActionEvent event){ changeScene(event, "Calendar.fxml"); }

    /** Opens Events screen. */
    @FXML private void EventsButton(ActionEvent event){ changeScene(event, "Events.fxml"); }

    /** Opens Checklist screen. */
    @FXML private void ChecklistButton(ActionEvent event){ changeScene(event, "Checklist.fxml"); }

    /** Opens New Task screen. */
    @FXML private void AddTaskButton(ActionEvent event){ changeScene(event, "NewTask.fxml"); }
}
