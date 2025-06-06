package org.example.gymmanagementsystem.controller;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.gymmanagementsystem.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;
import java.util.regex.Pattern;

public class LoginPageController {
    public PasswordField txtPassword;
    public Button btnLogin;
    public Label lblError;
    public Hyperlink txtForgotId;
    public PasswordField txtuserName;
    public TextField txtEmail;
    private org.example.gymmanagementsystem.User user = new User("admin", "123");

    public void loginOnAction(ActionEvent actionEvent) {
        String email = txtEmail.getText();
        String username = txtuserName.getText();
        String password = txtPassword.getText();

        if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            showAlert("Please fill in all fields.", Alert.AlertType.ERROR);
            return;
        }

        if (user.verifyLogin(username, password)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashboard.fxml"));
                Parent dashboardRoot = loader.load();
                Stage stage = (Stage) txtuserName.getScene().getWindow();
                Scene scene = new Scene(dashboardRoot);
                stage.setScene(scene);
                stage.setTitle("Dashboard");
                stage.show();
            } catch (IOException e) {
                showAlert("Error loading dashboard.", Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        } else {
            showAlert("Invalid username or password.", Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(message);
        alert.show();
    }

    public void passwordOnAction(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }

    public void emailOnAction(ActionEvent actionEvent) {
        txtuserName.requestFocus();
    }

    public void forgotOnAction(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Forgot Password");
        dialog.setHeaderText("Password Recovery");
        dialog.setContentText("Please enter your registered email address:");

        dialog.showAndWait().ifPresent(email -> {
            if (!isValidEmail(email)) {
                showAlert("Please enter a valid email address.", Alert.AlertType.ERROR);
            } else {
                Task<Void> emailTask = new Task<>() {
                    @Override
                    protected Void call() throws Exception {
                        sendResetEmail(email);
                        return null;
                    }
                };


                emailTask.setOnSucceeded(event -> showAlert("A password reset link has been sent to " + email, Alert.AlertType.INFORMATION));
                emailTask.setOnFailed(event -> {
                    showAlert("Failed to send reset email. Please try again later.", Alert.AlertType.ERROR);
                    emailTask.getException().printStackTrace();
                });

                new Thread(emailTask).start();
            }
        });
    }

    private boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        // Email validation regex
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    private void sendResetEmail(String recipientEmail) throws MessagingException {
        // Email server configuration (Gmail SMTP)
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        final String senderEmail = "punchihewadevindi@gmail.com";
        final String senderPassword = "ttdeuwznuhfslwdg";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        String resetToken = UUID.randomUUID().toString();

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
        message.setSubject("Password Reset Request");
        message.setText("Dear User,\n\n" +
                "You have requested to reset your password. Click the link below to reset it:\n" +

                "http://example.com/reset-password?email=" + recipientEmail + "&token=" + resetToken + "\n\n" +
                "Alternatively, use this temporary token to reset your password: " + resetToken + "\n" +
                "Please enter this token in the password reset form and set a new password.\n\n" +
                "If you did not request this, please ignore this email.\n\n" +
                "Best regards,\nGym Management System");

        Transport.send(message);
    }

    public void userNameOnAction(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }
}