package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modle.DTO.SupplierDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SupplierController implements Initializable {

    ObservableList<SupplierDTO> Supplier= FXCollections.observableArrayList(
            new SupplierDTO("S001", "Fernando", "Agro Foods Pvt Ltd", "No.45 Main Street", "Matara", "Southern", "81000", "0712345678", "agrofoods@gmail.com"),
            new SupplierDTO("S002", "Perera", "Fresh Farm Supplies", "No.22 Lake Road", "Galle", "Southern", "80000", "0723456789", "texwyc@gmail.com"),
            new SupplierDTO("S003", "Silva", "Dairy Best Co.", "No.5 River Road", "Colombo", "Western", "82000", "0734567890", "cbhnbndn@gmail.com")


    );

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

    @FXML
    void btnBack(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"))));
            Stage satge1 = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            satge1.close();
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();

    }

    @FXML
    void btnReload(ActionEvent event) {
        suptable.setItems(Supplier);

    }

    @FXML
    void btnadd(ActionEvent event) {
        SupplierDTO s1=new SupplierDTO(
                txtId.getText(),txtName.getText(),txtCompany.getText(),txtAddress.getText(),txtCity.getText(),txtProvince.getText(),txtPostalCode.getText(),txtEmail.getText(),txtContact.getText()
        );
        Supplier.add(s1);
        suptable.setItems(Supplier);

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
        SupplierDTO selectedSupplier = suptable.getSelectionModel().getSelectedItem();
        Supplier.remove(selectedSupplier);
        suptable.setItems(Supplier);

    }

    @FXML
    void btnupdate(ActionEvent event) {
        SupplierDTO selectedSupplier = suptable.getSelectionModel().getSelectedItem();
        if (selectedSupplier != null) {
            selectedSupplier.setSupplierId(txtId.getText());
            selectedSupplier.setSupplierName(txtName.getText());
            selectedSupplier.setSupplierCompany(txtCompany.getText());
            selectedSupplier.setSupplierAddress(txtAddress.getText());
            selectedSupplier.setSupplierCity(txtCity.getText());
            selectedSupplier.setSupplierProvince(txtProvince.getText());
            selectedSupplier.setSupplierPostalCode(txtPostalCode.getText());
            selectedSupplier.setSupplierEmail(txtEmail.getText());
            selectedSupplier.setSupplierContact(txtContact.getText());
            suptable.refresh();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        supplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        supplierName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        supplierCompany.setCellValueFactory(new PropertyValueFactory<>("supplierCompany"));
        supplierAddress.setCellValueFactory(new PropertyValueFactory<>("supplierAddress"));
        supplierCity.setCellValueFactory(new PropertyValueFactory<>("supplierCity"));
        supplierProvince.setCellValueFactory(new PropertyValueFactory<>("supplierProvince"));
        supplierPostalCode.setCellValueFactory(new PropertyValueFactory<>("supplierPostalCode"));
        supplierEmail.setCellValueFactory(new PropertyValueFactory<>("supplierEmail"));
        supplierContact.setCellValueFactory(new PropertyValueFactory<>("supplierContact"));

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
}
