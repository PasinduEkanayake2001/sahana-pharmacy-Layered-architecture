package lk.ijse.pharmacy.repository;

import lk.ijse.pharmacy.db.DbConnection;
import lk.ijse.pharmacy.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepo {

    public static boolean save;

    public static boolean save(Customer customer) throws SQLException {
        String sql = "INSERT INTO customer VALUES(?, ?, ?, ?, ?)";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, customer.getCustomerId());
        pstm.setObject(2, customer.getName());
        pstm.setObject(3, customer.getAddress());
        pstm.setObject(4, customer.getTel());
        pstm.setObject(5, customer.getUserId());

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Customer customer) throws SQLException {
        String sql = "UPDATE customer SET name = ?, address = ?, tel = ?, userId = ? WHERE customerId = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, customer.getName());
        pstm.setObject(2, customer.getAddress());
        pstm.setObject(3, customer.getTel());
        pstm.setObject(4, customer.getUserId());
        pstm.setObject(5, customer.getCustomerId());

        return pstm.executeUpdate() > 0;
    }

    public static Customer searchById(String customerId) throws SQLException {
        String sql = "SELECT * FROM customer WHERE customerId = ?";

        Connection connection =DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, customerId);

        ResultSet rs = pstm.executeQuery();
        if (rs.next()){
            String cus_id = rs.getString(1);
            String name = rs.getString(2);
            String address = rs.getString(3);
            String tel = rs.getString(4);
            String user_id = rs.getString(5);

            Customer customer = new Customer(cus_id, name, address, tel, user_id);

            return customer;
        }
        return null;
    }

    public static List<Customer> getAll() throws SQLException {
        String sql = "SELECT * FROM customer";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        List<Customer> cusList = new ArrayList<>();

        while (resultSet.next()){
            String customerId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String userId = resultSet.getString(4);
            String tel = resultSet.getString(5);


            Customer customer = new Customer(customerId, name, address, userId, tel);
            cusList.add(customer);
        }
        return cusList;
    }

    public static boolean delete(String customerId) throws SQLException {
        String sql = "DELETE FROM customer WHERE customerId = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, customerId);

        return pstm.executeUpdate() > 0;
    }
}
