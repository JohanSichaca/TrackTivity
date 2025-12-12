package Logic.Services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

/**
 * Manages the storage and retrieval of tasks.
 * Tasks are saved in a text file with each field separated by a semicolon.
 */
public class TaskManager {

    public static ObservableList<Schedulable> tasksList = FXCollections.observableArrayList();

    private static final String FILE_PATH =
            "C:/Users/Johan/Desktop/ProyectoFinal/TrackTivity/src/main/resources/Data/tasks.txt";

    /**
     * Saves all tasks to a text file.
     * Each task is written as a semicolon-separated line.
     */
    public static void saveToFile() {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs();

            try (FileWriter writer = new FileWriter(file)) {
                for (Schedulable t : tasksList) {
                    writer.write(
                            t.getTaskName() + ";" +
                                    t.getDescription() + ";" +
                                    t.getCategory() + ";" +
                                    t.getPriority() + ";" +
                                    t.getSubject() + ";" +
                                    t.getExpirationDate() + ";" +
                                    t.isStatus() + "\n"
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads all tasks from the text file.
     * If the file does not exist, it is created automatically.
     */
    public static void loadFromFile() {
        tasksList.clear();

        try {
            File file = new File(FILE_PATH);

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                return;
            }

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] p = line.split(";");
                    if (p.length < 7) continue;

                    Schedulable task = new Schedulable(
                            p[0], p[1], p[2], p[3], p[4], p[5]
                    );
                    task.setStatus(Boolean.parseBoolean(p[6]));
                    tasksList.add(task);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
