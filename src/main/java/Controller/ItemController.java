package Controller;

import dao.ItemDAO;
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
import modle.DTO.ItemDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ItemController implements Initializable {

    private ObservableList<ItemDTO> itemList = FXCollections.observableArrayList();
    private ItemDAO itemDAO;

    @FXML
    private TableColumn<?, ?> category;
    @FXML
    private TableColumn<?, ?> description;
    @FXML
    private TableColumn<?, ?> itemCode;
    @FXML
    private TableView<ItemDTO> itemtable;
    @FXML
    private TableColumn<?, ?> qtyOnHand;
    @FXML
    private TextField txtcategory;
    @FXML
    private TextField txtdescription;
    @FXML
    private TextField txtitemCode;
    @FXML
    private TextField txtqtyOnHand;
    @FXML
    private TextField txtunitPrice;
    @FXML
    private TableColumn<?, ?> unitPrice;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemDAO = new ItemDAO();
        loadAllItems();

        initializeTableColumns();
        setupTableSelectionListener();
    }

    private void initializeTableColumns() {
        itemCode.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("itemCode"));
        description.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("description"));
        category.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("category"));
        qtyOnHand.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("qtyOnHand"));
        unitPrice.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("unitPrice"));
    }

    private void setupTableSelectionListener() {
        itemtable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtitemCode.setText(newValue.getItemCode());
                txtdescription.setText(newValue.getDescription());
                txtcategory.setText(newValue.getCategory());
                txtqtyOnHand.setText(String.valueOf(newValue.getQtyOnHand()));
                txtunitPrice.setText(String.valueOf(newValue.getUnitPrice()));
            }
        });
    }

    private void loadAllItems() {
        try {
            List<ItemDTO> items = itemDAO.getAllItems();
            itemList.clear();
            itemList.addAll(items);
            itemtable.setItems(itemList);
        } catch (SQLException e) {
            showAlert("Database Error", "Failed to load items: " + e.getMessage());
        }
    }

    @FXML
    void btnBack(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"))));
            Stage stage1 = (Stage) txtitemCode.getScene().getWindow();
            stage1.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    @FXML
    void btnReload(ActionEvent event) {
        loadAllItems();
    }

    @FXML
    void btnadd(ActionEvent event) {
        try {
            // Validate inputs
            if (txtitemCode.getText().isEmpty() || txtdescription.getText().isEmpty()) {
                showAlert("Input Error", "Item Code and Description are required!");
                return;
            }

            ItemDTO item = new ItemDTO(
                    txtitemCode.getText(),
                    txtdescription.getText(),
                    txtcategory.getText(),
                    Integer.parseInt(txtqtyOnHand.getText()),
                    Double.parseDouble(txtunitPrice.getText())
            );

            if (itemDAO.saveItem(item)) {
                showAlert("Success", "Item added successfully!");
                loadAllItems();
                btnclear(event);
            } else {
                showAlert("Error", "Failed to add item!");
            }
        } catch (SQLException e) {
            showAlert("Database Error", "Failed to add item: " + e.getMessage());
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter valid quantity and unit price!");
        }
    }

    @FXML
    void btnclear(ActionEvent event) {
        txtitemCode.clear();
        txtdescription.clear();
        txtcategory.clear();
        txtqtyOnHand.clear();
        txtunitPrice.clear();
    }

    @FXML
    void btndelete(ActionEvent event) {
        try {
            if (itemDAO.deleteItem(txtitemCode.getText())) {
                showAlert("Success", "Item deleted successfully!");
                loadAllItems();
                btnclear(event);
            } else {
                showAlert("Error", "Failed to delete item!");
            }
        } catch (SQLException e) {
            showAlert("Database Error", "Failed to delete item: " + e.getMessage());
        }
    }

    @FXML
    void btnupdate(ActionEvent event) {
        try {
            // Validate inputs
            if (txtitemCode.getText().isEmpty() || txtdescription.getText().isEmpty()) {
                showAlert("Input Error", "Item Code and Description are required!");
                return;
            }

            ItemDTO item = new ItemDTO(
                    txtitemCode.getText(),
                    txtdescription.getText(),
                    txtcategory.getText(),
                    Integer.parseInt(txtqtyOnHand.getText()),
                    Double.parseDouble(txtunitPrice.getText())
            );

            if (itemDAO.updateItem(item)) {
                showAlert("Success", "Item updated successfully!");
                loadAllItems();
            } else {
                showAlert("Error", "Failed to update item!");
            }
        } catch (SQLException e) {
            showAlert("Database Error", "Failed to update item: " + e.getMessage());
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter valid quantity and unit price!");
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