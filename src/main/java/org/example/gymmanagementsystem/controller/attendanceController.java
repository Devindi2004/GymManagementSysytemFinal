package org.example.gymmanagementsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.gymmanagementsystem.dto.AttendanceDTO;
import org.example.gymmanagementsystem.model.AttendanceModel;
import org.example.gymmanagementsystem.model.ClassModel;
import org.example.gymmanagementsystem.model.MemberModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class attendanceController {

    private final AttendanceModel aModel = new AttendanceModel();
    public ComboBox cmbAttendanceId;

    @FXML
    private TableColumn<?, ?> clmAttendance;

    @FXML
    private TableColumn<?, ?> clmDate;


    @FXML
    private DatePicker dateId;

    @FXML
    private TableColumn<?, ?> clmMember;

    @FXML
    private TableView<AttendanceDTO> tblAttendance;

    @FXML
    private TextField txtAttendanceId;

    @FXML
    private TextField txtmemID;

    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactory();
        setNextId();
        cmbAttendanceId.setItems(MemberModel.Allclassid());

        loadtable();
    }

    private void loadtable() throws SQLException, ClassNotFoundException {
        ArrayList<AttendanceDTO> attendanceDTOS = aModel.getAllAttendance();

        ObservableList<AttendanceDTO> data = FXCollections.observableArrayList();
        for (AttendanceDTO a : attendanceDTOS) {
            data.add(a);
        }
        tblAttendance.setItems(data);
    }

    private void setNextId() throws SQLException, ClassNotFoundException {
        String nextId = AttendanceModel.getNextId();
        txtAttendanceId.setText(nextId);
    }

    private void setCellValueFactory() {
        clmAttendance.setCellValueFactory(new PropertyValueFactory<>("attendanceId"));
        clmMember.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        clmDate.setCellValueFactory(new PropertyValueFactory<>("date"));


    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtAttendanceId.getText();

        if (id == null || id.trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a record to delete.", ButtonType.OK).show();
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Delete Confirmation");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("Are you sure you want to delete the attendance record with ID: " + id + "?");

        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean isDelete = aModel.delete(id);
            if (isDelete) {
                setNextId();
                loadtable();
                new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully.").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Deleting Failed.").show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Deletion Cancelled.").show();
        }
    }

    @FXML
    void btnGenarateROnAction(ActionEvent event) {

    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtAttendanceId.clear();
        txtmemID.clear();
        dateId.setValue(null);
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtAttendanceId.getText();
        String memID = cmbAttendanceId.getValue().toString();
        String attendanceDate = dateId.getValue().toString();

        AttendanceDTO attendanceDTO = new AttendanceDTO(
                id,
                memID,
                attendanceDate
        );
        boolean isSave = aModel.save(attendanceDTO);

        if (isSave) {
            setNextId();
            loadtable();
            new Alert(Alert.AlertType.INFORMATION, "Saved Successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Saving Failed").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtAttendanceId.getText();
        String memID = cmbAttendanceId.getValue().toString();
        String attendanceDate = dateId.getValue().toString();

        AttendanceDTO attendanceDTO = new AttendanceDTO(
                id,
                memID,
                attendanceDate
        );
        boolean isUpdate = aModel.update(attendanceDTO);
        if (isUpdate) {
            setNextId();
            loadtable();
            new Alert(Alert.AlertType.INFORMATION, "Updated Successfully").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Update Failed").show();
        }
    }
    public void tClickOnAction(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        AttendanceDTO selectedItem = tblAttendance.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            txtAttendanceId.setText(selectedItem.getAttendanceId());
            txtmemID.setText(selectedItem.getMemberId());
            dateId.setValue(LocalDate.parse(selectedItem.getDate()));

        }
    }
}
