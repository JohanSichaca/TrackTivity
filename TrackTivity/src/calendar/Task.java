package calendar;

// import java.util.Date → Used to represent the due date of a task.
import java.util.Date;

/**
 * Represents a task with a deadline and progress.
 */
public class Task extends Schedulable {
    private String status;
    private float progress;

    /** Constructor of class Task */
    public Task(String title, Date date, String status, float progress) {
        super(title, date);
        this.status = status;
        this.progress = progress;
    }

    /**
     * Will update the progress percentage and completion state.
     */
    public void updateProgress(float newProgress) {}

    /**
     * Will display detailed task information.
     */
    public void showDetails() {}
}
