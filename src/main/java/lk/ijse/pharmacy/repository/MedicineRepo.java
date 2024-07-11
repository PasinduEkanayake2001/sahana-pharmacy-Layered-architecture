package lk.ijse.pharmacy.repository;

import lk.ijse.pharmacy.db.DbConnection;
import lk.ijse.pharmacy.model.Medicine;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MedicineRepo {
    public static boolean add(Medicine medicine) throws SQLException {
        String sql = "INSERT INTO medicine VALUES(?, ?, ?, ?, ?, ?)";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, medicine.getMedicineId());
        pstm.setObject(2, medicine.getName());
        pstm.setObject(3, medicine.getDescription());
        pstm.setObject(4, medicine.getQtyOnHand());
        pstm.setObject(5, medicine.getPrice());
        pstm.setObject(6, medicine.getShelfId());

        return pstm.executeUpdate() > 0;

    }

    public static boolean update(Medicine medicine) throws SQLException {
        String sql = "UPDATE medicine SET name = ?, description = ?, qtyOnHand = ?, price = ?, shelfId = ? WHERE medicineId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, medicine.getName());
        pstm.setObject(2, medicine.getDescription());
        pstm.setObject(3, medicine.getQtyOnHand());
        pstm.setObject(4, medicine.getPrice());
        pstm.setObject(5, medicine.getShelfId());
        pstm.setObject(6, medicine.getMedicineId());

        return pstm.executeUpdate() > 0;

    }

    public static Medicine searchById(String medicineId) throws SQLException {
        String sql = "SELECT * FROM medicine WHERE medicineId = ?";
        Connection connection =DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, medicineId);

        ResultSet rs = pstm.executeQuery();
        if (rs.next()){
            String med_id = rs.getString(1);
            String name = rs.getString(2);
            String description = rs.getString(3);
            String qtyOnHand = rs.getString(4);
            double price = rs.getDouble(5);
            String shelfId = rs.getString(6);

            Medicine medicine = new Medicine(med_id, name, description, qtyOnHand, price, shelfId);

            return medicine;
        }
        return null;
    }

    public static boolean delete(String medicineId) throws SQLException {
        String sql = "DELETE FROM medicine WHERE medicineId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, medicineId);

        return pstm.executeUpdate() > 0;
    }

    public static List<Medicine> getAll() throws SQLException {
        String sql = "SELECT * FROM medicine";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        List<Medicine> medList = new ArrayList<>();

        while (resultSet.next()) {
            String medicineId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String description = resultSet.getString(3);
            String qtyOnHand = resultSet.getString(4);
            double price = resultSet.getDouble(5);
            String shelfId = resultSet.getString(6);



            Medicine medicine = new Medicine(medicineId, name, description, qtyOnHand, price, shelfId);
            medList.add(medicine);
        }
        return medList;
    }

    @SneakyThrows
    public static boolean reduceMedicineStock(String code, double buyqty) {
        Medicine medicine = MedicineRepo.searchById(code);
        double qty = Double.parseDouble(medicine.getQtyOnHand());
        double nowqty = qty - buyqty;
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(" update medicine set qtyOnHand = ? where medicineId =?");
        preparedStatement.setObject(1,String.valueOf(nowqty));
        preparedStatement.setObject(2,code);

        return preparedStatement.executeUpdate() > 0;

    }
}