package org.example.gymmanagementsystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class testController {

        @FXML
        private StackPane contentPane;

        public void showMembers() {
            contentPane.getChildren().setAll(new Label("Member Management Section"));
        }

        public void showTrainers() {
            contentPane.getChildren().setAll(new Label("Trainer Management Section"));
        }

        public void showSubscriptions() {
            contentPane.getChildren().setAll(new Label("Subscription Management Section"));
        }

        public void handleExit() {
            System.exit(0);
        }

        public void handleAbout() {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("About");
            alert.setHeaderText("Gym Management System");
            alert.setContentText("Created using JavaFX and FXML.");
            alert.showAndWait();
        }
    }


