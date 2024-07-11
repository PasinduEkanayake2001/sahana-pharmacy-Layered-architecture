package lk.ijse.pharmacy.repository;

import lk.ijse.pharmacy.db.DbConnection;
import lk.ijse.pharmacy.model.Orders;
import lk.ijse.pharmacy.model.OrdersMedicineDetail;
import lk.ijse.pharmacy.model.Payment;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class TransactionRepo {
    @SneakyThrows
    public static boolean saveTransaction(Payment payment , Orders orders , ArrayList<OrdersMedicineDetail> arrayList){

        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO payment VALUES(?, ?, ?, ?)";;
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,payment.getPaymentId());
        pstm.setObject(2,payment.getAmount());
        pstm.setObject(3,payment.getPaymentMethod());
        pstm.setObject(4,payment.getDate());

        int i = pstm.executeUpdate();

        String sql2 = "INSERT INTO orders VALUES(?, ?, ?, ?)";
        PreparedStatement pstm2 = connection.prepareStatement(sql2);
        pstm2.setObject(1,orders.getOrdersId());
        pstm2.setObject(2,orders.getDate());
        pstm2.setObject(3,orders.getPaymentId());
        pstm2.setObject(4,orders.getCustomerId());

        int i1 = pstm2.executeUpdate();

        System.out.println(arrayList.size());
        for (int j = 0; j < arrayList.size(); j++) {
            String sql3 = "INSERT INTO ordersmedicinedetail VALUES(?, ?)";
            PreparedStatement pstm3 = connection.prepareStatement(sql3);
            pstm3.setObject(1,arrayList.get(j).getOrdersId());
            pstm3.setObject(2,arrayList.get(j).getMedicineId());
            int i2 = pstm3.executeUpdate();
        }
        connection.commit();
        if (i > 0 && i1 > 0){
            return true;
        }else {
            return false;
        }

    }
}