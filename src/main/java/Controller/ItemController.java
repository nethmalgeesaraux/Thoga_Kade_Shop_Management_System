package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import modle.DTO.CustomerDTO;
import modle.DTO.ItemDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemController implements Initializable {

    ObservableList<ItemDTO> Item= FXCollections.observableArrayList(
            new ItemDTO("I001", "Red Rice 5kg", "Groceries", 40, 1200.00 ),
            new ItemDTO("I002", "Whole Wheat Bread", "Bakery", 30, 250.00 ),
            new ItemDTO("I003", "Fresh Milk 1L", "Dairy", 50, 150.00)


    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
