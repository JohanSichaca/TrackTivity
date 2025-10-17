package calendar;

// import java.util.Date → Used for managing basic date values in schedulable elements.
import java.util.Date;

/**
 * Abstract base class for all schedulable calendar elements.
 * Defines shared attributes and methods for tasks, events, and habits.
 */
public abstract class Schedulable {
    protected String title;
    protected Date date;

    /** Constructor of class Schedulable */
    public Schedulable(String title, Date date) {
        this.title = title;
        this.date = date;
    }

    /**
     * Will display detailed information of the schedulable item.
     */
    public abstract void showDetails();
}
