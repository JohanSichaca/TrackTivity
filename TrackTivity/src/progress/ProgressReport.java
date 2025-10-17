package progress;

/**
 * Displays a summary report of the user's progress.
 */
public class ProgressReport {
    private double daily;
    private double weekly;
    private double monthly;

    /** Constructor of class ProgressReport */
    public ProgressReport(double daily, double weekly, double monthly) {
        this.daily = daily;
        this.weekly = weekly;
        this.monthly = monthly;
    }

    /**
     * Will generate and display a visual progress summary.
     */
    public void showSummary() {}
}
