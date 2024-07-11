package lk.ijse.pharmacy.repository;

import lk.ijse.pharmacy.db.DbConnection;
import lk.ijse.pharmacy.model.Orders;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class OrderRepo {

    @SneakyThrows
    public ArrayList<Orders> getAllOrders(){
        ArrayList<Orders> orders = new ArrayList<>();
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM orders");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            orders.add(new Orders(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4)));
        }

        return orders;
    }
}
