package dao;

import modle.DTO.CustomerDTO;
import modle.entity.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public boolean saveCustomer(CustomerDTO customer) throws SQLException {
        String sql = "INSERT INTO customer (title, cus_id, name, address, dob, salary, city, province, postal_code) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, customer.getTitle());
            pstm.setString(2, customer.getCusID());
            pstm.setString(3, customer.getCusName());
            pstm.setString(4, customer.getCusAddress());
            pstm.setString(5, customer.getDob());
            pstm.setDouble(6, Double.parseDouble(customer.getCusSalary()));
            pstm.setString(7, customer.getCity());
            pstm.setString(8, customer.getProvince());
            pstm.setString(9, customer.getPostalCode());

            return pstm.executeUpdate() > 0;
        }
    }

    public List<CustomerDTO> getAllCustomers() throws SQLException {
        List<CustomerDTO> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer";

        try (Connection connection = DBConnection.getInstance().getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                CustomerDTO customer = new CustomerDTO(
                        rs.getString("title"),
                        rs.getString("cus_id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("dob"),
                        String.valueOf(rs.getDouble("salary")),
                        rs.getString("city"),
                        rs.getString("province"),
                        rs.getString("postal_code")
                );
                customers.add(customer);
            }
        }
        return customers;
    }

    public boolean deleteCustomer(String text) {
        String sql = "DELETE FROM customer WHERE cus_id = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, text);
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCustomer(CustomerDTO customer) {
        String sql = "UPDATE customer SET title=?, name=?, address=?, dob=?, salary=?, city=?, province=?, postal_code=? WHERE cus_id=?";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, customer.getTitle());
            pstm.setString(2, customer.getCusName());
            pstm.setString(3, customer.getCusAddress());
            pstm.setString(4, customer.getDob());
            pstm.setDouble(5, Double.parseDouble(customer.getCusSalary()));
            pstm.setString(6, customer.getCity());
            pstm.setString(7, customer.getProvince());
            pstm.setString(8, customer.getPostalCode());
            pstm.setString(9, customer.getCusID());

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Add other methods (update, delete, search) with similar try-with-resources
}