package Logic.Services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;

/**
 * Manages the list of events, including saving to and loading from a file.
 */
public class EventManager {

    /** Observable list of events for JavaFX bindings. */
    public static ObservableList<Event> eventsList = FXCollections.observableArrayList();

    /** Path to the events data file. */
    private static final String FILE_PATH =
            "C:/Users/Johan/Desktop/ProyectoFinal/TrackTivity/src/main/resources/Data/events.txt";

    /**
     * Saves all events in the eventsList to the file.
     */
    public static void saveToFile() {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs(); // Ensure parent directories exist
            FileWriter writer = new FileWriter(file);

            for (Event e : eventsList) {
                writer.write(
                        e.getName() + ";" +
                                e.getDescription() + ";" +
                                e.getDate() + ";" +
                                e.getStartTime() + ";" +
                                e.getEndTime() + "\n"
                );
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads events from the file into eventsList.
     * Creates the file if it does not exist.
     */
    public static void loadFromFile() {
        eventsList.clear();

        try {
            File file = new File(FILE_PATH);

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                return;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");

                if (parts.length < 5) continue;

                Event event = new Event(parts[0], parts[1], parts[2], parts[3], parts[4]);
                eventsList.add(event);
            }

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
