package calendar;

// import java.util.Date → Used to manage start or repetition date of a habit.
import java.util.Date;

/**
 * Represents a recurring habit maintained by the user.
 * Extends Task to reuse common attributes.
 */
public class Habit extends Task {
    private String frequency;
    private int streak;

    /** Constructor of class Habit */
    public Habit(String title, Date date, String frequency, int streak) {
        super(title, date, "Active", 0);
        this.frequency = frequency;
        this.streak = streak;
    }

    /**
     * Will reset the habit streak when the routine is broken.
     */
    public void resetStreak() {}

    /**
     * Will display habit information such as frequency and streak count.
     */
    public void showDetails() {}
}
