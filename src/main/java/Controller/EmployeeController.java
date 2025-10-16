package Controller;

import dao.EmployeeDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modle.DTO.EmployeeDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    private ObservableList<EmployeeDTO> employeeList = FXCollections.observableArrayList();
    private EmployeeDAO employeeDAO;

    @FXML
    private TableColumn<?, ?> address;
    @FXML
    private TableView<EmployeeDTO> custable;
    @FXML
    private TableColumn<?, ?> dob;
    @FXML
    private TableColumn<?, ?> emsId;
    @FXML
    private TableColumn<?, ?> joinDate;
    @FXML
    private TableColumn<?, ?> name;
    @FXML
    private TableColumn<?, ?> nic;
    @FXML
    private TableColumn<?, ?> number;
    @FXML
    private TableColumn<?, ?> position;
    @FXML
    private TableColumn<?, ?> salary;
    @FXML
    private TableColumn<?, ?> stats;
    @FXML
    private TextField txtaddress;
    @FXML
    private TextField txtdob;
    @FXML
    private TextField txtemsid;
    @FXML
    private TextField txtjoinDate;
    @FXML
    private TextField txtname;
    @FXML
    private TextField txtnic;
    @FXML
    private TextField txtnumber;
    @FXML
    private TextField txtposition;
    @FXML
    private TextField txtsalary;
    @FXML
    private TextField txtstats;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        employeeDAO = new EmployeeDAO();
        loadAllEmployees();

        initializeTableColumns();
        setupTableSelectionListener();
    }

    private void initializeTableColumns() {
        emsId.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("empID"));
        name.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("empName"));
        nic.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("empNic"));
        dob.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("empDob"));
        position.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("empPosition"));
        salary.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("empSalary"));
        number.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("empnumber"));
        address.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("empAddress"));
        joinDate.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("empJoinDate"));
        stats.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("empStatus"));
    }

    private void setupTableSelectionListener() {
        custable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtemsid.setText(newValue.getEmpID());
                txtname.setText(newValue.getEmpName());
                txtnic.setText(newValue.getEmpNic());
                txtdob.setText(newValue.getEmpDob());
                txtposition.setText(newValue.getEmpPosition());
                txtsalary.setText(String.valueOf(newValue.getEmpSalary()));
                txtnumber.setText(newValue.getEmpnumber());
                txtaddress.setText(newValue.getEmpAddress());
                txtjoinDate.setText(newValue.getEmpJoinDate());
                txtstats.setText(newValue.getEmpStatus());
            }
        });
    }

    private void loadAllEmployees() {
        try {
            List<EmployeeDTO> employees = employeeDAO.getAllEmployees();
            employeeList.clear();
            employeeList.addAll(employees);
            custable.setItems(employeeList);
        } catch (SQLException e) {
            showAlert("Database Error", "Failed to load employees: " + e.getMessage());
        }
    }

    @FXML
    void btnBack(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"))));
            Stage stage1 = (Stage) txtemsid.getScene().getWindow();
            stage1.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    @FXML
    void btnReload(ActionEvent event) {
        loadAllEmployees();
    }

    @FXML
    void btnadd(ActionEvent event) {
        try {
            EmployeeDTO employee = new EmployeeDTO(
                    txtemsid.getText(),
                    txtname.getText(),
                    txtnic.getText(),
                    txtdob.getText(),
                    txtposition.getText(),
                    Double.parseDouble(txtsalary.getText()),
                    txtnumber.getText(),
                    txtaddress.getText(),
                    txtjoinDate.getText(),
                    txtstats.getText()
            );

            if (employeeDAO.saveEmployee(employee)) {
                showAlert("Success", "Employee added successfully!");
                loadAllEmployees();
                btnclear(event);
            } else {
                showAlert("Error", "Failed to add employee!");
            }
        } catch (SQLException e) {
            showAlert("Database Error", "Failed to add employee: " + e.getMessage());
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter valid salary!");
        }
    }

    @FXML
    void btnclear(ActionEvent event) {
        txtemsid.clear();
        txtname.clear();
        txtnic.clear();
        txtdob.clear();
        txtposition.clear();
        txtsalary.clear();
        txtnumber.clear();
        txtaddress.clear();
        txtjoinDate.clear();
        txtstats.clear();
    }

    @FXML
    void btndelete(ActionEvent event) {
        try {
            if (employeeDAO.deleteEmployee(txtemsid.getText())) {
                showAlert("Success", "Employee deleted successfully!");
                loadAllEmployees();
                btnclear(event);
            } else {
                showAlert("Error", "Failed to delete employee!");
            }
        } catch (SQLException e) {
            showAlert("Database Error", "Failed to delete employee: " + e.getMessage());
        }
    }

    @FXML
    void btnupdate(ActionEvent event) {
        try {
            EmployeeDTO employee = new EmployeeDTO(
                    txtemsid.getText(),
                    txtname.getText(),
                    txtnic.getText(),
                    txtdob.getText(),
                    txtposition.getText(),
                    Double.parseDouble(txtsalary.getText()),
                    txtnumber.getText(),
                    txtaddress.getText(),
                    txtjoinDate.getText(),
                    txtstats.getText()
            );

            if (employeeDAO.updateEmployee(employee)) {
                showAlert("Success", "Employee updated successfully!");
                loadAllEmployees();
            } else {
                showAlert("Error", "Failed to update employee!");
            }
        } catch (SQLException e) {
            showAlert("Database Error", "Failed to update employee: " + e.getMessage());
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter valid salary!");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}