package Logic.Services;

import Logic.Models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages user registration, login, and persistence.
 */
public class AuthManager {

    private static final String FILE_PATH = "C:\\Users\\Johan\\Desktop\\ProyectoFinal\\TrackTivity\\src\\main\\resources\\Data\\users.txt";
    private List<User> users;

    public AuthManager() {
        users = new ArrayList<>();
        loadUsers();
    }

    /**
     * Validates email and password data.
     */
    public boolean validateData(String email, String password, String confirmPassword) {
        if (email == null || email.isEmpty()) return false;
        if (!email.matches("^(.+)@(.+)$")) return false;
        if (password == null || password.isEmpty()) return false;
        return password.equals(confirmPassword);
    }

    /**
     * Checks if an email is already registered.
     */
    public boolean emailExists(String email) {
        return users.stream().anyMatch(u -> u.getEmail().equals(email));
    }

    /**
     * Registers a new user and saves to file.
     */
    public void registerUser(String email, String password) throws IOException {
        users.add(new User(email, password));
        saveUsers();
    }

    /**
     * Loads users from the file.
     */
    public void loadUsers() {
        users.clear();
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    users.add(new User(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves users to the file.
     */
    public void saveUsers() {
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (User u : users) {
                writer.write(u.getEmail() + ";" + u.getPassword() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if credentials match any registered user.
     */
    public boolean login(String email, String password) {
        return users.stream().anyMatch(u -> u.login(email, password));
    }
}
