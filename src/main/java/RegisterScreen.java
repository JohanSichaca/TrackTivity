import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class RegisterScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Layout principal
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f5f5f5;");

        // Header
        HBox header = createHeader();
        root.setTop(header);

        // Contenido central con formulario de registro
        VBox center = createRegisterForm();
        root.setCenter(center);

        // Footer
        HBox footer = createFooter();
        root.setBottom(footer);

        Scene scene = new Scene(root, 900, 600);
        primaryStage.setTitle("Trackxitivity - Register");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createHeader() {
        HBox header = new HBox();
        header.setPadding(new Insets(15, 20, 15, 20));
        header.setAlignment(Pos.CENTER_LEFT);
        header.setStyle("-fx-background-color: white; -fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0;");

        // Logo
        Label logo = new Label("⊙");
        logo.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        logo.setStyle("-fx-text-fill: black;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Botones del header
        Button registerBtn = createHeaderButton("Register", true);
        Button loginBtn = createHeaderButton("Log-in", false);

        HBox buttonBox = new HBox(10, registerBtn, loginBtn);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);

        header.getChildren().addAll(logo, spacer, buttonBox);
        return header;
    }

    private Button createHeaderButton(String text, boolean outlined) {
        Button btn = new Button(text);
        btn.setPrefWidth(80);
        btn.setPrefHeight(35);
        btn.setFont(Font.font("Arial", FontWeight.NORMAL, 13));

        if (outlined) {
            btn.setStyle("-fx-background-color: white; " +
                    "-fx-border-color: #d4a574; " +
                    "-fx-border-width: 2; " +
                    "-fx-border-radius: 20; " +
                    "-fx-background-radius: 20; " +
                    "-fx-text-fill: black;");
        } else {
            btn.setStyle("-fx-background-color: #d4a574; " +
                    "-fx-border-radius: 20; " +
                    "-fx-background-radius: 20; " +
                    "-fx-text-fill: white;");
        }

        return btn;
    }

    private VBox createRegisterForm() {
        VBox center = new VBox();
        center.setAlignment(Pos.CENTER);
        center.setPadding(new Insets(50));

        // Contenedor del formulario con borde
        VBox formContainer = new VBox(18);
        formContainer.setAlignment(Pos.CENTER);
        formContainer.setPrefWidth(350);
        formContainer.setPadding(new Insets(40, 30, 40, 30));
        formContainer.setStyle("-fx-background-color: white; " +
                "-fx-border-color: black; " +
                "-fx-border-width: 2.5; " +
                "-fx-border-radius: 25; " +
                "-fx-background-radius: 25;");

        // Título REGISTER
        Label title = new Label("REGISTER");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setStyle("-fx-text-fill: black;");

        // Campo de email
        TextField emailField = createStyledTextField("Enter an e-mail");

        // Campo de contraseña
        PasswordField passwordField = createStyledPasswordField("Enter a password");

        // Campo de confirmar contraseña
        PasswordField confirmPasswordField = createStyledPasswordField("Confirm the password");

        // Botón Register
        Button registerButton = new Button("Register");
        registerButton.setPrefWidth(280);
        registerButton.setPrefHeight(40);
        registerButton.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        registerButton.setStyle("-fx-background-color: #fcd5b5; " +
                "-fx-border-color: #d4a574; " +
                "-fx-border-width: 1.5; " +
                "-fx-border-radius: 20; " +
                "-fx-background-radius: 20; " +
                "-fx-text-fill: black;");


        registerButton.setOnMouseEntered(e ->
                registerButton.setStyle("-fx-background-color: #fcc79b; " +
                        "-fx-border-color: #d4a574; " +
                        "-fx-border-width: 1.5; " +
                        "-fx-border-radius: 20; " +
                        "-fx-background-radius: 20; " +
                        "-fx-text-fill: black; " +
                        "-fx-cursor: hand;")
        );

        registerButton.setOnMouseExited(e ->
                registerButton.setStyle("-fx-background-color: #fcd5b5; " +
                        "-fx-border-color: #d4a574; " +
                        "-fx-border-width: 1.5; " +
                        "-fx-border-radius: 20; " +
                        "-fx-background-radius: 20; " +
                        "-fx-text-fill: black;")
        );


        HBox loginLink = new HBox(5);
        loginLink.setAlignment(Pos.CENTER);

        Label haveAccount = new Label("I have account");
        haveAccount.setFont(Font.font("Arial", 12));
        haveAccount.setStyle("-fx-text-fill: #666666;");

        Hyperlink loginHyperlink = new Hyperlink("Log-in");
        loginHyperlink.setFont(Font.font("Arial", 12));
        loginHyperlink.setStyle("-fx-text-fill: #d4a574; -fx-underline: true;");
        loginHyperlink.setBorder(Border.EMPTY);
        loginHyperlink.setPadding(new Insets(0));

        loginLink.getChildren().addAll(haveAccount, loginHyperlink);

       
        Region spacer1 = new Region();
        spacer1.setPrefHeight(10);

        Region spacer2 = new Region();
        spacer2.setPrefHeight(5);

        formContainer.getChildren().addAll(
                title,
                spacer1,
                emailField,
                passwordField,
                confirmPasswordField,
                spacer2,
                registerButton,
                loginLink
        );

        center.getChildren().add(formContainer);
        return center;
    }

    private TextField createStyledTextField(String prompt) {
        TextField field = new TextField();
        field.setPromptText(prompt);
        field.setPrefWidth(280);
        field.setPrefHeight(40);
        field.setFont(Font.font("Arial", 13));
        field.setStyle("-fx-background-color: white; " +
                "-fx-border-color: #333333; " +
                "-fx-border-width: 1.5; " +
                "-fx-border-radius: 20; " +
                "-fx-background-radius: 20; " +
                "-fx-padding: 0 15 0 15;");

        // Efecto focus
        field.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                field.setStyle("-fx-background-color: white; " +
                        "-fx-border-color: #d4a574; " +
                        "-fx-border-width: 2; " +
                        "-fx-border-radius: 20; " +
                        "-fx-background-radius: 20; " +
                        "-fx-padding: 0 15 0 15;");
            } else {
                field.setStyle("-fx-background-color: white; " +
                        "-fx-border-color: #333333; " +
                        "-fx-border-width: 1.5; " +
                        "-fx-border-radius: 20; " +
                        "-fx-background-radius: 20; " +
                        "-fx-padding: 0 15 0 15;");
            }
        });

        return field;
    }

    private PasswordField createStyledPasswordField(String prompt) {
        PasswordField field = new PasswordField();
        field.setPromptText(prompt);
        field.setPrefWidth(280);
        field.setPrefHeight(40);
        field.setFont(Font.font("Arial", 13));
        field.setStyle("-fx-background-color: white; " +
                "-fx-border-color: #333333; " +
                "-fx-border-width: 1.5; " +
                "-fx-border-radius: 20; " +
                "-fx-background-radius: 20; " +
                "-fx-padding: 0 15 0 15;");

        // Efecto focus
        field.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                field.setStyle("-fx-background-color: white; " +
                        "-fx-border-color: #d4a574; " +
                        "-fx-border-width: 2; " +
                        "-fx-border-radius: 20; " +
                        "-fx-background-radius: 20; " +
                        "-fx-padding: 0 15 0 15;");
            } else {
                field.setStyle("-fx-background-color: white; " +
                        "-fx-border-color: #333333; " +
                        "-fx-border-width: 1.5; " +
                        "-fx-border-radius: 20; " +
                        "-fx-background-radius: 20; " +
                        "-fx-padding: 0 15 0 15;");
            }
        });

        return field;
    }

    private HBox createFooter() {
        HBox footer = new HBox();
        footer.setPadding(new Insets(20));
        footer.setAlignment(Pos.CENTER_RIGHT);
        footer.setStyle("-fx-background-color: white; -fx-border-color: #e0e0e0; -fx-border-width: 1 0 0 0;");

        Label brandLabel = new Label("TRACKXITIVITY");
        brandLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        brandLabel.setStyle("-fx-text-fill: black; -fx-letter-spacing: 2;");

        footer.getChildren().add(brandLabel);
        return footer;
    }

    public static void main(String[] args) {
        launch(args);
    }
}