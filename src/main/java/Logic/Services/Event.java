package Logic.Services;

import javafx.beans.property.*;

/**
 * Represents an event with a name, description, date, start time, and end time.
 */
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

    /** Returns the name property for JavaFX bindings. */
    public StringProperty nameProperty() { return name; }

    /** Returns the description property for JavaFX bindings. */
    public StringProperty descriptionProperty() { return description; }

    /** Returns the date property for JavaFX bindings. */
    public StringProperty dateProperty() { return date; }

    /** Returns the start time property for JavaFX bindings. */
    public StringProperty startTimeProperty() { return startTime; }

    /** Returns the end time property for JavaFX bindings. */
    public StringProperty endTimeProperty() { return endTime; }

    /** Returns the name value. */
    public String getName() { return name.get(); }

    /** Returns the description value. */
    public String getDescription() { return description.get(); }

    /** Returns the date value. */
    public String getDate() { return date.get(); }

    /** Returns the start time value. */
    public String getStartTime() { return startTime.get(); }

    /** Returns the end time value. */
    public String getEndTime() { return endTime.get(); }
}
