package com.example.tracktivity;

import Logic.Services.Calendar;
import Logic.Services.Event;
import Logic.Services.EventManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CalendarController implements Initializable {

    @FXML
    private ImageView ImageProfile;

    @FXML
    private ImageView ImageNotifications;

    @FXML
    private Text year;

    @FXML
    private Text month;

    @FXML
    private FlowPane calendar;

    private ZonedDateTime dateFocus;
    private ZonedDateTime today;

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
    private void AddEventButton(ActionEvent event) {
        changeScene(event, "NewEvent.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ImageNotifications.setOnMouseClicked(e -> changeScene(e, "Notifications.fxml"));
        ImageProfile.setOnMouseClicked(e -> changeScene(e, "Profile.fxml"));
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        drawCalendar();
    }

    @FXML
    void backOneMonth(ActionEvent event) {
        dateFocus = dateFocus.minusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    @FXML
    void forwardOneMonth(ActionEvent event) {
        dateFocus = dateFocus.plusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    private void drawCalendar() {
        year.setText(String.valueOf(dateFocus.getYear()));
        month.setText(String.valueOf(dateFocus.getMonth()));
        double calendarWidth = calendar.getPrefWidth();
        double calendarHeight = calendar.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = calendar.getHgap();
        double spacingV = calendar.getVgap();
        Map<Integer, List<Calendar>> calendarMap = getCalendarActivitiesMonth(dateFocus);
        int monthMaxDate = dateFocus.toLocalDate().lengthOfMonth();
        int dateOffset = dateFocus.withDayOfMonth(1).getDayOfWeek().getValue();
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                StackPane stackPane = new StackPane();
                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeWidth(strokeWidth);
                double cellWidth = (calendarWidth / 7) - strokeWidth - spacingH;
                double cellHeight = (calendarHeight / 6) - strokeWidth - spacingV;
                rectangle.setWidth(cellWidth);
                rectangle.setHeight(cellHeight);
                stackPane.getChildren().add(rectangle);
                int calculatedDate = (col + 1) + (7 * row);
                int currentDate = calculatedDate - dateOffset;
                if (calculatedDate > dateOffset && currentDate <= monthMaxDate) {
                    Text dateText = new Text(String.valueOf(currentDate));
                    dateText.setTranslateY(-cellHeight * 0.35);
                    stackPane.getChildren().add(dateText);
                    List<Calendar> activities = calendarMap.get(currentDate);
                    if (activities != null) createCalendarActivity(activities, cellHeight, cellWidth, stackPane);
                    if (today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate) {
                        rectangle.setStroke(Color.BLUE);
                        rectangle.setStrokeWidth(2);
                    }
                }
                calendar.getChildren().add(stackPane);
            }
        }
    }

    private void createCalendarActivity(List<Calendar> activities, double rectangleHeight, double rectangleWidth, StackPane stackPane) {
        VBox activityBox = new VBox(2);
        activityBox.setStyle("-fx-background-color: #C0C0C0; -fx-padding: 2;");
        activityBox.setMaxWidth(rectangleWidth * 0.9);
        activityBox.setMinWidth(rectangleWidth * 0.9);
        activityBox.setPrefWidth(rectangleWidth * 0.9);
        activityBox.setMaxHeight(rectangleHeight * 0.55);
        activityBox.setPrefHeight(rectangleHeight * 0.55);
        activityBox.setTranslateY(rectangleHeight * 0.15);
        for (int k = 0; k < activities.size(); k++) {
            if (k >= 2) {
                Text more = new Text("...");
                more.wrappingWidthProperty().set(rectangleWidth * 0.9);
                more.setOnMouseClicked(e -> System.out.println(activities));
                activityBox.getChildren().add(more);
                break;
            }
            Calendar act = activities.get(k);
            String textValue = act.getClientName() + ", " + act.getDate().toLocalTime();
            if (textValue.length() > 20) textValue = textValue.substring(0, 20) + "...";
            final String finalTextValue = textValue;
            Text text = new Text(finalTextValue);
            text.wrappingWidthProperty().set(rectangleWidth * 0.9);
            text.setOnMouseClicked(e -> System.out.println(finalTextValue));
            activityBox.getChildren().add(text);
        }
        stackPane.getChildren().add(activityBox);
    }

    private Map<Integer, List<Calendar>> createCalendarMap(List<Calendar> activities) {
        Map<Integer, List<Calendar>> map = new HashMap<>();
        for (Calendar activity : activities) {
            int day = activity.getDate().getDayOfMonth();
            map.putIfAbsent(day, new ArrayList<>());
            map.get(day).add(activity);
        }
        return map;
    }

    private Map<Integer, List<Calendar>> getCalendarActivitiesMonth(ZonedDateTime dateFocus) {
        List<Calendar> activities = new ArrayList<>();

        EventManager.loadFromFile();

        int year = dateFocus.getYear();
        int month = dateFocus.getMonth().getValue();

        for (Logic.Services.Event e : EventManager.eventsList) {
            try {

                // ---------- REPARACIÓN DE FECHA ----------
                String rawDate = e.getDate().trim();
                LocalDate eventDate;

                if (rawDate.matches("\\d{2}/\\d{2}/\\d{4}")) {                 // 12/12/2025
                    DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    eventDate = LocalDate.parse(rawDate, f);

                } else if (rawDate.matches("\\d{4}-\\d{2}-\\d{2}")) {          // 2025-12-12
                    eventDate = LocalDate.parse(rawDate);

                } else if (rawDate.matches("\\d{2} \\d{2}")) {                 // 12 12  (día-mes sin año)
                    String[] p = rawDate.split(" ");
                    eventDate = LocalDate.of(year, Integer.parseInt(p[1]), Integer.parseInt(p[0]));

                } else {
                    System.out.println("Fecha inválida: " + rawDate);
                    continue;
                }


                // ---------- REPARACIÓN DE HORA ----------
                String rawTime = e.getStartTime().trim();
                LocalTime startTime;

                if (rawTime.matches("\\d{2}:\\d{2}")) {                         // 15:00
                    startTime = LocalTime.parse(rawTime);

                } else if (rawTime.matches("\\d{1}:\\d{2}")) {                  // 3:00  → 03:00
                    startTime = LocalTime.parse("0" + rawTime);

                } else if (rawTime.matches("\\d{1,2}")) {                       // 3  → 03:00
                    startTime = LocalTime.of(Integer.parseInt(rawTime), 0);

                } else {
                    System.out.println("Hora inválida: " + rawTime);
                    continue;
                }


                ZonedDateTime fullDate = ZonedDateTime.of(eventDate, startTime, dateFocus.getZone());

                if (fullDate.getYear() == year && fullDate.getMonthValue() == month) {
                    activities.add(new Calendar(fullDate, e.getName(), e.hashCode()));
                }

            } catch (Exception ex) {
                System.out.println("Error in: " + e.getDate() + " " + e.getStartTime());
            }
        }

        return createCalendarMap(activities);
    }

}
