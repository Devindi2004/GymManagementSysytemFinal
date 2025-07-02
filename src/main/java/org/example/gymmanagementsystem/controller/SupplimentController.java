package org.example.gymmanagementsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.gymmanagementsystem.dto.SupplementDTO;
import org.example.gymmanagementsystem.model.SupplimentModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class SupplimentController {

    private final SupplimentModel suppmodel  = new SupplimentModel();
    public TextField txtPrice;
    public TextField txtQuantity;

    @FXML
    private TableColumn<?, ?> clmPrice;

    @FXML
    private TableColumn<?, ?> clmQuantity;

    @FXML
    private TableColumn<?, ?> clmDescription;

    @FXML
    private TableColumn<?, ?> clmName;

    @FXML
    private TableColumn<?, ?> clmSuppliment;

    @FXML
    private TableView<SupplementDTO> tblSuppliment;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtSupplimentId;

    @FXML
    private TextField txtsuppName;

    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactory();
        setNextId();
        loadtable();
    }

    private void loadtable() throws SQLException, ClassNotFoundException {
        ArrayList<SupplementDTO> supplementDTOS = suppmodel.getAllSuppliment();

        ObservableList<SupplementDTO> data = FXCollections.observableArrayList();
        for (SupplementDTO s : supplementDTOS) {
            data.add(s);
        }



        tblSuppliment.setItems(data);
    }

    private void setNextId() throws SQLException, ClassNotFoundException {
        String nextId = suppmodel.getNextId();
        txtSupplimentId.setText(nextId);
    }

    private void setCellValueFactory() {
        clmSuppliment.setCellValueFactory(new PropertyValueFactory<>("supplimentId"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        clmPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        clmQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtSupplimentId.getText();

        if (id == null || id.trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a supplement to delete.", ButtonType.OK).show();
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Delete Confirmation");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("Are you sure you want to delete the supplement with ID: " + id + "?");

        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean isDelete = suppmodel.delete(id);
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
        txtSupplimentId.clear();
        txtsuppName.clear();
        txtDescription.clear();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String supplimentId = txtSupplimentId.getText();
        String name = txtsuppName.getText();
        String description = txtDescription.getText();
        String price = txtPrice.getText();
        String quantity = txtQuantity.getText();

        SupplementDTO supplementDTO = new SupplementDTO(
                supplimentId,
                name,
                description,
                price,
                quantity
        );

        boolean isSave = suppmodel.save(supplementDTO);

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
        String suppId = txtSupplimentId.getText();
        String suppName = txtsuppName.getText();
        String description = txtDescription.getText();
        String price = txtPrice.getText();
        String quantity = txtQuantity.getText();


        SupplementDTO supplementDTO = new SupplementDTO(
                suppId,
                suppName,
                description,
                price,
                quantity
        );


        boolean isUpdate = suppmodel.update(supplementDTO);
        if (isUpdate) {
            setNextId();
            loadtable();
            new Alert(Alert.AlertType.INFORMATION, "Updated Successfully").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Update Failed").show();
        }
    }
    public void tableClickOnAction(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        SupplementDTO selectedItem = tblSuppliment.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            txtSupplimentId.setText(selectedItem.getSupplimentId());
            txtsuppName.setText(selectedItem.getName());
            txtDescription.setText(selectedItem.getDescription());




        }
    }

}
