package org.example.gymmanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.gymmanagementsystem.HelloApplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashBoardController implements Initializable {
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
    public Button btnEquipment;
    public ImageView txtequipId;

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

    public void goBackAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/view/Logging.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) btnGYMId.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void sessionOnAction(ActionEvent actionEvent) {
        nevigateTo("/view/session.fxml");
    }

/* <<<<<<<<<<<<<<  ✨ Windsurf Command ⭐ >>>>>>>>>>>>>>>> */
/* <<<<<<<<<<  bf1d52ee-dc99-4e71-b623-3e71457a6769  >>>>>>>>>>> */
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

    public void equipmentOnAction(ActionEvent actionEvent) {
        nevigateTo("/view/equipment.fxml");
    }
}
