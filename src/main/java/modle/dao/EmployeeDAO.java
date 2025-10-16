package dao;



import modle.DTO.EmployeeDTO;
import modle.entity.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public boolean saveEmployee(EmployeeDTO employee) throws SQLException {
        String sql = "INSERT INTO employee (emp_id, name, nic, dob, position, salary, contact_number, address, join_date, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, employee.getEmpID());
            pstm.setString(2, employee.getEmpName());
            pstm.setString(3, employee.getEmpNic());
            pstm.setString(4, employee.getEmpDob());
            pstm.setString(5, employee.getEmpPosition());
            pstm.setDouble(6, employee.getEmpSalary());
            pstm.setString(7, employee.getEmpnumber());
            pstm.setString(8, employee.getEmpAddress());
            pstm.setString(9, employee.getEmpJoinDate());
            pstm.setString(10, employee.getEmpStatus());

            return pstm.executeUpdate() > 0;
        }
    }

    public List<EmployeeDTO> getAllEmployees() throws SQLException {
        List<EmployeeDTO> employees = new ArrayList<>();
        String sql = "SELECT * FROM employee";

        try (Connection connection = DBConnection.getInstance().getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                EmployeeDTO employee = new EmployeeDTO(
                        rs.getString("emp_id"),
                        rs.getString("name"),
                        rs.getString("nic"),
                        rs.getString("dob"),
                        rs.getString("position"),
                        rs.getDouble("salary"),
                        rs.getString("contact_number"),
                        rs.getString("address"),
                        rs.getString("join_date"),
                        rs.getString("status")
                );
                employees.add(employee);
            }
        }
        return employees;
    }

    public boolean updateEmployee(EmployeeDTO employee) throws SQLException {
        String sql = "UPDATE employee SET name=?, nic=?, dob=?, position=?, salary=?, contact_number=?, address=?, join_date=?, status=? WHERE emp_id=?";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, employee.getEmpName());
            pstm.setString(2, employee.getEmpNic());
            pstm.setString(3, employee.getEmpDob());
            pstm.setString(4, employee.getEmpPosition());
            pstm.setDouble(5, employee.getEmpSalary());
            pstm.setString(6, employee.getEmpnumber());
            pstm.setString(7, employee.getEmpAddress());
            pstm.setString(8, employee.getEmpJoinDate());
            pstm.setString(9, employee.getEmpStatus());
            pstm.setString(10, employee.getEmpID());

            return pstm.executeUpdate() > 0;
        }
    }

    public boolean deleteEmployee(String empId) throws SQLException {
        String sql = "DELETE FROM employee WHERE emp_id = ?";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, empId);
            return pstm.executeUpdate() > 0;
        }
    }

    public EmployeeDTO searchEmployee(String empId) throws SQLException {
        String sql = "SELECT * FROM employee WHERE emp_id = ?";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, empId);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                return new EmployeeDTO(
                        rs.getString("emp_id"),
                        rs.getString("name"),
                        rs.getString("nic"),
                        rs.getString("dob"),
                        rs.getString("position"),
                        rs.getDouble("salary"),
                        rs.getString("contact_number"),
                        rs.getString("address"),
                        rs.getString("join_date"),
                        rs.getString("status")
                );
            }
            return null;
        }
    }
}
