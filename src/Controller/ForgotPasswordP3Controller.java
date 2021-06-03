package Controller;

import Model.ForgotPasswordModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import main.User;
import main.UserHolder;
import main.Util;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ForgotPasswordP3Controller implements Initializable {

    User user = new User();
    ForgotPasswordModel fpmodel = new ForgotPasswordModel();

    @FXML
    private Label lblChecker;
    @FXML
    private Label lblWelcomeUser;
    @FXML
    private TextField txtNewPass;
    @FXML
    private TextField txtConfirmNewPass;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserHolder holder = UserHolder.getInstance();
        user = holder.getUser();
        String name = user.getFirstName();
        lblWelcomeUser.setText("Hello " +
                name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase());
        lblChecker.setText("");
    }

    public void changePassword(ActionEvent event) throws IOException {
        UserHolder holder = UserHolder.getInstance();
        user = holder.getUser();

        String newPassword = txtNewPass.getText();
        String confirmNewPassword = txtConfirmNewPass.getText();
        String username = user.getUsername();
        if(newPassword.equals("") || confirmNewPassword.equals(""))
        {
            Util.alertError("Fields Cannot Be Left Empty!");
        }
        else
        {
            if(newPassword.equals(confirmNewPassword))
            {
                try {
                    fpmodel.changePassword(newPassword,username);
                    Util.alertSuccess("Password Successfully Changed!");
                    Util.sceneSwitcher("../View/loginPage.fxml",Util.getStage(event));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                // lblChecker.setTextFill(Color.RED);
                // lblChecker.setText("Passwords Do Not Match!");
                Util.alertError("Passwords Do Not Match!");
            }
        }
    }

    public void previousPage(ActionEvent event) throws IOException {
        Util.sceneSwitcher("../View/loginPage.fxml", Util.getStage(event));
    }

}
