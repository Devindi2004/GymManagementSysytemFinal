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
import org.example.gymmanagementsystem.model.diatPlanModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class diatPlanController {

    private final diatPlanModel dmodel  = new diatPlanModel();

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
        String nextId = diatPlanModel.getNextId();
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

        boolean isDelete = dmodel.delete(id);
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

//        DietPlanDTO DietPlanDTO = new DietPlanDTO();
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
