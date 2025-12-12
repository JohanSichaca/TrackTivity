package com.example.tracktivity;

import Logic.Services.AuthManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Handles user login flow.
 * Validates credentials and opens Home view.
 */
public class LoginController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;

    private AuthManager authManager = new AuthManager();

    /**
     * Attempts to log the user in.
     * Checks credentials and navigates to Home.
     */
    @FXML
    private void loginUser(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        try {
            if (email.isEmpty() || password.isEmpty()) {
                showMessage("Enter email and password.");
                return;
            }

            if (authManager.login(email, password)) {
                showMessage("Login successful.");

                FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } else {
                showMessage("Invalid email or password.");
            }

        } catch (IOException e) {
            showMessage("Error: " + e.getMessage());
        }
    }

    /**
     * Opens the Register screen.
     */
    @FXML
    private void registerButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Register.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays a pop-up message.
     */
    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
