package com.example.tracktivity;

import Logic.Services.Event;
import Logic.Services.EventManager;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Controls the Events screen.
 * Loads, displays, and deletes events.
 */
public class EventsController {

    @FXML private TableView<Event> tableView;
    @FXML private TableColumn<Event, String> nameCol;
    @FXML private TableColumn<Event, String> descriptionCol;
    @FXML private TableColumn<Event, String> dateCol;
    @FXML private TableColumn<Event, String> startTimeCol;
    @FXML private TableColumn<Event, String> endTimeCol;
    @FXML private ImageView ImageNotifications;

    /**
     * Initializes table and loads events.
     */
    @FXML
    private void initialize() {
        EventManager.loadFromFile();
        tableView.setItems(EventManager.eventsList);

        nameCol.setCellValueFactory(cell -> cell.getValue().nameProperty());
        descriptionCol.setCellValueFactory(cell -> cell.getValue().descriptionProperty());
        dateCol.setCellValueFactory(cell -> cell.getValue().dateProperty());
        startTimeCol.setCellValueFactory(cell -> cell.getValue().startTimeProperty());
        endTimeCol.setCellValueFactory(cell -> cell.getValue().endTimeProperty());

        ImageNotifications.setOnMouseClicked(e -> changeScene(e, "Notifications.fxml"));
    }

    /**
     * Deletes the selected event.
     */
    @FXML
    private void deleteEvent(ActionEvent event) {
        Event selected = tableView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            System.out.println("No event selected");
            return;
        }

        EventManager.eventsList.remove(selected);
        EventManager.saveToFile();
        tableView.refresh();
    }

    /**
     * Changes the current scene.
     */
    private void changeScene(javafx.event.Event event, String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/tracktivity/" + fxml)
            );
            Parent root = loader.load();
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
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

    /** Opens Checklist. */
    @FXML private void ChecklistButton(ActionEvent event){ changeScene(event, "Checklist.fxml"); }

    /** Opens Dashboard. */
    @FXML private void DashboardButton(ActionEvent event){ changeScene(event, "Dashboard.fxml"); }

    /** Opens event creation form. */
    @FXML private void AddEventButton(ActionEvent event){ changeScene(event, "NewEvent.fxml"); }
}
