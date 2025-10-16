package modle.entity;


import java.sql.*;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "thogakade";
    private static final String FULL_URL = URL + DB_NAME;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234"; // Change to your MySQL password

    private static DBConnection instance;
    private Connection connection;

    private DBConnection() throws SQLException {
        initializeDatabase();
        this.connection = DriverManager.getConnection(FULL_URL, USERNAME, PASSWORD);
    }

    private void initializeDatabase() {
        try {
            // First connect without database to create it
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();

            // Create database if not exists
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            stmt.execute("USE " + DB_NAME);

            // Create tables
            createCustomerTable(stmt);
            createEmployeeTable(stmt);
            createItemTable(stmt);
            createSupplierTable(stmt);

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.err.println("Error initializing database: " + e.getMessage());
        }
    }

    private void createCustomerTable(Statement stmt) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS customer (" +
                "title VARCHAR(10), " +
                "cus_id VARCHAR(10) PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL, " +
                "address VARCHAR(255), " +
                "dob DATE, " +
                "salary DECIMAL(10,2), " +
                "city VARCHAR(50), " +
                "province VARCHAR(50), " +
                "postal_code VARCHAR(10), " +
                "created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
        stmt.executeUpdate(sql);
    }

    private void createEmployeeTable(Statement stmt) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS employee (" +
                "emp_id VARCHAR(10) PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL, " +
                "nic VARCHAR(15) UNIQUE, " +
                "dob DATE, " +
                "position VARCHAR(50), " +
                "salary DECIMAL(10,2), " +
                "contact_number VARCHAR(15), " +
                "address VARCHAR(255), " +
                "join_date DATE, " +
                "status VARCHAR(20) DEFAULT 'Active', " +
                "created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
        stmt.executeUpdate(sql);
    }

    private void createItemTable(Statement stmt) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS item (" +
                "item_code VARCHAR(10) PRIMARY KEY, " +
                "description VARCHAR(255) NOT NULL, " +
                "category VARCHAR(50), " +
                "qty_on_hand INT DEFAULT 0, " +
                "unit_price DECIMAL(10,2), " +
                "created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
        stmt.executeUpdate(sql);
    }

    private void createSupplierTable(Statement stmt) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS supplier (" +
                "supplier_id VARCHAR(10) PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL, " +
                "company VARCHAR(100), " +
                "address VARCHAR(255), " +
                "city VARCHAR(50), " +
                "province VARCHAR(50), " +
                "postal_code VARCHAR(10), " +
                "email VARCHAR(100), " +
                "contact VARCHAR(15), " +
                "created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
        stmt.executeUpdate(sql);
    }

    public static DBConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DBConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}