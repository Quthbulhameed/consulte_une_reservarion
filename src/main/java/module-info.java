module com.example.demo3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    requires javafx.graphics;
    requires java.sql;
    requires java.desktop;

    opens com.example.demo3 to javafx.fxml;
    exports com.example.demo3;
}