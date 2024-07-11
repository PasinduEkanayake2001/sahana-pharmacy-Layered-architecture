package lk.ijse.pharmacy.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.pharmacy.model.Customer;
import lk.ijse.pharmacy.model.tm.CustomerTm;
import lk.ijse.pharmacy.repository.CustomerRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class CustomerFormController {

    @FXML
    private AnchorPane CustomerRootNode;
    @FXML
    private TextField txtCustomerAddress;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtCustomerTel;

    @FXML
    private TextField txtUserId;

    @FXML
    private TableColumn<?, ?> colCustomerAddress;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableColumn<?, ?> colCustomerTel;

    @FXML
    private TableColumn<?, ?> colCustomerId;
    @FXML
    private TableColumn<?, ?> colUserId;

    @FXML
    private TableView<CustomerTm> tblCustomer;

    public void initialize() {
        setCellValueFactory();
        loadAllCustomers();
    }

    public void setCellValueFactory() {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colCustomerTel.setCellValueFactory(new PropertyValueFactory<>("Tel"));

    }

    private void loadAllCustomers() {
        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();

        try {
            List<Customer> customerList = CustomerRepo.getAll();
            for (Customer customer : customerList) {
                CustomerTm tm = new CustomerTm(
                        customer.getCustomerId(),
                        customer.getName(),
                        customer.getAddress(),
                        customer.getUserId(),
                        customer.getTel()
                );
                obList.add(tm);
            }
            tblCustomer.setItems(obList);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


        @FXML
    void btnSaveOnAction(ActionEvent event) {
            String customerId = txtCustomerId.getText();
            String customerName = txtCustomerName.getText();
            String customerAddress = txtCustomerAddress.getText();
            String customerTel = txtCustomerTel.getText();
            String userID = txtUserId.getText();

        Customer customer =new Customer(customerId, customerName, customerAddress,  customerTel,userID);
        try {
            boolean isSaved = CustomerRepo.save(customer);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer is saved").show();
                clearFields();
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }loadAllCustomers();
        }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String customerId = txtCustomerId.getText();
        String customerName = txtCustomerName.getText();
        String customerAddress = txtCustomerAddress.getText();
        String customerTel = txtCustomerTel.getText();
        String userID = txtUserId.getText();

        Customer customer =new Customer(customerId, customerName, customerAddress, customerTel, userID);

        try {
            boolean isUpdated = CustomerRepo.update(customer);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer is updated").show();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }loadAllCustomers();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String customerId = txtCustomerId.getText();

        try {
            boolean isDeleted = CustomerRepo.delete(customerId);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer is deleted").show();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }loadAllCustomers();
    }


    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    public void txtSearchOnAction(ActionEvent actionEvent) throws SQLException {
        String customerId = txtCustomerId.getText();
        Customer customer =CustomerRepo.searchById(customerId);

        if (customer != null){
            txtCustomerId.setText(customer.getCustomerId());
            txtCustomerName.setText(customer.getName());
            txtCustomerAddress.setText(customer.getAddress());
            txtCustomerTel.setText(customer.getTel());
            txtUserId.setText(customer.getUserId());
        }else{
            new Alert(Alert.AlertType.INFORMATION, "customer not found").show();
        }
    }


    private void clearFields() {
        txtCustomerId.setText("");
        txtCustomerName.setText("");
        txtCustomerAddress.setText("");
        txtCustomerTel.setText("");
        txtUserId.setText("");
    }

}

