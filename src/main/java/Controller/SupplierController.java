package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import modle.DTO.CustomerDTO;
import modle.DTO.SupplierDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class SupplierController implements Initializable {

    ObservableList<SupplierDTO> Supplier= FXCollections.observableArrayList(
            new SupplierDTO("S001", "Fernando", "Agro Foods Pvt Ltd", "No.45 Main Street", "Matara", "Southern", "81000", "0712345678", "agrofoods@gmail.com"),
            new SupplierDTO("S002", "Perera", "Fresh Farm Supplies", "No.22 Lake Road", "Galle", "Southern", "80000", "0723456789", "texwyc@gmail.com"),
            new SupplierDTO("S003", "Silva", "Dairy Best Co.", "No.5 River Road", "Colombo", "Western", "82000", "0734567890", "cbhnbndn@gmail.com")


    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
