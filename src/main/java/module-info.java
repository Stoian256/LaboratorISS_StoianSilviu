module com.example.proiectbibliotecaiss {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.proiectbibliotecaiss.controllers to javafx.fxml,javafx.base;
    opens com.example.proiectbibliotecaiss.domain to javafx.base;
    exports com.example.proiectbibliotecaiss;
    exports com.example.proiectbibliotecaiss.controllers;
}