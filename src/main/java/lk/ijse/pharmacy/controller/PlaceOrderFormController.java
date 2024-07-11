package lk.ijse.pharmacy.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lk.ijse.pharmacy.model.*;
import lk.ijse.pharmacy.model.tm.PlaceOrderTm;
import lk.ijse.pharmacy.repository.CustomerRepo;
import lk.ijse.pharmacy.repository.MedicineRepo;
import lk.ijse.pharmacy.repository.OrderRepo;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PlaceOrderFormController implements Initializable {

    public  static AnchorPane pane;
    @FXML
    private TextField custTelField;

    @FXML
    private JFXComboBox<String> itemCodeCombo;

    @FXML
    private Label orderIdlbl;

    @FXML
    private Label customerNamelbl;

    @FXML
    private Label itemDescriptionlbl;

    @FXML
    private Label itemUnitpricelbl;

    @FXML
    private Label itemqtyonHand;

    @FXML
    private TextField buyqtyitem;

    @FXML
    private Label netTtotallbl;

    @FXML
    private TableColumn<PlaceOrderTm, JFXButton> colAction;

    @FXML
    private TableColumn<PlaceOrderTm, String> colCode;

    @FXML
    private TableColumn<PlaceOrderTm, String> colDes;

    @FXML
    private TableColumn<PlaceOrderTm, String> colQty;

    @FXML
    private TableColumn<PlaceOrderTm, String> colTotal;

    @FXML
    private TableColumn<PlaceOrderTm, String> colUnitprice;

    @FXML
    private TableView<PlaceOrderTm> tblplaceOrder;

    OrderRepo orderRepo = new OrderRepo();

    ObservableList<PlaceOrderTm> observableList = FXCollections.observableArrayList();

    double netTotal = 0;


    @FXML
    void onCustomertelTyping(KeyEvent event) throws SQLException {
        String text = custTelField.getText();
        List<Customer> all = CustomerRepo.getAll();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getTel().equals(text)){
                customerNamelbl.setText(all.get(i).getName());
            }
        }
    }
    @FXML
    void onMedicineCodeSelecting(ActionEvent event) throws SQLException {
        String value = itemCodeCombo.getValue();
        List<Medicine> all = MedicineRepo.getAll();
        for (int i = 0; i < all.size(); i++) {
            if (value.equals(all.get(i).getMedicineId())){
                itemDescriptionlbl.setText(all.get(i).getDescription());
                itemqtyonHand.setText(all.get(i).getQtyOnHand());
                itemUnitpricelbl.setText(String.valueOf(all.get(i).getPrice()));
            }
        }

    }
    @FXML
    void onaddCartClick(ActionEvent event) {
        double x = Double.parseDouble(itemqtyonHand.getText());
        double buyqty = Double.parseDouble(buyqtyitem.getText());
        if (x > buyqty) {
            String code = itemCodeCombo.getValue();
            String des = itemDescriptionlbl.getText();


            MedicineRepo.reduceMedicineStock(code, buyqty);

            double unitprice = Double.parseDouble(itemUnitpricelbl.getText());
            double total = buyqty * unitprice;
            netTotal += total;
            netTtotallbl.setText(String.valueOf(netTotal));
            observableList.add(new PlaceOrderTm(code, des, String.valueOf(buyqty), String.valueOf(unitprice), String.valueOf(total), new JFXButton("Delete")));
            loadValues();
            clearFileds();
        }else {
            new Alert(Alert.AlertType.ERROR,"cant get this amount").show();
        }
    }

    private void clearFileds() {
        itemCodeCombo.setValue("");
        itemDescriptionlbl.setText("");
        itemUnitpricelbl.setText("");
        itemqtyonHand.setText("");
        buyqtyitem.setText("");
    }

    private void loadValues() {
        tblplaceOrder.setItems(observableList);

        for (int i = 0; i < observableList.size(); i++) {
            observableList.get(i).getDelete().setStyle("-fx-background-color: rgba(175, 108, 108, 1)");
            observableList.get(i).getDelete().setTextFill(Color.WHITE);


            int finalI = i;
            double total = Double.parseDouble(observableList.get(i).getTotal());
            observableList.get(i).getDelete().setOnAction(event -> {
                observableList.remove(finalI);
                netTotal -= total;
                netTtotallbl.setText(String.valueOf(netTotal));
            });
        }



    }
    @FXML
    void onPlaceOrderClick(ActionEvent event) throws IOException, SQLException {

        ArrayList<OrdersMedicineDetail> arrayList = new ArrayList<>();

        Orders orders = new Orders();
        orders.setOrdersId(orderIdlbl.getText());
        String now = String.valueOf(LocalDate.now());
        orders.setDate(now);
        String custId = null;
        List<Customer> all = CustomerRepo.getAll();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getTel().equals(custTelField.getText())){
                custId = all.get(i).getCustomerId();
            }
        }
        orders.setCustomerId(custId);

        for (int i = 0; i < observableList.size(); i++) {
            arrayList.add(new OrdersMedicineDetail(orderIdlbl.getText(),observableList.get(i).getItemCode()));
        }

        Stage stage = new Stage();
        OrderPaymentController.total = netTotal;
        OrderPaymentController.orders =orders;
        OrderPaymentController.arrayList = arrayList;
        OrderPaymentController.pane = pane;
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/orderPayment_form.fxml"))));
        stage.show();

        Stage stage1 = (Stage) itemUnitpricelbl.getScene().getWindow();
        stage1.close();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        itemCodeComboSettingValues();
        setOrderID();
        setTbleCloumnValues();
    }

    private void setTbleCloumnValues() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDes.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitprice.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("delete"));
    }

    private void setOrderID() {
        ArrayList<Orders> allOrders = orderRepo.getAllOrders();
        if (allOrders.isEmpty()) {
            orderIdlbl.setText("ORD001"); // Starting ID if no orders exist
            return;
        }
        String ordersId = allOrders.get(allOrders.size() - 1).getOrdersId();
        String[] ar = ordersId.split("");
        int x = Integer.parseInt(ar[ar.length-1]);
        ar[ar.length-1] = String.valueOf(x+1);
        String join = String.join("", ar);

        orderIdlbl.setText(join);

    }


    private void itemCodeComboSettingValues() {
        List<Medicine> all = null;
        try {
            all = MedicineRepo.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ObservableList<String> observableList = FXCollections.observableArrayList();
        for (int i = 0; i < all.size(); i++) {
            observableList.add(all.get(i).getMedicineId());
        }
        itemCodeCombo.setItems(observableList);
    }
}
