package org.example.gymmanagementsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.gymmanagementsystem.dto.EquipmentDTO;
import org.example.gymmanagementsystem.model.ClassModel;
import org.example.gymmanagementsystem.model.EquipmentModel;
import org.example.gymmanagementsystem.model.SupplierModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class equipmentController {

    private final EquipmentModel eModel = new EquipmentModel();
    public ComboBox cmbClassId;
    public ComboBox cmbSupplierId;

    @FXML
    private TableColumn<?, ?> clmClassId;

    @FXML
    private TableColumn<?, ?> clmEquipId;

    @FXML
    private TableColumn<?, ?> clmNameId;

    @FXML
    private TableColumn<?, ?> clmSuppId;

    @FXML
    private TableColumn<?, ?> clmType;

    @FXML
    private TableView<EquipmentDTO> tblEquip;

    @FXML
    private TextField txtClassId;

    @FXML
    private TextField txtNameId;

    @FXML
    private TextField txtSuppId;

    @FXML
    private TextField txtTypeId;

    @FXML
    private TextField txtequipId;

    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactory();
        setNextId();
        cmbClassId.setItems(ClassModel.Allclassid());
        cmbSupplierId.setItems(SupplierModel.AllSupplierI());
        loadtable();
    }

    private void loadtable() throws SQLException, ClassNotFoundException {
        ArrayList<EquipmentDTO> equipmentDTOS = eModel.getAllequipment();
        tblEquip.getItems().clear();

        ObservableList<EquipmentDTO> data = FXCollections.observableArrayList();
            for (EquipmentDTO e : equipmentDTOS) {
                data.add(e);
            }
            tblEquip.setItems(data);
    }

    private void setNextId() throws SQLException, ClassNotFoundException {
        String equipId = EquipmentModel.getNextId();
        txtequipId.setText(equipId);
    }

    private void setCellValueFactory() {
        clmEquipId.setCellValueFactory(new PropertyValueFactory<>("equipId"));
        clmSuppId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        clmClassId.setCellValueFactory(new PropertyValueFactory<>("classId"));
        clmNameId.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmType.setCellValueFactory(new PropertyValueFactory<>("type"));
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtequipId.getText();

        if (id == null || id.trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a equipment to delete.", ButtonType.OK).show();
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Delete Confirmation");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("Are you sure you want to delete the diet plan with ID: " + id + "?");

        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean isDelete = eModel.delete(id);
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
        txtequipId.clear();
        txtClassId.clear();
        txtSuppId.clear();
        txtNameId.clear();
        txtTypeId.clear();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String equipId = txtequipId.getText();
        String supplierId = cmbSupplierId.getValue().toString();
        String classId = cmbClassId.getValue().toString();
        String name = txtNameId.getText();
        String typeId = txtTypeId.getText();


        EquipmentDTO equipmentDTO = new EquipmentDTO(
                equipId,
                supplierId,
                classId,
                name,
                typeId
        );

        boolean isSave = eModel.save(equipmentDTO);

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
        String equipId = txtequipId.getText();
        String supplierId = txtSuppId.getText();
        String classId = txtClassId.getText();
        String name = txtNameId.getText();
        String typeId = txtTypeId.getText();


        EquipmentDTO equipmentDTO = new EquipmentDTO(
                equipId,
                supplierId,
                classId,
                name,
                typeId
        );

        boolean isUpdate = eModel.update(equipmentDTO);
        if (isUpdate) {
            setNextId();
            loadtable();
            new Alert(Alert.AlertType.INFORMATION, "Updated Successfully").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Update Failed").show();
        }
    }

    public void tableClickOnAction(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        EquipmentDTO selectedItem = tblEquip.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            txtequipId.setText(selectedItem.getEquipId());
            txtSuppId.setText(selectedItem.getSupplierId());
            txtClassId.setText(selectedItem.getClass().getSimpleName());
            txtNameId.setText(selectedItem.getName());
            txtTypeId.setText(selectedItem.getType());


        }
    }

}
