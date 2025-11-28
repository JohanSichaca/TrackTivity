import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class tracktivitybelog extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Layout principal
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f5f5f5;");

        // Header con logo y botones
        HBox header = createHeader();
        root.setTop(header);

        // Contenido central
        VBox center = createCenterContent();
        root.setCenter(center);

        // Footer
        HBox footer = createFooter();
        root.setBottom(footer);

        Scene scene = new Scene(root, 900, 600);
        primaryStage.setTitle("Trackxitivity");
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
        Button registerBtn = createHeaderButton("Register", false);
        Button loginBtn = createHeaderButton("Log-in", true);

        HBox buttonBox = new HBox(10, registerBtn, loginBtn);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);

        header.getChildren().addAll(logo, spacer, buttonBox);
        return header;
    }

    private Button createHeaderButton(String text, boolean filled) {
        Button btn = new Button(text);
        btn.setPrefWidth(80);
        btn.setPrefHeight(35);
        btn.setFont(Font.font("Arial", FontWeight.NORMAL, 13));

        if (filled) {
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

    private VBox createCenterContent() {
        VBox center = new VBox(40);
        center.setAlignment(Pos.CENTER);
        center.setPadding(new Insets(50));

        // Título
        Label welcome = new Label("WELCOME");
        welcome.setFont(Font.font("Arial", FontWeight.BOLD, 48));
        welcome.setStyle("-fx-text-fill: black;");

        Label subtitle = new Label("Organize and schedule your activities");
        subtitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        subtitle.setStyle("-fx-text-fill: black; -fx-underline: true;");

        // Contenedor de cards
        HBox cardsBox = new HBox(40);
        cardsBox.setAlignment(Pos.CENTER);

        VBox organizeCard = createCard("Organize", "📅");
        VBox programCard = createCard("Program", "📦");
        VBox controlCard = createCard("Control", "📋");

        cardsBox.getChildren().addAll(organizeCard, programCard, controlCard);

        // Botón Log-in central
        Button loginBtn = new Button("Log-in");
        loginBtn.setPrefWidth(120);
        loginBtn.setPrefHeight(40);
        loginBtn.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        loginBtn.setStyle("-fx-background-color: #fce4d6; " +
                "-fx-border-color: #d4a574; " +
                "-fx-border-width: 1.5; " +
                "-fx-border-radius: 20; " +
                "-fx-background-radius: 20; " +
                "-fx-text-fill: black;");

        center.getChildren().addAll(welcome, subtitle, cardsBox, loginBtn);
        return center;
    }

    private VBox createCard(String title, String icon) {
        VBox card = new VBox(15);
        card.setAlignment(Pos.CENTER);
        card.setPrefSize(180, 180);
        card.setStyle("-fx-background-color: white; " +
                "-fx-border-color: black; " +
                "-fx-border-width: 2.5; " +
                "-fx-border-radius: 30; " +
                "-fx-background-radius: 30;");
        card.setPadding(new Insets(20));

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        titleLabel.setStyle("-fx-text-fill: black;");

        Label iconLabel = new Label(icon);
        iconLabel.setFont(Font.font(50));

        card.getChildren().addAll(titleLabel, iconLabel);

        // Efecto hover
        card.setOnMouseEntered(e ->
                card.setStyle("-fx-background-color: #f9f9f9; " +
                        "-fx-border-color: black; " +
                        "-fx-border-width: 2.5; " +
                        "-fx-border-radius: 30; " +
                        "-fx-background-radius: 30; " +
                        "-fx-cursor: hand;")
        );

        card.setOnMouseExited(e ->
                card.setStyle("-fx-background-color: white; " +
                        "-fx-border-color: black; " +
                        "-fx-border-width: 2.5; " +
                        "-fx-border-radius: 30; " +
                        "-fx-background-radius: 30;")
        );

        return card;
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
