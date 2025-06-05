package org.example.gymmanagementsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.gymmanagementsystem.dto.SupplierDTO;
import org.example.gymmanagementsystem.model.SupplierModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class supplierController {

    private final SupplierModel spModel = new SupplierModel();

    @FXML
    private TableColumn<?, ?> clmContact;

    @FXML
    private TableColumn<?, ?> clmItem;

    @FXML
    private TableColumn<?, ?> clmName;

    @FXML
    private TableColumn<?, ?> clmSupplier;

    @FXML
    private TableView<SupplierDTO> tblSupplier;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtItem;

    @FXML
    private TextField txtSupplierId;

    @FXML
    private TextField txtsuppName;

    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactory();
        setNextId();
        loadtable();
    }

    private void loadtable() throws SQLException, ClassNotFoundException {
        ArrayList<SupplierDTO> supplierDTOS = spModel.getAllSupplier();

        ObservableList<SupplierDTO> data = FXCollections.observableArrayList();
        for (SupplierDTO s : supplierDTOS) {
            data.add(s);
        }

        tblSupplier.setItems(data);
    }

    private void setNextId() throws SQLException, ClassNotFoundException {
        String nextId = SupplierModel.getNextId();
        txtSupplierId.setText(nextId);
    }

    private void setCellValueFactory() {
        clmSupplier.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        clmItem.setCellValueFactory(new PropertyValueFactory<>("item"));


    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtSupplierId.getText();

        if (id == null || id.trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a Supplier to delete.", ButtonType.OK).show();
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Delete Confirmation");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("Are you sure you want to delete the diet plan with ID: " + id + "?");

        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean isDelete = spModel.delete(id);
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
        txtSupplierId.clear();
        txtsuppName.clear();
        txtContact.clear();
        txtItem.clear();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String supplierId = txtSupplierId.getText();
        String suppNName = txtsuppName.getText();
        String contact = txtContact.getText();
        String item = txtItem.getText();


        SupplierDTO supplierDTO = new SupplierDTO(
                supplierId,
                suppNName,
                contact,
                item
        );

        boolean isSave = spModel.save(supplierDTO);

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
        String supplierId = txtSupplierId.getText();
        String suppNName = txtsuppName.getText();
        String contact = txtContact.getText();
        String item = txtItem.getText();


        SupplierDTO supplierDTO = new SupplierDTO(
                supplierId,
                suppNName,
                contact,
                item
        );


        boolean isUpdate = spModel.update(supplierDTO);
        if (isUpdate) {
            setNextId();
            loadtable();
            new Alert(Alert.AlertType.INFORMATION, "Updated Successfully").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Update Failed").show();
        }
    }

    public void tableClickOnAction(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        SupplierDTO selectedItem = tblSupplier.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            txtSupplierId.setText(selectedItem.getSupplierId());
            txtsuppName.setText(selectedItem.getName());
            txtContact.setText(selectedItem.getContact());
            txtItem.setText(selectedItem.getItem());

        }
    }
}
