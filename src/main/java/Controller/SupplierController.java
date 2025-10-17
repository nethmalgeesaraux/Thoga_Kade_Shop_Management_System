package Controller;

import dao.SupplierDAO;
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
import modle.DTO.SupplierDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class SupplierController implements Initializable {

    private ObservableList<SupplierDTO> supplierList = FXCollections.observableArrayList();
    private SupplierDAO supplierDAO;

    @FXML
    private TableView<SupplierDTO> suptable;
    @FXML
    private TableColumn<?, ?> supplierAddress;
    @FXML
    private TableColumn<?, ?> supplierCity;
    @FXML
    private TableColumn<?, ?> supplierCompany;
    @FXML
    private TableColumn<?, ?> supplierContact;
    @FXML
    private TableColumn<?, ?> supplierEmail;
    @FXML
    private TableColumn<?, ?> supplierId;
    @FXML
    private TableColumn<?, ?> supplierName;
    @FXML
    private TableColumn<?, ?> supplierPostalCode;
    @FXML
    private TableColumn<?, ?> supplierProvince;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField txtCompany;
    @FXML
    private TextField txtContact;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPostalCode;
    @FXML
    private TextField txtProvince;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        supplierDAO = new SupplierDAO();
        loadAllSuppliers();

        initializeTableColumns();
        setupTableSelectionListener();
    }

    private void initializeTableColumns() {
        supplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        supplierName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        supplierCompany.setCellValueFactory(new PropertyValueFactory<>("supplierCompany"));
        supplierAddress.setCellValueFactory(new PropertyValueFactory<>("supplierAddress"));
        supplierCity.setCellValueFactory(new PropertyValueFactory<>("supplierCity"));
        supplierProvince.setCellValueFactory(new PropertyValueFactory<>("supplierProvince"));
        supplierPostalCode.setCellValueFactory(new PropertyValueFactory<>("supplierPostalCode"));
        supplierEmail.setCellValueFactory(new PropertyValueFactory<>("supplierEmail"));
        supplierContact.setCellValueFactory(new PropertyValueFactory<>("supplierContact"));
    }

    private void setupTableSelectionListener() {
        suptable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtId.setText(newValue.getSupplierId());
                txtName.setText(newValue.getSupplierName());
                txtCompany.setText(newValue.getSupplierCompany());
                txtAddress.setText(newValue.getSupplierAddress());
                txtCity.setText(newValue.getSupplierCity());
                txtProvince.setText(newValue.getSupplierProvince());
                txtPostalCode.setText(newValue.getSupplierPostalCode());
                txtEmail.setText(newValue.getSupplierEmail());
                txtContact.setText(newValue.getSupplierContact());
            }
        });
    }

    private void loadAllSuppliers() {
        try {
            List<SupplierDTO> suppliers = supplierDAO.getAllSuppliers();
            supplierList.clear();
            supplierList.addAll(suppliers);
            suptable.setItems(supplierList);
        } catch (SQLException e) {
            showAlert("Database Error", "Failed to load suppliers: " + e.getMessage());
        }
    }

    @FXML
    void btnBack(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"))));
            Stage stage1 = (Stage) txtId.getScene().getWindow();
            stage1.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    @FXML
    void btnReload(ActionEvent event) {
        loadAllSuppliers();
    }

    @FXML
    void btnadd(ActionEvent event) {
        try {
            // Validate required fields
            if (txtId.getText().isEmpty() || txtName.getText().isEmpty()) {
                showAlert("Input Error", "Supplier ID and Name are required!");
                return;
            }

            SupplierDTO supplier = new SupplierDTO(
                    txtId.getText(),
                    txtName.getText(),
                    txtCompany.getText(),
                    txtAddress.getText(),
                    txtCity.getText(),
                    txtProvince.getText(),
                    txtPostalCode.getText(),
                    txtEmail.getText(),
                    txtContact.getText()
            );

            if (supplierDAO.saveSupplier(supplier)) {
                showAlert("Success", "Supplier added successfully!");
                loadAllSuppliers();
                btnclear(event);
            } else {
                showAlert("Error", "Failed to add supplier!");
            }
        } catch (SQLException e) {
            showAlert("Database Error", "Failed to add supplier: " + e.getMessage());
        }
    }

    @FXML
    void btnclear(ActionEvent event) {
        txtId.clear();
        txtName.clear();
        txtCompany.clear();
        txtAddress.clear();
        txtCity.clear();
        txtProvince.clear();
        txtPostalCode.clear();
        txtEmail.clear();
        txtContact.clear();
    }

    @FXML
    void btndelete(ActionEvent event) {
        try {
            if (supplierDAO.deleteSupplier(txtId.getText())) {
                showAlert("Success", "Supplier deleted successfully!");
                loadAllSuppliers();
                btnclear(event);
            } else {
                showAlert("Error", "Failed to delete supplier!");
            }
        } catch (SQLException e) {
            showAlert("Database Error", "Failed to delete supplier: " + e.getMessage());
        }
    }

    @FXML
    void btnupdate(ActionEvent event) {
        try {
            // Validate required fields
            if (txtId.getText().isEmpty() || txtName.getText().isEmpty()) {
                showAlert("Input Error", "Supplier ID and Name are required!");
                return;
            }

            SupplierDTO supplier = new SupplierDTO(
                    txtId.getText(),
                    txtName.getText(),
                    txtCompany.getText(),
                    txtAddress.getText(),
                    txtCity.getText(),
                    txtProvince.getText(),
                    txtPostalCode.getText(),
                    txtEmail.getText(),
                    txtContact.getText()
            );

            if (supplierDAO.updateSupplier(supplier)) {
                showAlert("Success", "Supplier updated successfully!");
                loadAllSuppliers();
            } else {
                showAlert("Error", "Failed to update supplier!");
            }
        } catch (SQLException e) {
            showAlert("Database Error", "Failed to update supplier: " + e.getMessage());
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