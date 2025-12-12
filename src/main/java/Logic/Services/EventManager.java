package Logic.Services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;

public class EventManager {

    public static ObservableList<Event> eventsList = FXCollections.observableArrayList();

    private static final String FILE_PATH =
            "C:/Users/Johan/Desktop/ProyectoFinal/TrackTivity/src/main/resources/Data/events.txt";

    public static void saveToFile() {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs();
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
                String[] p = line.split(";");

                if (p.length < 5) continue;

                Event event = new Event(p[0], p[1], p[2], p[3], p[4]);
                eventsList.add(event);
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
