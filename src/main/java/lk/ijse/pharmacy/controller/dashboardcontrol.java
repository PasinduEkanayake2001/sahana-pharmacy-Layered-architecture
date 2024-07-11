package lk.ijse.pharmacy.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.pharmacy.model.tm.OrdersTm;

import java.net.URL;
import java.util.ResourceBundle;

public class dashboardcontrol implements Initializable {
    @FXML
    private TableColumn<OrdersTm, String> colCustomerId;

    @FXML
    private TableColumn<OrdersTm, String> colDate;


    @FXML
    private TableColumn<OrdersTm, String> colOrdersId;

    @FXML
    private TableColumn<OrdersTm, String> colPaymentId;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setColmnValues();


    }
    private void setColmnValues() {
        colOrdersId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }
}
