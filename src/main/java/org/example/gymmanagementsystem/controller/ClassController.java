package org.example.gymmanagementsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.gymmanagementsystem.dto.ClassInfoDTO;
import org.example.gymmanagementsystem.model.ClassModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ClassController {

    private final ClassModel cModel = new ClassModel();

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
        ArrayList <ClassInfoDTO> classInfoDTOS = cModel.getAllClass();

        ObservableList<ClassInfoDTO> data = FXCollections.observableArrayList();

        for (ClassInfoDTO classInfoDTO : classInfoDTOS) {
            data.add(classInfoDTO);
        }

        tblClassTable.setItems(data);
    }

    private void setNextId() throws SQLException, ClassNotFoundException {
        String nextId = cModel.getNextId();
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

        if (id == null || id.trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a class to delete.", ButtonType.OK).show();
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Delete Confirmation");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("Are you sure you want to delete the class with ID: " + id + "?");

        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean isDelete = cModel.delete(id);
            if (isDelete) {
                setNextId();
                loadtable();
                new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Deleting Failed").show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Deletion Cancelled").show();
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
        txtClassId.clear();
        txtTime.clear();
        txtIDuration.clear();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String classId = txtClassId.getText();
        String Duration = txtIDuration.getText();
        String time = txtTime.getText();

        ClassInfoDTO classInfoDTO = new ClassInfoDTO(
                classId,
                time,
                Duration
        );

        boolean isSave = cModel.save(classInfoDTO);

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
        String Duration = txtIDuration.getText();

        ClassInfoDTO classInfoDTO = new ClassInfoDTO(
                classId,
                time,
                Duration
        );

        boolean isUpdate = cModel.update(classInfoDTO);
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
