package org.example.gymmanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class dashBoardController implements Initializable {
    public AnchorPane AnkMainPage;
    public AnchorPane ANKMainload;
    public Button btnMemberId;
    public Button btnAttendanceId;
    public Button btnPaymentId;
    public Button btnSessionId;
    public Button btnClassId;
    public Button btnDiatplanId;
    public Button btnTrainerId;
    public Button btnSupplierId;
    public Button btnSupplimentId;
    public Button btnOredrIid;
    public Label btnGYMId;

    public void ClassOnAction(ActionEvent actionEvent) {
        nevigateTo("/view/class.fxml");
    }

    public void diatplanOnAction(ActionEvent actionEvent) {
        nevigateTo("/view/diatPlan.fxml");
    }

    public void trainerOnAction(ActionEvent actionEvent) {
        nevigateTo("/view/trainer.fxml");
    }

    public void suplierOnAction(ActionEvent actionEvent) {
        nevigateTo("/view/supplier.fxml");
    }

    public void SupplimentOnAction(ActionEvent actionEvent) {
        nevigateTo("/view/suppliment.fxml");
    }

    public void orderOnAction(ActionEvent actionEvent) {
        nevigateTo("/view/orders.fxml");
    }

    public void goBackAction(ActionEvent actionEvent) {
        nevigateTo("/view/goBack.fxml");
    }

    public void sessionOnAction(ActionEvent actionEvent) {
        nevigateTo("/view/session.fxml");
    }

    public void PaymentOnAction(ActionEvent actionEvent) {
        nevigateTo("/view/payment.fxml");
    }

    public void AttendanceOnAction(ActionEvent actionEvent) {
        nevigateTo("/view/attendance.fxml");
    }

    public void memberOnAction(ActionEvent actionEvent) {
        nevigateTo("/view/member.fxml");

    }
    private void nevigateTo(String s) {
        try {
            ANKMainload.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(s));

            pane.prefWidthProperty().bind(ANKMainload.widthProperty());
            pane.prefHeightProperty().bind(ANKMainload.heightProperty());

            ANKMainload.getChildren().add(pane);
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Page Not Found!").show();
            e.printStackTrace();

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
nevigateTo("/view/member.fxml");
    }
}
