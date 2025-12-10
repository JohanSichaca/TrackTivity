package Logic.Services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

public class TaskManager {

    public static ObservableList<Schedulable> tasksList = FXCollections.observableArrayList();

    // Ruta fija donde quieres guardar el archivo
    private static final String FILE_PATH =
            "C:/Users/Johan/Desktop/ProyectoFinal/TrackTivity/src/main/resources/Data/tasks.txt";

    public static void saveToFile() {
        try {

            File file = new File(FILE_PATH);

            // Crea la carpeta Data si no existe
            file.getParentFile().mkdirs();

            FileWriter writer = new FileWriter(file);

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

            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadFromFile() {

        tasksList.clear();

        try {

            File file = new File(FILE_PATH);

            if (!file.exists()) {
                System.out.println("No tasks file found, creating a new one...");
                file.getParentFile().mkdirs();
                file.createNewFile();
                return;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
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

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}