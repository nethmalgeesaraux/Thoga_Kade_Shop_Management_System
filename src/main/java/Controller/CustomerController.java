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
import modle.DTO.CustomerDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

        ObservableList<CustomerDTO> CusList= FXCollections.observableArrayList(
            new CustomerDTO("Mr.","C001.","Kasun","Galle","1999-12-12","25000.00","Galle","Southern","80000"),
            new CustomerDTO("Mr.","C002.","Kaveesha","Matara","2000-11-11","30000.00","Matara","Southern","81000"),
            new CustomerDTO("Mrs.","C003.","Nisansala","Colombo","1998-10-10","35000.00","Colombo","Western","82000")

    );

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
        custable.setItems(CusList);

    }

    @FXML
    void btnadd(ActionEvent event) {
        CustomerDTO customerDTO=new CustomerDTO(
                txtcusid.getText(),
                txttitle.getText(),
                txtname.getText(),
                txtadress.getText(),
                txtdob.getText(),
                txtsalary.getText(),
                txtcity.getText(),
                txtprovince.getText(),
                txtpostalCode.getText()
        );
        CusList.add(customerDTO);


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
        CustomerDTO selectedCustomer = custable.getSelectionModel().getSelectedItem();
        CusList.remove(selectedCustomer);

    }

    @FXML
    void btnupdate(ActionEvent event) {
        CustomerDTO selectedCustomer = custable.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            selectedCustomer.setCusID(txtcusid.getText());
            selectedCustomer.setTitle(txttitle.getText());
            selectedCustomer.setCusName(txtname.getText());
            selectedCustomer.setCusAddress(txtadress.getText());
            selectedCustomer.setDob(txtdob.getText());
            selectedCustomer.setCusSalary(txtsalary.getText());
            selectedCustomer.setCity(txtcity.getText());
            selectedCustomer.setProvince(txtprovince.getText());
            selectedCustomer.setPostalCode(txtpostalCode.getText());
            custable.refresh();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       cusId.setCellValueFactory(new PropertyValueFactory<>("cusID"));
       title.setCellValueFactory(new PropertyValueFactory<>("title"));
       name.setCellValueFactory(new PropertyValueFactory<>("cusName"));
       adress.setCellValueFactory(new  PropertyValueFactory<>("cusAddress"));
       dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
       salary.setCellValueFactory(new PropertyValueFactory<>("cusSalary"));
       ctiy.setCellValueFactory(new PropertyValueFactory<>("city"));
       province.setCellValueFactory(new PropertyValueFactory<>("province"));
       postcode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        System.out.println(CusList);


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
}
