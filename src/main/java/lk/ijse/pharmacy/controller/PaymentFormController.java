package lk.ijse.pharmacy.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pharmacy.db.DbConnection;
import lk.ijse.pharmacy.model.Payment;
import lk.ijse.pharmacy.model.tm.PaymentTm;
import lk.ijse.pharmacy.repository.PaymentRepo;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentFormController {
    @FXML
    private AnchorPane PaymentRootNode;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colPaymentId;

    @FXML
    private TableColumn<?, ?> colPaymentMethod;

    @FXML
    private TableView<PaymentTm> tblPayment;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtPaymentId;

    @FXML
    private TextField txtPaymentMethod;


    public void initialize() {
        setCellValueFactory();
        loadAllPayments();
    }

    private void setCellValueFactory() {
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colPaymentMethod.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    private void loadAllPayments() {
        ObservableList<PaymentTm> obList = FXCollections.observableArrayList();

        try {
            List<Payment> paymentList = PaymentRepo.getAll();
            for (Payment payment : paymentList) {
                PaymentTm tm = new PaymentTm(
                        payment.getPaymentId(),
                        payment.getAmount(),
                        payment.getPaymentMethod(),
                        payment.getDate()
                );

                obList.add(tm);
            }

            tblPayment.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtPaymentId.getText();

        try {
            boolean isDeleted = PaymentRepo.delete(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "payment deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        loadAllPayments();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtPaymentId.getText();
        double amount = Double.parseDouble(txtAmount.getText());
        String paymentMethod = txtPaymentMethod.getText();
        String date = txtDate.getText();

        Payment payment = new Payment(id, amount, paymentMethod, date);

        try {
            boolean isSaved = PaymentRepo.save(payment);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Payment saved!").show();
                clearFields();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        loadAllPayments();


    }

    private void clearFields() {
        txtPaymentId.setText("");
        txtAmount.setText("");
        txtPaymentMethod.setText("");
        txtDate.setText("");

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtPaymentId.getText();
        double amount = Double.parseDouble(txtAmount.getText());
        String paymentMethod = txtPaymentMethod.getText();
        String date = txtDate.getText();

        Payment payment = new Payment(id, amount, paymentMethod, date);

        try {
            boolean isUpdated = PaymentRepo.update(payment);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "payment updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        loadAllPayments();
    }

    public void txtSearchOnAction(ActionEvent actionEvent) throws SQLException {
        String id = txtPaymentId.getText();

        Payment payment = PaymentRepo.searchById(id);
        if (payment != null) {
            txtPaymentId.setText(payment.getPaymentId());
            txtAmount.setText(Double.toString(payment.getAmount()));
            txtPaymentMethod.setText(payment.getPaymentMethod());
            txtDate.setText(payment.getDate());
        } else {
            new Alert(Alert.AlertType.INFORMATION, "payment not found!").show();
        }
    }

    public void btnPrintBillOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/report/CBILL.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        Map<String, Object> data = new HashMap<>();
        data.put("CustomerID",txtPaymentId.getText());
        data.put("Total",getTotal());

        JasperPrint jasperPrint =
                JasperFillManager.fillReport(jasperReport, data, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint, false);

    }
    public String getTotal(){
        return "5000";
    }


}
/*

*/


