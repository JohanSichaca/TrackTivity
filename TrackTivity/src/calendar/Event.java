package calendar;

// import java.util.Date → Used for handling event date.
import java.util.Date;

/**
 * Represents a calendar event.
 * Inherits from Schedulable and adds start and end times.
 */
public class Event extends Schedulable {
    private String startTime;
    private String endTime;

    /** Constructor of class Event */
    public Event(String title, Date date, String startTime, String endTime) {
        super(title, date);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Will display event details such as title, date, and duration.
     */
    public void showDetails() {}
}
