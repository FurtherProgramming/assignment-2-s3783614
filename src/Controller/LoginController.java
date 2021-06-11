package Controller;

import Model.LoginModel;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import main.User;
import main.UserHolder;
import main.Util;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    User user = new User();
    AdminManageBookingController ambc = new AdminManageBookingController();
    LoginModel loginModel = new LoginModel();

    @FXML private TextField txtUsername;
    @FXML private TextField txtPassword;

    // Check database connection
    @Override
    public void initialize(URL location, ResourceBundle resources){

    }

    /* login Action method
       check if user input is the same as database.
     */
    public void Login(ActionEvent event)
    {

        String username = txtUsername.getText();
        String password = txtPassword.getText();
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
                    //To delete all bookings
                    ambc.deleteOldBookings();
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
                    //To delete all old bookings
                    ambc.deleteOldBookings();
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

}
