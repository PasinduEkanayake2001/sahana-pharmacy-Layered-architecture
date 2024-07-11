package lk.ijse.pharmacy.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.pharmacy.model.Orders;
import lk.ijse.pharmacy.model.tm.OrdersTm;
import lk.ijse.pharmacy.repository.OrderRepo;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrdersFormController implements Initializable {
    @FXML
    private TableColumn<OrdersTm, String> colCustomerId;

    @FXML
    private TableColumn<OrdersTm, String> colDate;

    @FXML
    private TableColumn<OrdersTm, JFXButton> colDelete;

    @FXML
    private TableColumn<OrdersTm, String> colOrdersId;

    @FXML
    private TableColumn<OrdersTm, String> colPaymentId;


    @FXML
    private TableView<OrdersTm> tblOrders;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtOrdersId;

    @FXML
    private TextField txtPaymentId;
    OrderRepo orderRepo = new OrderRepo();
    @FXML
    private AnchorPane ancpane;

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws IOException {
        PlaceOrderFormController.pane = ancpane;
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/place_order_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setTitle("Place_Order_Form");
        stage.setScene(scene);
        stage.centerOnScreen();

        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setColmnValues();
        loadValues();
    }

    private void loadValues() {
        ArrayList<Orders> allOrders = orderRepo.getAllOrders();
        ObservableList<OrdersTm> observableList = FXCollections.observableArrayList();
        for (int i = 0; i < allOrders.size(); i++) {
            observableList.add(new OrdersTm(allOrders.get(i).getOrdersId(),allOrders.get(i).getPaymentId(),allOrders.get(i).getCustomerId(),allOrders.get(i).getDate()));
        }

        tblOrders.setItems(observableList);

    }

    private void setColmnValues() {
        colOrdersId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }
}
