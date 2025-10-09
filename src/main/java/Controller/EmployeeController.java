package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import modle.DTO.CustomerDTO;
import modle.DTO.EmployeeDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    ObservableList<EmployeeDTO> Employee= FXCollections.observableArrayList(
            new EmployeeDTO("E001", "Sunil Perera", "832451230V", "1983-07-12", "Manager", 75000.0, "0712456789", "No.15Temple Road, Kalutara", "2018-05-10", "Active"),
            new EmployeeDTO("E002", "Kamal Silva", "912345678V", "1990-03-22", "Sales Executive", 55000.0, "0723456789", "No.22 Lake Road, Colombo", "2019-08-15", "Active"),
            new EmployeeDTO("E003", "Nimal Fernando", "823456789V", "1985-11-30", "Accountant", 60000.0, "0734567890", "No.5 River Road, Galle", "2020-01-20", "Inactive")



    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
