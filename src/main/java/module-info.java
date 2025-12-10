module com.example.tracktivity {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires javafx.base;
    requires javafx.graphics;

    opens com.example.tracktivity to javafx.fxml;
    exports com.example.tracktivity;
}