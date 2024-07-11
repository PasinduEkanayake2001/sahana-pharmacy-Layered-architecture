package lk.ijse.pharmacy.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.pharmacy.db.DbConnection;

import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {
    @FXML
    private CheckBox loginCheckBox;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private TextField loginShowPassword;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtUserName;


    @FXML
    void loginShowPassword(ActionEvent event) {
        if (loginCheckBox.isSelected()){
            loginShowPassword.setText(loginPassword.getText());
            loginShowPassword.setVisible(true);
            loginPassword.setVisible(false);
        }else{
            loginPassword.setText(loginShowPassword.getText());
            loginShowPassword.setVisible(false);
            loginPassword.setVisible(true);
        }
    }

    @FXML
    void RegisterOnAction(ActionEvent event) throws IOException {
       Parent rootNode =  FXMLLoader.load(this.getClass().getResource("/view/registration_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);

        stage.setTitle("Registration form");
        stage.show();
    }

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        String userName = txtUserName.getText();
        String pw = "";
        if (!(loginCheckBox.isSelected())){

             pw = loginPassword.getText();
        }else {
            pw = loginShowPassword.getText();

        }

        try {
            checkCredential(userName, pw);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void checkCredential(String userName, String pw) throws SQLException, IOException {
        String sql = "SELECT name, password FROM user WHERE name = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, userName);

        ResultSet resultSet =pstm.executeQuery();
        if (resultSet.next()){
            String dbpw = resultSet.getString("password");

            if (pw.equals(dbpw)){
                navigateToTheDashboard();
            }else{
                new Alert(Alert.AlertType.ERROR, "password is incorrect !").show();
            }
        }else {
            new Alert(Alert.AlertType.INFORMATION, "user name is incorrect !").show();
        }
    }

    private void navigateToTheDashboard() throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard form");
        stage.show();
        stage.centerOnScreen();

    }
}
