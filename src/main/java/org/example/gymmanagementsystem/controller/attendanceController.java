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
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class attendanceController {

    private final AttendanceModel aModel = new AttendanceModel();

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

        boolean isDelete = aModel.delete(id);
        if (isDelete) {
            setNextId();
            loadtable();
            new Alert(Alert.AlertType.INFORMATION, "deleted Successfully..").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "deleting Failed").show();
        }
    }

    @FXML
    void btnGenarateROnAction(ActionEvent event) {

    }

    @FXML
    void btnResetOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtAttendanceId.getText();
        String memID = txtmemID.getText();
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
        String memID = txtmemID.getText();
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
