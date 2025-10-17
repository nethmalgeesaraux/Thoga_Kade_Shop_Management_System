package dao;

import modle.DTO.SupplierDTO;
import modle.entity.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO {

    public boolean saveSupplier(SupplierDTO supplier) throws SQLException {
        String sql = "INSERT INTO supplier (supplier_id, name, company, address, city, province, postal_code, email, contact) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, supplier.getSupplierId());
            pstm.setString(2, supplier.getSupplierName());
            pstm.setString(3, supplier.getSupplierCompany());
            pstm.setString(4, supplier.getSupplierAddress());
            pstm.setString(5, supplier.getSupplierCity());
            pstm.setString(6, supplier.getSupplierProvince());
            pstm.setString(7, supplier.getSupplierPostalCode());
            pstm.setString(8, supplier.getSupplierEmail());
            pstm.setString(9, supplier.getSupplierContact());

            return pstm.executeUpdate() > 0;
        }
    }

    public List<SupplierDTO> getAllSuppliers() throws SQLException {
        List<SupplierDTO> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM supplier";

        try (Connection connection = DBConnection.getInstance().getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                SupplierDTO supplier = new SupplierDTO(
                        rs.getString("supplier_id"),
                        rs.getString("name"),
                        rs.getString("company"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("province"),
                        rs.getString("postal_code"),
                        rs.getString("email"),
                        rs.getString("contact")
                );
                suppliers.add(supplier);
            }
        }
        return suppliers;
    }

    public boolean updateSupplier(SupplierDTO supplier) throws SQLException {
        String sql = "UPDATE supplier SET name=?, company=?, address=?, city=?, province=?, postal_code=?, email=?, contact=? WHERE supplier_id=?";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, supplier.getSupplierName());
            pstm.setString(2, supplier.getSupplierCompany());
            pstm.setString(3, supplier.getSupplierAddress());
            pstm.setString(4, supplier.getSupplierCity());
            pstm.setString(5, supplier.getSupplierProvince());
            pstm.setString(6, supplier.getSupplierPostalCode());
            pstm.setString(7, supplier.getSupplierEmail());
            pstm.setString(8, supplier.getSupplierContact());
            pstm.setString(9, supplier.getSupplierId());

            return pstm.executeUpdate() > 0;
        }
    }

    public boolean deleteSupplier(String supplierId) throws SQLException {
        String sql = "DELETE FROM supplier WHERE supplier_id = ?";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, supplierId);
            return pstm.executeUpdate() > 0;
        }
    }

    public SupplierDTO searchSupplier(String supplierId) throws SQLException {
        String sql = "SELECT * FROM supplier WHERE supplier_id = ?";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, supplierId);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                return new SupplierDTO(
                        rs.getString("supplier_id"),
                        rs.getString("name"),
                        rs.getString("company"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("province"),
                        rs.getString("postal_code"),
                        rs.getString("email"),
                        rs.getString("contact")
                );
            }
            return null;
        }
    }

    public List<SupplierDTO> searchSuppliersByCity(String city) throws SQLException {
        List<SupplierDTO> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM supplier WHERE city = ?";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, city);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                SupplierDTO supplier = new SupplierDTO(
                        rs.getString("supplier_id"),
                        rs.getString("name"),
                        rs.getString("company"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("province"),
                        rs.getString("postal_code"),
                        rs.getString("email"),
                        rs.getString("contact")
                );
                suppliers.add(supplier);
            }
        }
        return suppliers;
    }
}