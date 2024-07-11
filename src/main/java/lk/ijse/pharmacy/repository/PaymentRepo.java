package lk.ijse.pharmacy.repository;

import lk.ijse.pharmacy.db.DbConnection;
import lk.ijse.pharmacy.model.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepo {
    public static boolean save(Payment payment) throws SQLException {
        String sql = "INSERT INTO payment VALUES(?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1,payment.getPaymentId());
        pstm.setObject(2,payment.getAmount());
        pstm.setObject(3,payment.getPaymentMethod());
        pstm.setObject(4,payment.getDate());

        return  pstm.executeUpdate () > 0;
    }

    public static boolean update(Payment payment) throws SQLException {
        String sql = "UPDATE payment SET amount = ?, paymentMethod = ?, date = ? WHERE paymentId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1,payment.getAmount());
        pstm.setObject(2,payment.getPaymentMethod());
        pstm.setObject(3,payment.getDate());
        pstm.setObject(4,payment.getPaymentId());

        return pstm.executeUpdate () > 0;
    }

    public static boolean delete(String id) throws SQLException {

        String sql = "DELETE FROM payment WHERE paymentId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, id);
        return pstm.executeUpdate () > 0;
    }

    public static Payment searchById(String id) throws SQLException {
        String sql = "SELECT * FROM payment WHERE paymentId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet rs = pstm.executeQuery();
        if (rs.next()){
            String pay_id = rs.getString(1);
            double amount = rs.getDouble(2);
            String payment_method = rs.getString(3);
            String date = rs.getString(4);

            Payment payment = new Payment(pay_id,amount,payment_method,date);

            return payment;
        }
        return null;
    }

    public static List<Payment> getAll() throws SQLException {
        String sql = "SELECT * FROM payment";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Payment> payList = new ArrayList<>();

        while (resultSet.next()) {
            String pay_id = resultSet.getString(1);
            double amount = resultSet.getDouble(2);
            String pay_method = resultSet.getString(3);
            String date = resultSet.getString(4);

            Payment customer = new Payment(pay_id, amount, pay_method, date);
            payList.add(customer);
        }
        return payList;
    }
}
