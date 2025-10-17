package calendar;

/**
 * Represents an item in a checklist used within a task.
 */
public class Checkitem {
    private String title;
    private boolean completed;

    /** Constructor of class CheckItem */
    public Checkitem(String title) {
        this.title = title;
        this.completed = false;
    }

    /**
     * Will mark this checklist item as completed.
     */
    public void markAsCompleted() {}
}
