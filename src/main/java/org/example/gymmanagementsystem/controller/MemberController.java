package org.example.gymmanagementsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.gymmanagementsystem.dto.MemberDTO;
import org.example.gymmanagementsystem.model.DiatPlanModel;
import org.example.gymmanagementsystem.model.MemberModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

public class MemberController {

    private final MemberModel mModel = new MemberModel();
    public ComboBox cmbDiatPlan;

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
    private TextField txtMemmberId;


    @FXML
    private TextField txtEmailId;

    @FXML
    private TextField txtNameId;

    // Email validation pattern
    private static final String EMAIL_PATTERN =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    // Contact validation pattern (Sri Lankan phone numbers)
    private static final String CONTACT_PATTERN = "^(\\+94|0)(7[0-9]{8}|[1-9][0-9]{8})$";

    private final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
    private final Pattern contactPattern = Pattern.compile(CONTACT_PATTERN);

    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactory();
        setNextId();
        cmbDiatPlan.setItems(DiatPlanModel.getAllDiatPlanId());

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
        String nextId = MemberModel.getnextId();
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

    // Email validation method
    private boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return emailPattern.matcher(email.trim()).matches();
    }

    // Contact validation method
    private boolean isValidContact(String contact) {
        if (contact == null || contact.trim().isEmpty()) {
            return false;
        }
        return contactPattern.matcher(contact.trim()).matches();
    }

    // Input validation method
    private boolean validateInputs(String email, String contact) {
        if (!isValidEmail(email)) {
            new Alert(Alert.AlertType.ERROR,
                    "Invalid email format. Please enter a valid email address.").show();
            return false;
        }

        if (!isValidContact(contact)) {
            new Alert(Alert.AlertType.ERROR,
                    "Invalid contact number. Please enter a valid Sri Lankan phone number.\n" +
                            "Format: 0771234567 or +94771234567").show();
            return false;
        }

        return true;
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtMemmberId.getText();

        if (id == null || id.trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a member to delete.", ButtonType.OK).show();
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Delete Confirmation");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("Are you sure you want to delete the member with ID: " + id + "?");

        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean isDelete = mModel.delete(id);
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
        txtMemmberId.clear();
        cmbDiatPlan.getSelectionModel().clearSelection();
        txtNameId.clear();
        txtEmailId.clear();
        txtContId.clear();
        txtAgeId.clear();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtMemmberId.getText();
        String dPlanId = cmbDiatPlan.getValue().toString();
        String name = txtNameId.getText();
        String email = txtEmailId.getText();
        String contact = txtContId.getText();
        String age = txtAgeId.getText();

        // Validate email and contact before saving
        if (!validateInputs(email, contact)) {
            return;
        }

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
        String dPlanId = cmbDiatPlan.getValue().toString();
        String name = txtNameId.getText();
        String email = txtEmailId.getText();
        String contact = txtContId.getText();
        String age = txtAgeId.getText();

        // Validate email and contact before updating
        if (!validateInputs(email, contact)) {
            return;
        }

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
            cmbDiatPlan.setValue(selectedItem.getDietPlanId());
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