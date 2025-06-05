package org.example.gymmanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class paymentController {

    @FXML
    private TableColumn<?, ?> clmAmount;

    @FXML
    private TableColumn<?, ?> clmMemberId;

    @FXML
    private TableColumn<?, ?> clmMemberName;

    @FXML
    private TableColumn<?, ?> clmPaymentDate;

    @FXML
    private TableColumn<?, ?> clmPaymentId;

    @FXML
    private TableColumn<?, ?> clmPaymentMethod;

    @FXML
    private TableColumn<?, ?> clmPaymentType;

    @FXML
    private ComboBox<?> cmbMemberId;

    @FXML
    private ComboBox<?> cmbPaymentMethod;

    @FXML
    private ComboBox<?> cmbPaymentType;

    @FXML
    private DatePicker dtpPaymentDate;

    @FXML
    private TableView<?> tblPayment;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtMemberName;

    @FXML
    private TextField txtPaymentId;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnGenerateROnAction(ActionEvent event) {

    }

    @FXML
    void btnResetOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void setMemberDataOnAction(ActionEvent event) {

    }

    @FXML
    void tableClickOnAction(MouseEvent event) {

    }

}
