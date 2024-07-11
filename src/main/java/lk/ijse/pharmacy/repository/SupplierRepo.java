package lk.ijse.pharmacy.repository;

import lk.ijse.pharmacy.db.DbConnection;
import lk.ijse.pharmacy.model.Customer;
import lk.ijse.pharmacy.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepo {
    public static boolean save(Supplier supplier) throws SQLException {
        String sql = "INSERT INTO supplier VALUES(?, ?, ?, ?, ?)";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, supplier.getSupplierId());
        pstm.setObject(2, supplier.getSupplierName());
        pstm.setObject(3, supplier.getCompanyName());
        pstm.setObject(4, supplier.getAddress());
        pstm.setObject(5, supplier.getTel());

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Supplier supplier) throws SQLException {
        String sql = "UPDATE supplier SET supplierName = ?, companyName = ?, address = ?, tel = ? WHERE supplierId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, supplier.getSupplierName());
        pstm.setObject(2, supplier.getCompanyName());
        pstm.setObject(3, supplier.getAddress());
        pstm.setObject(4, supplier.getTel());
        pstm.setObject(5, supplier.getSupplierId());

        return pstm.executeUpdate() > 0;
    }

    public static boolean delete(String supplierId) throws SQLException {

        String sql = "DELETE FROM supplier WHERE supplierId = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, supplierId);

        return pstm.executeUpdate() > 0;
    }

    public static Supplier searchById(String supplierId) throws SQLException {
        String sql = "SELECT * FROM supplier WHERE supplierId = ?";
        Connection connection =DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, supplierId);

        ResultSet rs = pstm.executeQuery();
        if (rs.next()){
            String supId = rs.getString(1);
            String supName = rs.getString(2);
            String supCompanyName = rs.getString(3);
            String supAddress = rs.getString(4);
            String supTel = rs.getString(5);

            Supplier supplier = new Supplier(supId, supName, supCompanyName, supAddress, supTel);

            return supplier;
        }
        return null;
    }

    public static List<Supplier> getAll() throws SQLException {
        String sql = "SELECT * FROM supplier";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        List<Supplier> supList = new ArrayList<>();

        while (resultSet.next()){
            String supplierId = resultSet.getString(1);
            String supplierName = resultSet.getString(2);
            String companyName = resultSet.getString(3);
            String address = resultSet.getString(4);
            String tel = resultSet.getString(5);


            Supplier supplier = new Supplier(supplierId, supplierName, companyName, address, tel);
            supList.add(supplier);
        }
        return supList;
    }
}
