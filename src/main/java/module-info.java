module org.example.proyectoad {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens org.example.proyectoad to javafx.fxml;
    exports org.example.proyectoad;
}