package org.example.gymmanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TestController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblError;

    @FXML
    void loginOnAction(ActionEvent event) {
        String email = txtUsername.getText();
        String password = txtPassword.getText();

        if (email.equals("admin") && password.equals("1234")) {
            lblError.setText("Login Successful!");
            lblError.setStyle("-fx-text-fill: green;");
        } else {
            lblError.setText("Invalid credentials!");
            lblError.setVisible(true);
        }
    }
}
