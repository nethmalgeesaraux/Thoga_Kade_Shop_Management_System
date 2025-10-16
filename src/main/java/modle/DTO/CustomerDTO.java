package modle.DTO;

import lombok.*;
import modle.DTO.CustomerDTO;
import modle.entity.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class CustomerDTO {
    private String cusID;
    private String title;
    private String cusName;
    private String cusAddress;
    private String dob;
    private String cusSalary;
    private String city;
    private String province;
    private String postalCode;

    public CustomerDTO(String title, String cusID, String cusName, String cusAddress, String dob, String cusSalary, String city, String province, String postalCode) {
        this.title = title;
        this.cusID = cusID;
        this.cusName = cusName;
        this.cusAddress = cusAddress;
        this.dob = dob;
        this.cusSalary = cusSalary;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
    }

    public String getCusID() {
        return cusID;
    }

    public String getTitle() {
        return title;
    }

    public String getCusName() {
        return cusName;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public String getDob() {
        return dob;
    }

    public String getCusSalary() {
        return cusSalary;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setCusID(String cusID) {
        this.cusID = cusID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setCusSalary(String cusSalary) {
        this.cusSalary = cusSalary;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "cusID='" + cusID + '\'' +
                ", title='" + title + '\'' +
                ", cusName='" + cusName + '\'' +
                ", cusAddress='" + cusAddress + '\'' +
                ", dob='" + dob + '\'' +
                ", cusSalary='" + cusSalary + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
    public boolean saveCustomer(CustomerDTO customer) throws SQLException {
        String sql = "INSERT INTO customer (title, cus_id, name, address, dob, salary, city, province, postal_code) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, customer.getTitle());
        pstm.setString(2, customer.getCusID());
        pstm.setString(3, customer.getCusName());
        pstm.setString(4, customer.getCusAddress());
        pstm.setString(5, customer.getDob());
        pstm.setString(6, customer.getCusSalary());
        pstm.setString(7, customer.getCity());
        pstm.setString(8, customer.getProvince());
        pstm.setString(9, customer.getPostalCode());

        return pstm.executeUpdate() > 0;
    }

    public boolean updateCustomer(CustomerDTO customer) throws SQLException {
        String sql = "UPDATE customer SET title=?, name=?, address=?, dob=?, salary=?, city=?, province=?, postal_code=? WHERE cus_id=?";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, customer.getTitle());
        pstm.setString(2, customer.getCusName());
        pstm.setString(3, customer.getCusAddress());
        pstm.setString(4, customer.getDob());
        pstm.setString(5, customer.getCusSalary());
        pstm.setString(6, customer.getCity());
        pstm.setString(7, customer.getProvince());
        pstm.setString(8, customer.getPostalCode());
        pstm.setString(9, customer.getCusID());

        return pstm.executeUpdate() > 0;
    }

    public boolean deleteCustomer(String customerId) throws SQLException {
        String sql = "DELETE FROM customer WHERE cus_id = ?";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, customerId);

        return pstm.executeUpdate() > 0;
    }

    public List<CustomerDTO> getAllCustomers() throws SQLException {
        List<CustomerDTO> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer";

        Connection connection = DBConnection.getInstance().getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            CustomerDTO customer = new CustomerDTO(
                    rs.getString("title"),
                    rs.getString("cus_id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("dob"),
                    rs.getString("salary"),
                    rs.getString("city"),
                    rs.getString("province"),
                    rs.getString("postal_code")
            );
            customers.add(customer);
        }
        return customers;
    }

    public CustomerDTO searchCustomer(String customerId) throws SQLException {
        String sql = "SELECT * FROM customer WHERE cus_id = ?";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, customerId);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            return new CustomerDTO(
                    rs.getString("title"),
                    rs.getString("cus_id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("dob"),
                    rs.getString("salary"),
                    rs.getString("city"),
                    rs.getString("province"),
                    rs.getString("postal_code")
            );
        }
        return null;
    }

}
