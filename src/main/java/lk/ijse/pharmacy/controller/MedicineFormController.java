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
import lk.ijse.pharmacy.model.Medicine;
import lk.ijse.pharmacy.model.tm.MedicineTm;
import lk.ijse.pharmacy.repository.CustomerRepo;
import lk.ijse.pharmacy.repository.MedicineRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MedicineFormController {

    @FXML
    private AnchorPane MedicineRootNode;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtMedicineId;

    @FXML
    private TextField txtMedicineName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtShelfId;

    @FXML
    private TableColumn<?, ?> colMedicineDescription;

    @FXML
    private TableColumn<?, ?> colMedicineId;

    @FXML
    private TableColumn<?, ?> colMedicineName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private TableColumn<?, ?> colShelfId;

    @FXML
    private TableView<MedicineTm> tblMedicine;

    public void initialize() {
        setCellValueFactory();
        loadAllMedicine();
    }

    private void setCellValueFactory() {
        colMedicineId.setCellValueFactory(new PropertyValueFactory<>("medicineId"));
        colMedicineName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colMedicineDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colShelfId.setCellValueFactory(new PropertyValueFactory<>("shelfId"));
    }

    private void loadAllMedicine() {
        ObservableList<MedicineTm> obList = FXCollections.observableArrayList();


        try {
            List<Medicine> medicineList = MedicineRepo.getAll();
            for (Medicine medicine : medicineList) {
                MedicineTm tm = new MedicineTm(
                       medicine.getMedicineId(),
                       medicine.getName(),
                       medicine.getDescription(),
                        medicine.getQtyOnHand(),
                        medicine.getPrice(),
                        medicine.getShelfId()

                );

                obList.add(tm);
            }

            tblMedicine.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String medicineId = txtMedicineId.getText();
        String medicineName = txtMedicineName.getText();
        String description = txtDescription.getText();
        String medicineQty = txtQtyOnHand.getText();
        double medicinePrice = Double.parseDouble(txtPrice.getText());
        String medicineShelfId = txtShelfId.getText();

        Medicine medicine = new Medicine(medicineId, medicineName, description, medicineQty, medicinePrice, medicineShelfId);
        try {
            boolean isAdd = MedicineRepo.add(medicine);
            if (isAdd) {
                new Alert(Alert.AlertType.INFORMATION, "Medicine Added Successfully").show();
                clearFields();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        loadAllMedicine();
    }

    private void clearFields() {
        txtMedicineId.setText("");
        txtMedicineName.setText("");
        txtDescription.setText("");
        txtQtyOnHand.setText("");
        txtPrice.setText("");
        txtShelfId.setText("");
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String medicineId = txtMedicineId.getText();
        String medicineName = txtMedicineName.getText();
        String description = txtDescription.getText();
        String medicineQty = txtQtyOnHand.getText();
        double medicinePrice = Double.parseDouble(txtPrice.getText());
        String medicineShelfId = txtShelfId.getText();

        Medicine medicine = new Medicine(medicineId,medicineName,description,medicineQty,medicinePrice,medicineShelfId);

        try {
            boolean isUpdated = MedicineRepo.update(medicine);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "medicine is updated").show();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        loadAllMedicine();

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String medicineId = txtMedicineId.getText();


        try {
            boolean isDeleted = MedicineRepo.delete(medicineId);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "medicine is deleted").show();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        loadAllMedicine();
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws SQLException {
        String medicineId = txtMedicineId.getText();
        Medicine medicine= MedicineRepo.searchById(medicineId);

        if (medicine != null){
            txtMedicineId.setText(medicine.getMedicineId());
            txtMedicineName.setText(medicine.getName());
            txtDescription.setText(medicine.getDescription());
            txtQtyOnHand.setText(medicine.getQtyOnHand());
            txtPrice.setText(Double.toString(medicine.getPrice()));
            txtShelfId.setText(medicine.getShelfId());


        }else {
            new  Alert(Alert.AlertType.INFORMATION, "Medicine Not Found").show();
        }
    }

}

