package org.example.gymmanagementsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.gymmanagementsystem.dto.DietPlanDTO;
import org.example.gymmanagementsystem.model.DiatPlanModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class diatPlanController {

    private final DiatPlanModel dmodel  = new DiatPlanModel();

    @FXML
    private TableColumn<?, ?> clmDrink;

    @FXML
    private TableColumn<?, ?> clmFood;

    @FXML
    private TableColumn<?, ?> clmdiatplan;

    @FXML
    private TableColumn<?, ?> clmplanName;

    @FXML
    private TableView<DietPlanDTO> tblDiatPlan;

    @FXML
    private TextField txtDiatPlanId;

    @FXML
    private TextField txtDrinkId;

    @FXML
    private TextField txtFoodId;

    @FXML
    private TextField txtPlanNameId;

    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactory();
        setNextId();
        loadtable();
    }

    private void loadtable() throws SQLException, ClassNotFoundException {
        ArrayList<DietPlanDTO> DiatPlanDTOS = dmodel.getAllDiatPlan();

        ObservableList<DietPlanDTO> data = FXCollections.observableArrayList();
        for (DietPlanDTO d : DiatPlanDTOS) {
            data.add(d);
        }

        tblDiatPlan.setItems(data);
    }

    private void setNextId() throws SQLException, ClassNotFoundException {
        String nextId = DiatPlanModel.getNextId();
        txtDiatPlanId.setText(nextId);
    }

    private void setCellValueFactory() {
        clmdiatplan.setCellValueFactory(new PropertyValueFactory<>("DietPlanId"));
        clmplanName.setCellValueFactory(new PropertyValueFactory<>("PlanName"));
        clmFood.setCellValueFactory(new PropertyValueFactory<>("Food"));
        clmDrink.setCellValueFactory(new PropertyValueFactory<>("Drink"));
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtDiatPlanId.getText();

        if (id == null || id.trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a diet plan to delete.", ButtonType.OK).show();
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Delete Confirmation");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("Are you sure you want to delete the diet plan with ID: " + id + "?");

        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean isDelete = dmodel.delete(id);
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
        txtDiatPlanId.clear();
        txtPlanNameId.clear();
        txtDrinkId.clear();
        txtFoodId.clear();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String dietPlanId = txtDiatPlanId.getText();
        String planName = txtPlanNameId.getText();
        String food = txtFoodId.getText();
        String drink = txtDrinkId.getText();

        DietPlanDTO dietPlanDTO = new DietPlanDTO(
                dietPlanId,
                planName,
                food,
                drink
        );

//        DietPlanDTO DietPlanDTO = null;
        boolean isSave = dmodel.save(dietPlanDTO);

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
        String dietPlanId = txtDiatPlanId.getText();
        String planName = txtPlanNameId.getText();
        String food = txtFoodId.getText();
        String drink = txtDrinkId.getText();


        DietPlanDTO dietPlanDTO = new DietPlanDTO(
                dietPlanId,
                planName,
                food,
                drink
        );

        boolean isUpdate = dmodel.update(dietPlanDTO);
        if (isUpdate) {
            setNextId();
            loadtable();
            new Alert(Alert.AlertType.INFORMATION, "Updated Successfully").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Update Failed").show();
        }
    }

    public void tableClickOnAction(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        DietPlanDTO selectedItem = tblDiatPlan.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            txtDiatPlanId.setText(selectedItem.getDietPlanId());
            txtPlanNameId.setText(selectedItem.getPlanName());
            txtFoodId.setText(selectedItem.getFood());
            txtDrinkId.setText(selectedItem.getDrink());


        }
    }
}
