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
import lk.ijse.pharmacy.model.Customer;
import lk.ijse.pharmacy.model.Supplier;
import lk.ijse.pharmacy.model.tm.CustomerTm;
import lk.ijse.pharmacy.model.tm.SupplierTm;
import lk.ijse.pharmacy.repository.CustomerRepo;
import lk.ijse.pharmacy.repository.SupplierRepo;

import java.sql.SQLException;
import java.util.List;

public class SupplierFormController {
    @FXML
    private AnchorPane SupplierRootNode;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCompanyName;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> colSupplierName;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private TableView<SupplierTm> tblSupplier;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCompanyName;

    @FXML
    private TextField txtSupplierId;

    @FXML
    private TextField txtSupplierName;

    @FXML
    private TextField txtTel;


    public void initialize() {
        setCellValueFactory();
        loadAllCustomers();
    }

    private void setCellValueFactory() {
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        colCompanyName.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
    }
    private void loadAllCustomers() {
        ObservableList<SupplierTm> obList = FXCollections.observableArrayList();

        try {
            List<Supplier> supplierList = SupplierRepo.getAll();
            for (Supplier supplier : supplierList) {
                SupplierTm tm = new SupplierTm(
                        supplier.getSupplierId(),
                        supplier.getSupplierName(),
                        supplier.getCompanyName(),
                        supplier.getAddress(),
                        supplier.getTel()
                );
                obList.add(tm);
            }
            tblSupplier.setItems(obList);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String supplierId = txtSupplierId.getText();
        String supplierName = txtSupplierName.getText();
        String companyName = txtCompanyName.getText();
        String address = txtAddress.getText();
        String tel = txtTel.getText();

        Supplier supplier = new Supplier(supplierId, supplierName, companyName, address, tel);

        try {
            boolean isSaved = SupplierRepo.save(supplier);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "supplier is saved").show();
                clearFields();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String supplierId = txtSupplierId.getText();
        String supplierName = txtSupplierName.getText();
        String companyName = txtCompanyName.getText();
        String address = txtAddress.getText();
        String tel = txtTel.getText();

        Supplier supplier = new Supplier(supplierId, supplierName, companyName, address, tel);


        try {
            boolean isUpdated = SupplierRepo.update(supplier);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "supplier is updated").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String supplierId = txtSupplierId.getText();

        try {
            boolean isDeleted = SupplierRepo.delete(supplierId);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "supplier is deleted").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws SQLException {
        String supplierId = txtSupplierId.getText();
        Supplier supplier = SupplierRepo.searchById(supplierId);

        if(supplier != null) {
            txtSupplierId.setText(supplier.getSupplierId());
            txtSupplierName.setText(supplier.getSupplierName());
            txtCompanyName.setText(supplier.getCompanyName());
            txtAddress.setText(supplier.getAddress());
            txtTel.setText(supplier.getTel());
        }else{
            new Alert(Alert.AlertType.INFORMATION, "supplier not found").show();
        }
    }

    private void clearFields() {
        txtSupplierId.setText("");
        txtSupplierName.setText("");
        txtCompanyName.setText("");
        txtAddress.setText("");
        txtTel.setText("");
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }
}


