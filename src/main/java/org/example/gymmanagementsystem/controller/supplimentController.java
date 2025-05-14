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
import org.example.gymmanagementsystem.dto.DietPlanDTO;
import org.example.gymmanagementsystem.dto.SupplementDTO;
import org.example.gymmanagementsystem.model.SupplimentModel;
import org.example.gymmanagementsystem.model.diatPlanModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class supplimentController {

    private final SupplimentModel suppmodel  = new SupplimentModel();

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
        clmSuppliment.setCellValueFactory(new PropertyValueFactory<>("SupplimentId"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        clmDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtSupplimentId.getText();

        boolean isDelete = suppmodel.delete(id);
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
        String supplimentId = txtSupplimentId.getText();
        String name = txtsuppName.getText();
        String description = txtDescription.getText();

        SupplementDTO supplementDTO = new SupplementDTO(
                supplimentId,
                name,
                description
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


        SupplementDTO supplementDTO = new SupplementDTO(
                suppId,
                suppName,
                description
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



//            // save button disable
//            btnSave.setDisable(true);
//
//            // update, delete button enable
//            btnUpdate.setDisable(false);
//            btnDelete.setDisable(false);
        }
    }

}
