package Logic.Services;

import Logic.SubMain.App;
import Logic.Models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AuthManager extends App {

    private static final String FILE_PATH = "C:\\Users\\Johan\\Desktop\\ProyectoFinal\\TrackTivity\\src\\main\\resources\\Data\\users.txt";

    public AuthManager() {
        users = new ArrayList<>();
        loadUsers();
    }

    public boolean validateData(String email, String password, String confirmPassword) {
        if (email == null || email.isEmpty()) return false;
        if (!email.matches("^(.+)@(.+)$")) return false;
        if (password == null || password.isEmpty()) return false;
        if (!password.equals(confirmPassword)) return false;
        return true;
    }

    public boolean emailExists(String email) {
        for (User u : users) {
            if (u.getEmail().equals(email)) return true;
        }
        return false;
    }

    public void registerUser(String email, String password) throws IOException {
        User user = new User(email, password);
        users.add(user);
        saveUsers();
    }

    @Override
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

    @Override
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

    public boolean login(String email, String password) {
        for (User u : users) {
            if (u.login(email, password)) return true;
        }
        return false;
    }
}