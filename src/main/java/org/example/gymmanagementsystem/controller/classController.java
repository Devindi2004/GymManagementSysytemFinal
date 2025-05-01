package org.example.gymmanagementsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.gymmanagementsystem.dto.ClassInfoDTO;
import org.example.gymmanagementsystem.model.ClassModel;
import org.example.gymmanagementsystem.model.ClassModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class classController {

    private final ClassModel classModel = new ClassModel();

    @FXML
    private TableColumn<?, ?> DurationClm;

    @FXML
    private TableColumn<?, ?> IdClm;

    @FXML
    private TableColumn<?, ?> TimeClm;

    @FXML
    private TableView<ClassInfoDTO> tblClassTable;

    @FXML
    private TextField txtClassId;

    @FXML
    private TextField txtIDuration;

    @FXML
    private TextField txtTime;

    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactory();
        setNextId();
        loadtable();
    }

    private void loadtable() throws SQLException, ClassNotFoundException {
        ArrayList <ClassInfoDTO> classInfoDTOS = classModel.getAllClass();

        ObservableList<ClassInfoDTO> data = FXCollections.observableArrayList();

        for (ClassInfoDTO classInfoDTO : classInfoDTOS) {
            data.add(classInfoDTO);
        }

        tblClassTable.setItems(data);
    }

    private void setNextId() throws SQLException, ClassNotFoundException {
        String nextId = classModel.getNextId();
        txtClassId.setText(nextId);
    }

    private void setCellValueFactory() {
        IdClm.setCellValueFactory(new PropertyValueFactory<>("classId"));
        TimeClm.setCellValueFactory(new PropertyValueFactory<>("time"));
        DurationClm.setCellValueFactory(new PropertyValueFactory<>("Duration"));
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtClassId.getText();

        boolean isDelete = classModel.delete(id);
        if (isDelete) {
            setNextId();
            loadtable();
            new Alert(Alert.AlertType.INFORMATION, "deleted Successfully").show();
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
        String classId = txtClassId.getText();
        String duration = txtIDuration.getText();
        String time = txtTime.getText();

        ClassInfoDTO classInfoDTO = new ClassInfoDTO(
                classId,
                time,
                duration
        );

        boolean isSave = classModel.save(classInfoDTO);

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
        String classId = txtClassId.getText();
        String time = txtTime.getText();
        String duration = txtIDuration.getText();

        ClassInfoDTO classInfoDTO = new ClassInfoDTO(classId,
                time,
                duration
        );

        boolean isUpdate = classModel.update(classInfoDTO);
        if (isUpdate) {
            setNextId();
            loadtable();
            new Alert(Alert.AlertType.INFORMATION, "Updated Successfully").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Update Failed").show();
        }
    }

    public void tableClickOnAction(MouseEvent mouseEvent) {
         ClassInfoDTO selectedItem = tblClassTable.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            txtClassId.setText(selectedItem.getClassId());
            txtTime.setText(selectedItem.getTime());
            txtIDuration.setText(selectedItem.getDuration());


//            // save button disable
//            btnSave.setDisable(true);
//
//            // update, delete button enable
//            btnUpdate.setDisable(false);
//            btnDelete.setDisable(false);
        }
    }
}
