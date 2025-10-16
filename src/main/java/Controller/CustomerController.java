package Controller;

import dao.CustomerDAO;
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
import modle.DTO.CustomerDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    private ObservableList<CustomerDTO> CusList = FXCollections.observableArrayList();
    private CustomerDAO customerDAO;

    @FXML
    private TableColumn<?, ?> adress;
    @FXML
    private TableColumn<?, ?> ctiy;
    @FXML
    private TableColumn<?, ?> cusId;
    @FXML
    private TableView<CustomerDTO> custable;
    @FXML
    private TableColumn<?, ?> dob;
    @FXML
    private TableColumn<?, ?> name;
    @FXML
    private TableColumn<?, ?> postcode;
    @FXML
    private TableColumn<?, ?> province;
    @FXML
    private TableColumn<?, ?> salary;
    @FXML
    private TableColumn<?, ?> title;
    @FXML
    private TextField txtadress;
    @FXML
    private TextField txtcity;
    @FXML
    private TextField txtcusid;
    @FXML
    private TextField txtdob;
    @FXML
    private TextField txtname;
    @FXML
    private TextField txtpostalCode;
    @FXML
    private TextField txtprovince;
    @FXML
    private TextField txtsalary;
    @FXML
    private TextField txttitle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customerDAO = new CustomerDAO();
        loadAllCustomers();

        initializeTableColumns();
        setupTableSelectionListener();
    }

    private void initializeTableColumns() {
        cusId.setCellValueFactory(new PropertyValueFactory<>("cusID"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        name.setCellValueFactory(new PropertyValueFactory<>("cusName"));
        adress.setCellValueFactory(new PropertyValueFactory<>("cusAddress"));
        dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        salary.setCellValueFactory(new PropertyValueFactory<>("cusSalary"));
        ctiy.setCellValueFactory(new PropertyValueFactory<>("city"));
        province.setCellValueFactory(new PropertyValueFactory<>("province"));
        postcode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
    }

    private void setupTableSelectionListener() {
        custable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtcusid.setText(newValue.getCusID());
                txttitle.setText(newValue.getTitle());
                txtname.setText(newValue.getCusName());
                txtadress.setText(newValue.getCusAddress());
                txtdob.setText(newValue.getDob());
                txtsalary.setText(newValue.getCusSalary());
                txtcity.setText(newValue.getCity());
                txtprovince.setText(newValue.getProvince());
                txtpostalCode.setText(newValue.getPostalCode());
            }
        });
    }

    private void loadAllCustomers() {
        try {
            List<CustomerDTO> customers = customerDAO.getAllCustomers();
            CusList.clear();
            CusList.addAll(customers);
            custable.setItems(CusList);
        } catch (SQLException e) {
            showAlert("Database Error", "Failed to load customers: " + e.getMessage());
        }
    }

    @FXML
    void btnBack(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"))));
            Stage stage1 = (Stage) txtcusid.getScene().getWindow();
            stage1.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    @FXML
    void btnReload(ActionEvent event) {
        loadAllCustomers();
    }

    @FXML
    void btnadd(ActionEvent event) {
        try {
            CustomerDTO customer = new CustomerDTO(
                    txttitle.getText(),
                    txtcusid.getText(),
                    txtname.getText(),
                    txtadress.getText(),
                    txtdob.getText(),
                    txtsalary.getText(),
                    txtcity.getText(),
                    txtprovince.getText(),
                    txtpostalCode.getText()
            );

            if (customerDAO.saveCustomer(customer)) {
                showAlert("Success", "Customer added successfully!");
                loadAllCustomers();
                btnclear(event);
            } else {
                showAlert("Error", "Failed to add customer!");
            }
        } catch (SQLException e) {
            showAlert("Database Error", "Failed to add customer: " + e.getMessage());
        }
    }

    @FXML
    void btnclear(ActionEvent event) {
        txtcusid.clear();
        txttitle.clear();
        txtname.clear();
        txtadress.clear();
        txtdob.clear();
        txtsalary.clear();
        txtcity.clear();
        txtprovince.clear();
        txtpostalCode.clear();
    }

    @FXML
    void btndelete(ActionEvent event) {
        if (customerDAO.deleteCustomer(txtcusid.getText())) {
            showAlert("Success", "Customer deleted successfully!");
            loadAllCustomers();
            btnclear(event);
        } else {
            showAlert("Error", "Failed to delete customer!");
        }
    }

    @FXML
    void btnupdate(ActionEvent event) {
        CustomerDTO customer = new CustomerDTO(
                txttitle.getText(),
                txtcusid.getText(),
                txtname.getText(),
                txtadress.getText(),
                txtdob.getText(),
                txtsalary.getText(),
                txtcity.getText(),
                txtprovince.getText(),
                txtpostalCode.getText()
        );

        if (customerDAO.updateCustomer(customer)) {
            showAlert("Success", "Customer updated successfully!");
            loadAllCustomers();
        } else {
            showAlert("Error", "Failed to update customer!");
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