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
    ForgotPasswordModel forgotPasswordModel = new ForgotPasswordModel();

    @FXML
    private TextField txtUsername;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    public void userNameValidation(ActionEvent event) throws IOException {
        String username = txtUsername.getText();

        if(username.equals(""))
        {
            Util.alertError("Field Cannot Be Left Empty!");
        }
        else
        {
            if(forgotPasswordModel.usernameExists(username))
            {
                Util.sceneSwitcher("../View/forgotPasswordP2.fxml", Util.getStage(event));
            }
            else
            {
                Util.alertError("This username does not exist!");
            }

        }
    }

    public void previousPage(ActionEvent event) throws IOException {
        Util.sceneSwitcher("../View/loginPage.fxml", Util.getStage(event));
    }

}
