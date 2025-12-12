package com.example.tracktivity;

import Logic.Services.Schedulable;
import Logic.Services.TaskManager;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

/**
 * Controls the Checklist screen.
 * Displays, updates, and deletes tasks.
 */
public class ChecklistController {

    @FXML private TableView<Schedulable> tableView;
    @FXML private TableColumn<Schedulable, String> taskNameCol;
    @FXML private TableColumn<Schedulable, String> descriptionCol;
    @FXML private TableColumn<Schedulable, String> categoryCol;
    @FXML private TableColumn<Schedulable, String> priorityCol;
    @FXML private TableColumn<Schedulable, String> subjectCol;
    @FXML private TableColumn<Schedulable, String> expirationDateCol;
    @FXML private TableColumn<Schedulable, Boolean> statusCol;

    @FXML private ImageView ImageNotifications;

    /**
     * Deletes the selected task.
     */
    @FXML
    private void deleteTask(ActionEvent event) {
        Schedulable selected = tableView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            System.out.println("No task selected");
            return;
        }

        TaskManager.tasksList.remove(selected);
        TaskManager.saveToFile();
        System.out.println("Deleted task");
    }

    /**
     * Initializes table and loads tasks.
     */
    @FXML
    private void initialize() {

        TaskManager.loadFromFile();

        tableView.setItems(TaskManager.tasksList);
        tableView.setEditable(true);

        taskNameCol.setCellValueFactory(cell -> cell.getValue().taskNameProperty());
        descriptionCol.setCellValueFactory(cell -> cell.getValue().descriptionProperty());
        categoryCol.setCellValueFactory(cell -> cell.getValue().categoryProperty());
        priorityCol.setCellValueFactory(cell -> cell.getValue().priorityProperty());
        subjectCol.setCellValueFactory(cell -> cell.getValue().subjectProperty());
        expirationDateCol.setCellValueFactory(cell -> cell.getValue().expirationDateProperty());

        statusCol.setCellValueFactory(cell -> cell.getValue().statusProperty());
        statusCol.setCellFactory(CheckBoxTableCell.forTableColumn(statusCol));

        ImageNotifications.setOnMouseClicked(event -> changeScene(event, "Notifications.fxml"));
    }

    /**
     * Changes the current scene.
     */
    private void changeScene(javafx.event.Event event, String fxml) {
        try {
            TaskManager.saveToFile();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tracktivity/" + fxml));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Opens Home. */
    @FXML private void HomeButton(ActionEvent event){ changeScene(event, "Home.fxml"); }

    /** Opens Calendar. */
    @FXML private void CalendarButton(ActionEvent event){ changeScene(event, "Calendar.fxml"); }

    /** Opens Events. */
    @FXML private void EventsButton(ActionEvent event){ changeScene(event, "Events.fxml"); }

    /** Opens Dashboard. */
    @FXML private void DashboardButton(ActionEvent event){ changeScene(event, "Dashboard.fxml"); }

    /** Opens task creation form. */
    @FXML private void AddTaskButton(ActionEvent event){ changeScene(event, "NewTask.fxml"); }
}
