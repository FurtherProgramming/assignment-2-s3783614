package Controller;

import Model.ForgotPasswordModel;
import Model.LoginModel;
import Model.RegistrationModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Util;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ForgotPasswordController implements Initializable
{
    // LoginModel loginModel = new LoginModel();
    // RegistrationModel registrationModel = new RegistrationModel();
    ForgotPasswordModel forgotPasswordModel = new ForgotPasswordModel();

    @FXML
    private TextField txtUsername;
    @FXML
    private Label lblUsernameStatus;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        lblUsernameStatus.setText("");
    }


    public void userNameValidation(ActionEvent event) throws IOException {
        String username = txtUsername.getText();
        if(forgotPasswordModel.usernameExists(username))
        {
            // Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Parent root = FXMLLoader.load(getClass().getResource("../View/loginPage.fxml"));
            // Scene scene = new Scene(root);
            // stage.setScene(scene);
            // stage.show();
            // loginModel.retrieveInfo(username);
            // Util.sceneSwitcher("../View/forgotPasswordP2.fxml", Util.getStage(event));


            // Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Parent root = FXMLLoader.load(getClass().getResource("../View/forgotPasswordP2.fxml"));
            // Scene scene = new Scene(root);
            // stage.setScene(scene);
            // stage.show();

            Util.sceneSwitcher("../View/forgotPasswordP2.fxml", Util.getStage(event));

        }
        else
        {
            lblUsernameStatus.setText("This username does not exist!");
            lblUsernameStatus.setTextFill(Color.RED);
        }
    }

    public void previousPage(ActionEvent event) throws IOException {
        // Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Parent root = FXMLLoader.load(getClass().getResource("../View/loginPage.fxml"));
        // Scene scene = new Scene(root);
        // stage.setScene(scene);
        // stage.show();

        Util.sceneSwitcher("../View/loginPage.fxml", Util.getStage(event));
    }


    //TODO: Create and follow singleton
}
