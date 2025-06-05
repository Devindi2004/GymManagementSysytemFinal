package org.example.gymmanagementsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.gymmanagementsystem.dto.SessionDTO;
import org.example.gymmanagementsystem.model.ClassModel;
import org.example.gymmanagementsystem.model.SessionModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class sessionController {

    private final SessionModel ssModel = new SessionModel();
    public ComboBox cmbClassId;

    @FXML
    private TableColumn<?, ?> clmClass;

    @FXML
    private TableColumn<?, ?> clmSession;

    @FXML
    private TableColumn<?, ?> clmTime;

    @FXML
    private TableColumn<?, ?> clmType;

    @FXML
    private TableColumn<?, ?> clmdate;

    @FXML
    private TableView<SessionDTO> tblSession;

    @FXML
    private TextField txtClassID;

    @FXML
    private TextField txtSessionId;

    @FXML
    private TextField txtTime;

    @FXML
    private TextField txtType;

    @FXML
    private DatePicker txtdate;

    public void initialize() {
        try {
            setCellValueFactory();
            setNextId();
            cmbClassId.setItems(ClassModel.Allclassid());
            loadTable();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Initialization error: " + e.getMessage()).show();
        }
    }

    private void loadTable() throws SQLException, ClassNotFoundException {
        ArrayList<SessionDTO> sessionDTOS = ssModel.getAllSession();

        ObservableList<SessionDTO> data = FXCollections.observableArrayList();
        for (SessionDTO sessionDTO : sessionDTOS) {
            data.add(sessionDTO);

        }

        tblSession.setItems(data);
    }

    private void setNextId() throws SQLException, ClassNotFoundException {
        String nextId = SessionModel.getNextId();
        txtSessionId.setText(nextId);
    }

    private void setCellValueFactory() {
        clmSession.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
        clmClass.setCellValueFactory(new PropertyValueFactory<>("classID"));
        clmType.setCellValueFactory(new PropertyValueFactory<>("type"));
        clmTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        clmdate.setCellValueFactory(new PropertyValueFactory<>("date"));

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtSessionId.getText();

        if (id == null || id.trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select an Session to delete.").show();
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION, "Delete session ID: " + id + "?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            boolean isDeleted = ssModel.delete(id);
            if (isDeleted) {
                setNextId();
                loadTable();
                clearFields();
                new Alert(Alert.AlertType.INFORMATION, "Deleted successfully.").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Deletion failed.").show();
            }
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String sessionId = txtSessionId.getText();
        String classID = cmbClassId.getValue().toString();
        String time = txtTime.getText();
        String type = txtType.getText();
        String date = String.valueOf(txtdate.getValue());


        SessionDTO sessionDTO = new SessionDTO(
                sessionId,
                classID,
                time,
                type,
                date
        );

        boolean isSave = ssModel.save(sessionDTO);

        if (isSave) {
            setNextId();
            loadTable();
            new Alert(Alert.AlertType.INFORMATION, "Saved Successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Saving Failed").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String sessionId = txtSessionId.getText();
        String classID = txtClassID.getText();
        String time = txtTime.getText();
        String type = txtType.getText();
        String date = String.valueOf(txtdate.getValue());


        SessionDTO sessionDTO = new SessionDTO(
                sessionId,
                classID,
                time,
                type,
                date
        );

        boolean isUpdate = ssModel.update(sessionDTO);
        if (isUpdate) {
            setNextId();
            loadTable();
            new Alert(Alert.AlertType.INFORMATION, "Updated Successfully").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Update Failed").show();
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtSessionId.clear();
        txtClassID.clear();
        txtType.clear();
        txtTime.clear();
        txtdate.setValue(null);
    }

    @FXML
    void tableClickOnAction(MouseEvent event) {
        SessionDTO selectedItem = tblSession.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            txtSessionId.setText(selectedItem.getSessionId());
            txtClassID.setText(selectedItem.getClassID());
            txtType.setText(selectedItem.getType());
            txtTime.setText(selectedItem.getTime());
            txtdate.setValue(LocalDate.parse(selectedItem.getDate()));
        }
    }

    @FXML
    void btnGenarateROnAction(ActionEvent event) {
        // Report generation logic placeholder
        new Alert(Alert.AlertType.INFORMATION, "Generate Report clicked.").show();
    }
}
