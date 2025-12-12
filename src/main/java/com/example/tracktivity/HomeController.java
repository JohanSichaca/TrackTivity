package com.example.tracktivity;

import Logic.Services.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.*;
import Logic.Services.Calendar;

public class HomeController {

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

    // ELEMENTOS NUEVOS DEL MINI CALENDARIO
    @FXML private Text miniYear;
    @FXML private Text miniMonth;
    @FXML private FlowPane miniCalendar;

    private ZonedDateTime today = ZonedDateTime.now();
    private ZonedDateTime focusDate = ZonedDateTime.now();

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

        drawMiniCalendar();
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


    // ------------------------------
    // MINI CALENDARIO (AGREGADO)
    // ------------------------------

    private void drawMiniCalendar() {
        if (miniCalendar == null) return;

        miniCalendar.getChildren().clear();

        miniYear.setText(String.valueOf(focusDate.getYear()));
        miniMonth.setText(focusDate.getMonth().toString());

        double width = miniCalendar.getPrefWidth();
        double height = miniCalendar.getPrefHeight();
        double spacingH = miniCalendar.getHgap();
        double spacingV = miniCalendar.getVgap();

        Map<Integer, List<Calendar>> eventsMap = loadEventsForMonth(focusDate);

        int maxDay = focusDate.toLocalDate().lengthOfMonth();
        int offset = focusDate.withDayOfMonth(1).getDayOfWeek().getValue();

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {

                StackPane cell = new StackPane();

                Rectangle box = new Rectangle();
                box.setFill(Color.TRANSPARENT);
                box.setStroke(Color.BLACK);
                box.setStrokeWidth(1);

                double cw = (width / 7) - spacingH;
                double ch = (height / 6) - spacingV;

                box.setWidth(cw);
                box.setHeight(ch);

                cell.getChildren().add(box);

                int calc = (col + 1) + (row * 7);
                int day = calc - offset;

                if (calc > offset && day <= maxDay) {
                    Text dateText = new Text(String.valueOf(day));
                    dateText.setTranslateY(-ch * 0.35);
                    cell.getChildren().add(dateText);

                    List<Calendar> events = eventsMap.get(day);
                    if (events != null) addMiniEvents(events, ch, cw, cell);

                    if (today.getYear() == focusDate.getYear()
                            && today.getMonth() == focusDate.getMonth()
                            && today.getDayOfMonth() == day) {
                        box.setStroke(Color.BLUE);
                        box.setStrokeWidth(2);
                    }
                }

                miniCalendar.getChildren().add(cell);
            }
        }
    }

    private void addMiniEvents(List<Calendar> events, double h, double w, StackPane cell) {
        VBox box = new VBox(1);
        box.setStyle("-fx-background-color: #d0d0d0; -fx-padding: 2;");
        box.setPrefWidth(w * 0.9);
        box.setPrefHeight(h * 0.45);
        box.setTranslateY(h * 0.12);

        int limit = Math.min(events.size(), 2);

        for (int i = 0; i < limit; i++) {
            Calendar ev = events.get(i);
            String t = ev.getClientName() + " " + ev.getDate().toLocalTime();
            if (t.length() > 18) t = t.substring(0, 18) + "...";
            box.getChildren().add(new Text(t));
        }

        if (events.size() > 2) {
            box.getChildren().add(new Text("..."));
        }

        cell.getChildren().add(box);
    }

    private Map<Integer, List<Calendar>> loadEventsForMonth(ZonedDateTime date) {
        EventManager.loadFromFile();

        List<Calendar> result = new ArrayList<>();

        for (Event e : EventManager.eventsList) {
            try {
                LocalDate d = LocalDate.parse(e.getDate());

                ZonedDateTime dt = ZonedDateTime.of(
                        d.getYear(),
                        d.getMonthValue(),
                        d.getDayOfMonth(),
                        Integer.parseInt(e.getStartTime().split(":")[0]),
                        Integer.parseInt(e.getStartTime().split(":")[1]),
                        0, 0,
                        date.getZone()
                );

                result.add(new Calendar(dt, e.getName(), 0));

            } catch (Exception ignored) {}
        }

        Map<Integer, List<Calendar>> map = new HashMap<>();
        for (Calendar c : result) {
            int d = c.getDate().getDayOfMonth();
            map.computeIfAbsent(d, k -> new ArrayList<>()).add(c);
        }
        return map;
    }
}
