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
import org.example.gymmanagementsystem.dto.SupplementDTO;
import org.example.gymmanagementsystem.dto.TrainerDTO;
import org.example.gymmanagementsystem.model.TrainerModel;

import java.sql.SQLException;
import java.util.ArrayList;

import static com.sun.beans.introspect.PropertyInfo.Name.description;

public class trainerController {

    private final TrainerModel tModel  = new TrainerModel();

    @FXML
    private TableColumn<?, ?> clmContact;

    @FXML
    private TableColumn<?, ?> clmTName;

    @FXML
    private TableColumn<?, ?> clmTrainer;

    @FXML
    private TableColumn<?, ?> clmspecialization;

    @FXML
    private TableView<TrainerDTO> tblTrainer;

    @FXML
    private TextField txtContactId;

    @FXML
    private TextField txtSpecialization;

    @FXML
    private TextField txtTrainer;

    @FXML
    private TextField txtTrainerName;

    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactory();
        setNextId();
        loadtable();
    }

    private void setCellValueFactory() {
        clmTrainer.setCellValueFactory(new PropertyValueFactory<>("TrainerId"));
        clmTName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        clmContact.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        clmspecialization.setCellValueFactory(new PropertyValueFactory<>("specialization"));
    }

    private void setNextId() throws SQLException, ClassNotFoundException {
        String nextId = tModel.getNextId();
        txtTrainer.setText(nextId);
    }

    private void loadtable() throws SQLException, ClassNotFoundException {
        ArrayList<TrainerDTO> trainerDTOS = tModel.getAllTrainer();

        ObservableList<TrainerDTO> data = FXCollections.observableArrayList();
        for (TrainerDTO t : trainerDTOS) {
            data.add(t);
        }



        tblTrainer.setItems(data);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtTrainer.getText();

        boolean isDelete = tModel.delete(id);
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
        String TrainerId = txtTrainer.getText();
        String nameId = txtTrainerName.getText();
        String contact = txtContactId.getText();
        String Specialization = txtSpecialization.getText();

        TrainerDTO trainerDTO = new TrainerDTO(
                TrainerId, 
                nameId,
                contact,
                Specialization
        );


        //Object TrainerDTO = null;
        boolean isSave = tModel.save(trainerDTO);

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
        String TrainerId = txtTrainer.getText();
        String tName = txtTrainerName.getText();
        String tcontact = txtContactId.getText();
        String tSpecialization = txtSpecialization.getText();


        TrainerDTO trainerDTO = new TrainerDTO(
                TrainerId,
                tName,
                tcontact, 
                tSpecialization
        );


       // Object TrainerDTO = null;
        boolean isUpdate = tModel.update(trainerDTO);
        if (isUpdate) {
            setNextId();
            loadtable();
            new Alert(Alert.AlertType.INFORMATION, "Updated Successfully").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Update Failed").show();
        }
    }
    public void tableClickOnAction(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        TrainerDTO selectedItem = tblTrainer.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            txtTrainer.setText(selectedItem.getTrainerId());
            txtTrainerName.setText(selectedItem.getName());
            txtContactId.setText(selectedItem.getContact());
            txtSpecialization.setText(selectedItem.getSpecialization());



//            // save button disable
//            btnSave.setDisable(true);
//
//            // update, delete button enable
//            btnUpdate.setDisable(false);
//            btnDelete.setDisable(false);
        }
    }

}
