package notifications;

// import java.time.LocalDateTime → Used to register the exact date and time a notification is created.
import java.time.LocalDateTime;

/**
 * Represents a system notification sent to the user.
 */
public class Notification {
    private String message;
    private LocalDateTime dateTime;
    private boolean isRead;

    /** Constructor of class Notification */
    public Notification(String message) {
        this.message = message;
        this.dateTime = LocalDateTime.now();
        this.isRead = false;
    }

    /**
     * Will mark the notification as read in future versions.
     */
    public void markAsRead() {}
}
