module org.example.gymmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;
    requires java.desktop;


    opens org.example.gymmanagementsystem to javafx.fxml;
    exports org.example.gymmanagementsystem;

    opens org.example.gymmanagementsystem.controller to javafx.fxml;
    exports org.example.gymmanagementsystem.controller;
    opens org.example.gymmanagementsystem.dto;
    exports org.example.gymmanagementsystem.dto;
    opens org.example.gymmanagementsystem.model;
    exports org.example.gymmanagementsystem.model;
}