package org.example.gymmanagementsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.gymmanagementsystem.dto.*;
import org.example.gymmanagementsystem.model.MemberModel;
import org.example.gymmanagementsystem.model.OrderModel;
import org.example.gymmanagementsystem.model.SupplimentModel;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.Properties;

public class OrdersController {
    private final MemberModel mModel = new MemberModel();
    private final SupplimentModel   sModel = new SupplimentModel();
    private final OrderModel oModel = new OrderModel();
    public ComboBox <String> cmbMemberId;
    public ComboBox  <String> cmbSupplementId;
    public TextField txtSuppName;
    public TextField txtsuppPrice;
    public TextField txtsuppQuantity;

    @FXML
    private TableColumn<OrderDTO, String> clmAmount;

    @FXML
    private TableColumn<OrderDTO, String> clmDate;

    @FXML
    private TableColumn<OrderDTO, String> clmName;

    @FXML
    private TableColumn<OrderDTO, String> clmmemberId;

    @FXML
    private TableColumn<OrderDTO, String> clmorderId;

    @FXML
    private DatePicker date;

    @FXML
    private TableView<OrderDTO> tblOrder;

    @FXML
    private TextField txtAmountId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtmemberId;

    @FXML
    private TextField txtorderId;

    public void initialize() {
        try {
            setSuppliment();
            setMemberids();
            //setCellValueFactory();
            setNextId();
            cmbMemberId.setItems(MemberModel.AllMemberId());

            loadTable();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Initialization error: " + e.getMessage()).show();
        }
    }

    private void setSuppliment() throws SQLException, ClassNotFoundException {
        ArrayList<SupplementDTO> allSuppliment = sModel.getAllSuppliment();

        ObservableList<String> data = FXCollections.observableArrayList();
        for (SupplementDTO s : allSuppliment) {
            data.add(s.getSupplimentId());
        }
        cmbSupplementId.setItems(data);
    }

    private void setMemberids() throws SQLException, ClassNotFoundException {
        ArrayList<MemberDTO> allmember = mModel.getAllmember();
        
        ObservableList<String> data = FXCollections.observableArrayList();
        for (MemberDTO m : allmember) {
            data.add(m.getMemberId());
        }
        cmbMemberId.setItems(data);
    }

    private void loadTable() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDTO> orderList = oModel.getAllOrder();
        ObservableList<OrderDTO> data = FXCollections.observableArrayList();
        tblOrder.setItems(data);
    }

    private void setNextId() throws SQLException, ClassNotFoundException {
        String nextId = OrderModel.getNextId();
        txtorderId.setText(nextId);
    }

    private void setCellValueFactory() {
        clmorderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        clmmemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        clmAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtorderId.getText();

        if (id == null || id.trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select an order to delete.").show();
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION, "Delete order ID: " + id + "?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            boolean isDeleted = oModel.delete(id);
            if (isDeleted) {
                setNextId();
                loadTable();
                clearFields();
                new Alert(Alert.AlertType.INFORMATION, "Deleted successfully.").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Deletion failed.").show();
            }
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String name = txtName.getText();
        String orderId = txtorderId.getText();
        String memberId = cmbMemberId.getValue();
        String supplementId = cmbSupplementId.getValue();
        int amount = 1;
        String orderDate = new Date().toString();

        double price = Double.parseDouble(txtsuppPrice.getText());

        OrderDTO orderDTO = new OrderDTO(orderId, memberId, txtName.getText(), orderDate, String.valueOf(price));
        OrderPayment orderPayment = new OrderPayment(price ,orderId);

        boolean isSaved = oModel.save(orderDTO , orderPayment , supplementId);

        if (isSaved) {
            String supplementName = new String(sModel.getSupplementName(supplementId));
            sendEmail(mModel.getEmail(memberId) , price , name, memberId, supplementName);
            setNextId();
            loadTable();
            clearFields();
            new Alert(Alert.AlertType.INFORMATION, "Saved successfully.").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Save failed.").show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String orderId = txtorderId.getText();
        String memberId = txtmemberId.getText();
        String orderName = txtName.getText();
        String orderDate = date.getValue() != null ? date.getValue().toString() : "";
        String orderAmount = txtAmountId.getText();

        if (orderId.isEmpty() || memberId.isEmpty() || orderName.isEmpty() || orderDate.isEmpty() || orderAmount.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select an order to update.").show();
            return;
        }

        OrderDTO orderDTO = new OrderDTO(orderId, memberId, orderName, orderDate, orderAmount);
        boolean isUpdated = oModel.update(orderDTO);

        if (isUpdated) {
            setNextId();
            loadTable();
            clearFields();
            new Alert(Alert.AlertType.INFORMATION, "Updated successfully.").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Update failed.").show();
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtorderId.clear();
        cmbSupplementId.setValue(null);
        txtName.clear();

    }

    @FXML
    void tableClickOnAction(MouseEvent event) {
        OrderDTO selectedItem = tblOrder.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            txtorderId.setText(selectedItem.getOrderId());
            txtmemberId.setText(selectedItem.getMemberId());
            txtName.setText(selectedItem.getName());
            txtAmountId.setText(selectedItem.getAmount());
            date.setValue(LocalDate.parse(selectedItem.getDate()));
        }
    }

    @FXML
    void btnGenarateROnAction(ActionEvent event) {
        new Alert(Alert.AlertType.INFORMATION, "Generate Report clicked.").show();
    }

    public void setSupplimentDataOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String sID = cmbSupplementId.getValue();
        ArrayList<SupplementDTO> allSuppliment = sModel.getAllSuppliment();
        if (sID != null) {
            for (SupplementDTO s : allSuppliment) {
                if (s.getSupplimentId().equals(sID)) {
                    txtSuppName.setText(s.getName());
                    txtsuppPrice.setText(s.getPrice());
                    txtsuppQuantity.setText(s.getQuantity());
                }
            }
        }
    }

    public void setmemberDataOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String mID = cmbMemberId.getValue();
        ArrayList<MemberDTO> allmember = mModel.getAllmember();
        if (mID != null) {
            for (MemberDTO m : allmember) {
                if (m.getMemberId().equals(mID)) {
                    txtName.setText(m.getName());
                }
            }
        }
    }
    private static void sendEmail(String email, double price, String recipientEmail, String memberId,String supplementName) {

        new Thread(() -> {

            String senderEmail = "punchihewadevindi@gmail.com";
            String senderPassword = "ttdeuwznuhfslwdg";
            String subject = "Your Order Alert";
            String body = "Dear Customer,\n" +
                    "This is to inform you that your order has been placed successfully. We will contact you shortly to confirm the details.\n" +
                    "\n price: " + price + "\n" +
                    "\n supplement Name: " + supplementName + "\n" +
            "\n Member ID: " + memberId + "\n" +
            "\n Member Name: " + recipientEmail + "\n" +
                    "Thank you for choosing us.\n" +
                    "Best regards,\n" +
                    "Gym Management System";
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");

            Session session = Session.getInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, senderPassword);
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(senderEmail));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                message.setSubject(subject);

                Multipart multipart = new MimeMultipart();

                MimeBodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setText(body);
                multipart.addBodyPart(messageBodyPart);

                MimeBodyPart attachmentPart = new MimeBodyPart();

                message.setContent(multipart);

                Transport.send(message);

                System.out.println("Email sent successfully with the QR code attachment.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();



    }


}
