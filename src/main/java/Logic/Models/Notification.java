package Logic.Models;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a simple notification with name, description, and date.
 * Can load notifications for today from resource files.
 */
public class Notification {
    private String name;
    private String description;
    private LocalDate date;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Notification(String name, String description, LocalDate date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public LocalDate getDate() { return date; }

    @Override
    public String toString() { return name + " - " + date; }

    /**
     * Loads notifications for today from events.txt and tasks.txt.
     */
    public static List<Notification> loadTodayNotifications() {
        List<Notification> list = new ArrayList<>();
        LocalDate today = LocalDate.now();

        loadFromResource("/data/events.txt", 3, list, today);
        loadFromResource("/data/tasks.txt", 6, list, today);

        return list;
    }

    private static void loadFromResource(String resourcePath, int dateIndex, List<Notification> list, LocalDate today) {
        try (InputStream is = Notification.class.getResourceAsStream(resourcePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= dateIndex) {
                    String name = parts[0];
                    String description = parts[1];
                    LocalDate date = LocalDate.parse(parts[dateIndex - 1], formatter);
                    if (date.equals(today)) {
                        list.add(new Notification(name, description, date));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

