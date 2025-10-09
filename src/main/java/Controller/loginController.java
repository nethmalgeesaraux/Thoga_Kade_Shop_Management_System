package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class loginController {

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    void OnAction(ActionEvent event) {

        final String correctEmail = "nethmal@gmail.com";
        final String correctPassword = "nethmal2005";

        String enteredEmail = usernameField.getText();
        String enteredPassword = passwordField.getText();

        if (enteredEmail.isEmpty() || enteredPassword.isEmpty()) {
            new Alert(Alert.AlertType.ERROR,"Please enter both email and password").show();
        }

        if (enteredEmail.equals(correctEmail) && enteredPassword.equals(correctPassword)) {


            Stage stage = new Stage();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"))));
            } catch (
                    IOException e) {
                throw new RuntimeException(e);
            }
            stage.show();
            new Alert(Alert.AlertType.INFORMATION, "Login successful!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid email or password.").show();
        }

    }


}


