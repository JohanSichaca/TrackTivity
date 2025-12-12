package Logic.Services;

import javafx.beans.property.*;

public class Event {

    private final StringProperty name;
    private final StringProperty description;
    private final StringProperty date;
    private final StringProperty startTime;
    private final StringProperty endTime;

    public Event(String name, String description, String date, String startTime, String endTime) {
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.date = new SimpleStringProperty(date);
        this.startTime = new SimpleStringProperty(startTime);
        this.endTime = new SimpleStringProperty(endTime);
    }

    public StringProperty nameProperty() { return name; }
    public StringProperty descriptionProperty() { return description; }
    public StringProperty dateProperty() { return date; }
    public StringProperty startTimeProperty() { return startTime; }
    public StringProperty endTimeProperty() { return endTime; }

    public String getName() { return name.get(); }
    public String getDescription() { return description.get(); }
    public String getDate() { return date.get(); }
    public String getStartTime() { return startTime.get(); }
    public String getEndTime() { return endTime.get(); }
}
