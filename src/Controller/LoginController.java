package Controller;

import Model.LoginModel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.User;
import main.UserHolder;
import main.Util;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    User user = new User();

    public LoginModel loginModel = new LoginModel();
    @FXML
    private Label isConnected;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private Button btnLogIn;



    private Stage stage;
    private Scene scene;
    private Parent root;

    // Check database connection
    @Override
    public void initialize(URL location, ResourceBundle resources){
        if (loginModel.isDbConnected())
        {
            isConnected.setText("Connected");
        }
        else
        {
            isConnected.setText("Not Connected");
        }

    }
    /* login Action method
       check if user input is the same as database.
     */
    public void Login(ActionEvent event)
    {

        String username = txtUsername.getText();
        String password = txtPassword.getText();
        Parent root = null;
        try
        {
            if (loginModel.isLogin(username, password, "User"))
            {
                user = UserHolder.getInstance().getUser();

                if(user.getStatus().equals("Inactive"))
                {
                    Util.alertError("Inactive Account!");
                }
                else
                {
                    Util.sceneSwitcher("../View/employeeDashboard.fxml", Util.getStage(event));
                }
            }
            else if(loginModel.isLogin(username, password, "Admin"))
            {
                user = UserHolder.getInstance().getUser();
                if(user.getStatus().equals("Inactive"))
                {

                    Util.alertError("Inactive Account!");
                }
                else
                {
                    Util.sceneSwitcher("../View/AdminDashboard.fxml", Util.getStage(event));
                }
            }
            else
            {
                Util.alertError("Incorrect Username and Password!");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void registration(ActionEvent event)
    {
        try
        {
            Util.sceneSwitcher("../View/registration.fxml", Util.getStage(event));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void forgotPassword(ActionEvent event)
    {
        try {
            Util.sceneSwitcher("../View/forgotPassword.fxml", Util.getStage(event));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //11.2.3 big sur



}
