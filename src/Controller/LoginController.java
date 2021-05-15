package Controller;

import Model.LoginModel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public LoginModel loginModel = new LoginModel();
    @FXML
    private Label isConnected;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    // @FXML
    // private Hyperlink



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
    public void Login(ActionEvent event){

        // try {
        //     if (loginModel.isLogin(txtUsername.getText(),txtPassword.getText())){
        //
        //         isConnected.setText("Logged in successfully");
        //     }else{
        //         isConnected.setText("username and password is incorrect");
        //     }
        // } catch (SQLException e) {
        //     e.printStackTrace();
        // }

        Parent root = null;
        try
        {
            if (loginModel.isLogin(txtUsername.getText(),txtPassword.getText())){
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("../View/employeeDashboard.fxml"));
                Scene menuPageScene = new Scene(root);
                stage.setScene(menuPageScene);
                stage.show();
            }
            else
            {
                isConnected.setText("username and password is incorrect");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void registration(ActionEvent event)
    {

        try {

                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/registration.fxml"));
                Parent root = loader.load();
                // Parent root = FXMLLoader.load(getClass().getResource("../View/registration.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

        } catch ( IOException e) {
            e.printStackTrace();
        }

    }

    public void forgotPassword(ActionEvent event) {
        Parent root = null;
        try {

                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("../View/forgotPassword.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //11.2.3 big sur



}
