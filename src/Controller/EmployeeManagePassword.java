package Controller;

import Model.UpdateDetailsModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import main.User;
import main.UserHolder;
import main.Util;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeManagePassword implements Initializable {

    UpdateDetailsModel udModel = new UpdateDetailsModel();

    @FXML
    private TextField txtNewPass;
    @FXML
    private TextField txtConfirmNewPass;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void updatePassword(ActionEvent event) throws IOException
    {
        UserHolder holder = UserHolder.getInstance();
        User user = new User();
        user = holder.getUser();

        String newPass = txtNewPass.getText();
        String confirmNewPass = txtConfirmNewPass.getText();
        String userName = user.getUsername();

        if(newPass.equals("") || confirmNewPass.equals(""))
        {
            Util.alertError("Fields Cannot Be Left Empty!");
        }
        else
        {
            if(newPass.equals(confirmNewPass))
            {
                try
                {
                    //calls method to delete change password
                    udModel.changePassword(newPass,userName);
                    udModel.updateUser(userName);
                    Util.alertSuccess("Password Updated Successfully!");
                    Util.sceneSwitcher("../View/employeeManageAccount.fxml", Util.getStage(event));

                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                Util.alertError("Passwords, do not match!");
            }
        }

    }

    public void previousPage(ActionEvent event) throws IOException
    {
        Util.sceneSwitcher("../View/employeeManageAccount.fxml", Util.getStage(event));
    }
}
