package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.User;
import main.UserHolder;
import main.Util;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeManageAccountController implements Initializable {

    User user = new User();

    @FXML
    private Label lblWelcomeUser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserHolder holder = UserHolder.getInstance();
        user = holder.getUser();
        String name = user.getFirstName();
        lblWelcomeUser.setText("Hello " +
                name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase());
    }

    public void personalDetails(ActionEvent event) throws IOException
    {
        Util.sceneSwitcher("../View/employeeManagePersonalDetails.fxml", Util.getStage(event));
    }

    public void passwordChange(ActionEvent event) throws IOException
    {
        Util.sceneSwitcher("../View/employeeManagePassword.fxml", Util.getStage(event));

    }

    public void secretDetails(ActionEvent event) throws IOException
    {
        Util.sceneSwitcher("../View/employeeManageSecretQA.fxml", Util.getStage(event));
    }


    public void previousPage(ActionEvent event) throws IOException
    {
        UserHolder holder = UserHolder.getInstance();
        user = holder.getUser();
        String userType = user.getRole();
        if(userType.equals("User"))
        {
            Util.sceneSwitcher("../View/employeeDashboard.fxml", Util.getStage(event));
        }
        else
        {
            Util.sceneSwitcher("../View/adminDashboard.fxml", Util.getStage(event));
        }
    }

}
