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
import javafx.stage.Stage;
import modle.DTO.ItemDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemController implements Initializable {

    ObservableList<ItemDTO> Item= FXCollections.observableArrayList(
            new ItemDTO("I001", "Red Rice 5kg", "Groceries", 40, 1200.00 ),
            new ItemDTO("I002", "Whole Wheat Bread", "Bakery", 30, 250.00 ),
            new ItemDTO("I003", "Fresh Milk 1L", "Dairy", 50, 150.00)


    );

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
        itemtable.setItems(Item);

    }

    @FXML
    void btnadd(ActionEvent event) {
        String itemCode = txtitemCode.getText();
        String description = txtdescription.getText();
        String category = txtcategory.getText();
        int qtyOnHand = Integer.parseInt(txtqtyOnHand.getText());
        double unitPrice = Double.parseDouble(txtunitPrice.getText());

        ItemDTO newItem = new ItemDTO(itemCode, description, category, qtyOnHand, unitPrice);
        Item.add(newItem);
        itemtable.setItems(Item);

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
        ItemDTO selectedItem = itemtable.getSelectionModel().getSelectedItem();
        Item.remove(selectedItem);
        itemtable.setItems(Item);

    }

    @FXML
    void btnupdate(ActionEvent event) {
        ItemDTO selectedItem = itemtable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            selectedItem.setItemCode(txtitemCode.getText());
            selectedItem.setDescription(txtdescription.getText());
            selectedItem.setCategory(txtcategory.getText());
            selectedItem.setQtyOnHand(Integer.parseInt(txtqtyOnHand.getText()));
            selectedItem.setUnitPrice(Double.parseDouble(txtunitPrice.getText()));
            itemtable.refresh();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemCode.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("itemCode"));
        description.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("description"));
        category.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("category"));
        qtyOnHand.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("qtyOnHand"));
        unitPrice.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("unitPrice"));


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
}
