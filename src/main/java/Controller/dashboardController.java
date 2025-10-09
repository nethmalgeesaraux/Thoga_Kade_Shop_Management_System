package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class dashboardController {
    
    public void onActionback(javafx.event.ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/login.fxml"))));
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    public void onActionCustomer(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Customer.fxml"))));
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    public void onActionItem(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Item.fxml"))));
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    public void onActionSupplier(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Supplier.fxml"))));
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    public void onActionEmployee(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Employee.fxml"))));
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }
}
