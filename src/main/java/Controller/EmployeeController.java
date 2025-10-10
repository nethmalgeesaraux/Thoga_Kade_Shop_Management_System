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
import modle.DTO.EmployeeDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {


    ObservableList<EmployeeDTO> Employee= FXCollections.observableArrayList(
            new EmployeeDTO("E001", "Sunil Perera", "832451230V", "1983-07-12", "Manager", 75000.0, "0712456789", "No.15Temple Road, Kalutara", "2018-05-10", "Active"),
            new EmployeeDTO("E002", "Kamal Silva", "912345678V", "1990-03-22", "Sales Executive", 55000.0, "0723456789", "No.22 Lake Road, Colombo", "2019-08-15", "Active"),
            new EmployeeDTO("E003", "Nimal Fernando", "823456789V", "1985-11-30", "Accountant", 60000.0, "0734567890", "No.5 River Road, Galle", "2020-01-20", "Inactive")

    );

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

    @FXML
    void btnBack(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"))));
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();

    }

    @FXML
    void btnReload(ActionEvent event) {
        custable.setItems(Employee);

    }

    @FXML
    void btnadd(ActionEvent event) {
        EmployeeDTO employeeDTO = new EmployeeDTO(
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
        Employee.add(employeeDTO);
        custable.setItems(Employee);

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
        EmployeeDTO selectedItem = custable.getSelectionModel().getSelectedItem();
        Employee.remove(selectedItem);
        custable.setItems(Employee);

    }

    @FXML
    void btnupdate(ActionEvent event) {
        EmployeeDTO selectedEmployee = custable.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            selectedEmployee.setEmpID(txtemsid.getText());
            selectedEmployee.setEmpName(txtname.getText());
            selectedEmployee.setEmpNic(txtnic.getText());
            selectedEmployee.setEmpDob(txtdob.getText());
            selectedEmployee.setEmpPosition(txtposition.getText());
            selectedEmployee.setEmpSalary(Double.parseDouble(txtsalary.getText()));
            selectedEmployee.setEmpnumber(txtnumber.getText());
            selectedEmployee.setEmpAddress(txtaddress.getText());
            selectedEmployee.setEmpJoinDate(txtjoinDate.getText());
            selectedEmployee.setEmpStatus(txtstats.getText());
            custable.refresh();
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
}
