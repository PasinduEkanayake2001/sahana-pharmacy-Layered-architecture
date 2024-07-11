package lk.ijse.pharmacy.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.pharmacy.model.Orders;
import lk.ijse.pharmacy.model.OrdersMedicineDetail;
import lk.ijse.pharmacy.model.Payment;
import lk.ijse.pharmacy.repository.PaymentRepo;
import lk.ijse.pharmacy.repository.TransactionRepo;
import lombok.SneakyThrows;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OrderPaymentController implements Initializable {

    public static double total;
    public  static Orders orders;
    public static ArrayList<OrdersMedicineDetail> arrayList;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtPaymentId;

    @FXML
    private TextField txtPaymentMethod;
    public  static AnchorPane pane;

    @SneakyThrows
    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String date = String.valueOf(LocalDate.now());
        Payment payment = new Payment(txtPaymentId.getText(),total,txtPaymentMethod.getText(),date);
        orders.setPaymentId(txtPaymentId.getText());
        boolean b = TransactionRepo.saveTransaction(payment, orders, arrayList);
        if (b){
            Stage stage = (Stage) txtPaymentId.getScene().getWindow();
            stage.close();
            pane.getChildren().clear();
            pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/orders_form.fxml")));
        }
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtAmount.setText(String.valueOf(total));
        setPaymentId();
    }

    private void setPaymentId() throws SQLException {
        List<Payment> all = PaymentRepo.getAll();
        if (all.size() > 0) {
            String paymentId = all.get(all.size() - 1).getPaymentId();
            String[] split = paymentId.split("");
            int s = Integer.parseInt(split[split.length - 1])+1;
            split[split.length -1] = String.valueOf(s);
            String join = String.join("", split);
            txtPaymentId.setText(join);
        }
        else {
            txtPaymentId.setText("P001");
        }
    }
}
