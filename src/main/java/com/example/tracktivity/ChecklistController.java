package com.example.tracktivity;

import Logic.Services.Schedulable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class ChecklistController {

    @FXML private TableView<Schedulable> tableView;
    @FXML private TableColumn<Schedulable, String> taskNameCol;
    @FXML private TableColumn<Schedulable, String> descriptionCol;
    @FXML private TableColumn<Schedulable, String> categoryCol;
    @FXML private TableColumn<Schedulable, String> priorityCol;
    @FXML private TableColumn<Schedulable, String> subjectCol;
    @FXML private TableColumn<Schedulable, String> expirationDateCol;
    @FXML private TableColumn<Schedulable, Boolean> statusCol;
    @FXML private ImageView ImageProfile;
    @FXML private ImageView ImageNotifications;

    private ObservableList<Schedulable> tasksList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        tableView.setItems(tasksList);
        tableView.setEditable(true);

        taskNameCol.setCellValueFactory(cell -> cell.getValue().taskNameProperty());
        descriptionCol.setCellValueFactory(cell -> cell.getValue().descriptionProperty());
        categoryCol.setCellValueFactory(cell -> cell.getValue().categoryProperty());
        priorityCol.setCellValueFactory(cell -> cell.getValue().priorityProperty());
        subjectCol.setCellValueFactory(cell -> cell.getValue().subjectProperty());
        expirationDateCol.setCellValueFactory(cell -> cell.getValue().expirationDateProperty());
        statusCol.setCellValueFactory(cell -> cell.getValue().statusProperty());
        statusCol.setCellFactory(CheckBoxTableCell.forTableColumn(statusCol));
    }

    public void addTask(Schedulable task) {
        tasksList.add(task);
    }

    private void changeScene(javafx.event.Event event, String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tracktivity/" + fxml));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Botones de navegación
    @FXML private void HomeButton(ActionEvent event){ changeScene(event, "Home.fxml"); }
    @FXML private void CalendarButton(ActionEvent event){ changeScene(event, "Calendar.fxml"); }
    @FXML private void EventsButton(ActionEvent event){ changeScene(event, "Events.fxml"); }
    @FXML private void DashboardButton(ActionEvent event){ changeScene(event, "Dashboard.fxml"); }
    @FXML private void AddTaskButton(ActionEvent event){ changeScene(event, "NewTask.fxml"); }
    @FXML private void NewListButton(ActionEvent event){ changeScene(event, "NewList.fxml"); }
}
