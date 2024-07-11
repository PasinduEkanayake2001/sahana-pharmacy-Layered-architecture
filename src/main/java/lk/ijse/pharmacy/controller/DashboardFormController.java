package lk.ijse.pharmacy.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DashboardFormController implements Initializable {
    @FXML
    private AnchorPane rootNode;
    @FXML
    private AnchorPane mainRootNode;
    @FXML
    private Label date_time;

    @FXML
    private Label dashUserId;

    @FXML
    private Label dashUserName;

    public void displayUserIdUserName(){

    }


    public void runTime(){
        new Thread(){
            public void run() {
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
                while (true) {
                    try {
                        Thread.sleep(1000);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Platform.runLater(() -> {
                        date_time.setText(format.format(new Date()));
                    });
                }
            }
        }.start();
    }
    @Override
    public void initialize(URL url, java.util.ResourceBundle resourceBundle) {
        runTime();
        try {
            Parent rootNode = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));

        mainRootNode.getChildren().clear();
        mainRootNode.getChildren().add(rootNode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login form");
    }


    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
      Parent rootNode = FXMLLoader.load(getClass().getResource("/view/customer_form.fxml"));
      this.mainRootNode.getChildren().clear();
      this.mainRootNode.getChildren().add(rootNode);


    }

    @FXML
    void btnOrdersOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/orders_form.fxml"));
        this.mainRootNode.getChildren().clear();
        this.mainRootNode.getChildren().add(rootNode);
    }

    @FXML
    void btnMdOnAction(ActionEvent event) throws IOException {
      Parent rootNode = FXMLLoader.load(getClass().getResource("/view/medicine_form.fxml"));
      this.mainRootNode.getChildren().clear();
      this.mainRootNode.getChildren().add(rootNode);
    }


    @FXML
    void btnSupplierOnAction(ActionEvent event) throws IOException {
      Parent rootNode = FXMLLoader.load(getClass().getResource("/view/supplier_form.fxml"));
      this.mainRootNode.getChildren().clear();
      this.mainRootNode.getChildren().add(rootNode);
    }
    @FXML
    void btnPaymentOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/payment _form.fxml"));
        this.mainRootNode.getChildren().clear();
        this.mainRootNode.getChildren().add(rootNode);
    }

    public void btnDashboardOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));
        this.mainRootNode.getChildren().clear();
        this.mainRootNode.getChildren().add(rootNode);
    }
}
