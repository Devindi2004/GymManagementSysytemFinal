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
import org.example.gymmanagementsystem.dto.MemberDTO;
import org.example.gymmanagementsystem.model.memberModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class memberController {

    private final memberModel mModel = new memberModel();

    @FXML
    private TableColumn<?, ?> clmAge;

    @FXML
    private TableColumn<?, ?> clmEmailId;

    @FXML
    private TableColumn<?, ?> clmContact;

    @FXML
    private TableColumn<?, ?> clmDiatPlanId;

    @FXML
    private TableColumn<?, ?> clmMemberId;

    @FXML
    private TableColumn<?, ?> clmName;

    @FXML
    private TableView<MemberDTO> tblMember;

    @FXML
    private TextField txtAgeId;

    @FXML
    private TextField txtContId;

    @FXML
    private TextField txtDPlanId;

    @FXML
    private TextField txtMemmberId;


    @FXML
    private TextField txtEmailId;

    @FXML
    private TextField txtNameId;

    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactory();
        setNextId();
        loadtable();
    }

    private void loadtable() throws SQLException, ClassNotFoundException {
        ArrayList<MemberDTO> memberDTOS = mModel.getAllmember();

        ObservableList<MemberDTO> observableList = FXCollections.observableArrayList();
        for (MemberDTO m : memberDTOS) {
            observableList.add(m);
        }

        tblMember.setItems(observableList);

        }

    private void setNextId() throws SQLException, ClassNotFoundException {
        String nextId = memberModel.getnextId();
        txtMemmberId.setText(nextId);

    }

    private void setCellValueFactory() {
        clmMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        clmDiatPlanId.setCellValueFactory(new PropertyValueFactory<>("dietPlanId"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmEmailId.setCellValueFactory(new PropertyValueFactory<>("email"));
        clmContact.setCellValueFactory(new PropertyValueFactory<>("phone"));
        clmAge.setCellValueFactory(new PropertyValueFactory<>("age"));

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtMemmberId.getText();

        boolean isDelete = mModel.delete(id);
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
        String id = txtMemmberId.getText();
        String dPlanId = txtDPlanId.getText();
        String name = txtNameId.getText();
        String email = txtEmailId.getText();
        String contact = txtContId.getText();
        String age = txtAgeId.getText();

        MemberDTO memberDTO = new MemberDTO(
                id,
                dPlanId,
                name,
                email,
                contact,
                age
        );

        boolean isSave = mModel.save(memberDTO);

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
        String id = txtMemmberId.getText();
        String dPlanId = txtDPlanId.getText();
        String name = txtNameId.getText();
        String email = txtEmailId.getText();
        String contact = txtContId.getText();
        String age = txtAgeId.getText();

        MemberDTO memberDTO = new MemberDTO(
                id,
                dPlanId,
                name,
                email,
                contact,
                age
        );

        boolean isUpdate = mModel.update(memberDTO);
        if (isUpdate) {
            setNextId();
            loadtable();
            new Alert(Alert.AlertType.INFORMATION, "Updated Successfully").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Update Failed").show();
        }
    }

    public void tableClickOnAction(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        MemberDTO selectedItem = tblMember.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            txtMemmberId.setText(selectedItem.getMemberId());
            txtDPlanId.setText(selectedItem.getDietPlanId());
            txtNameId.setText(selectedItem.getName());
            txtAgeId.setText(selectedItem.getAge());
            txtEmailId.setText(selectedItem.getEmail());
            txtContId.setText(selectedItem.getPhone());


//            // save button disable
//            btnSave.setDisable(true);
//
//            // update, delete button enable
//            btnUpdate.setDisable(false);
//            btnDelete.setDisable(false);
        }
    }

}
