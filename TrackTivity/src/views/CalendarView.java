package views;

/**
 * Abstract base class for all calendar views (day, week, month).
 * Allows polymorphic display behavior across subclasses.
 */
public abstract class CalendarView {
    protected String viewName;

    /** Constructor of class CalendarView */
    public CalendarView(String viewName) {
        this.viewName = viewName;
    }

    /**
     * Will render the corresponding calendar view on screen.
     */
    public abstract void display();
}
