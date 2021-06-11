package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.User;
import main.UserHolder;
import main.Util;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable
{
    User user = new User();

    @FXML private Label lblWelcome;
    @FXML private Button btnLockdown;
    @FXML private Button btnVisual;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserHolder holder = UserHolder.getInstance();
        user = holder.getUser();
        String name = user.getFirstName();

        lblWelcome.setText("Hello " +
                name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase());
    }

    public void previousPage(ActionEvent event) throws IOException {
        Util.sceneSwitcher("../View/loginPage.fxml", Util.getStage(event));
    }

    public void lockdownConditions() throws IOException {
        Util.popButtonUpWindow("../View/adminLockdownCondition.fxml", btnLockdown);

    }
    //Switches scenes to manage account
    public void manageAdminAccount(ActionEvent event) throws IOException{
        Util.sceneSwitcher("../View/secretQuestionPage.fxml", Util.getStage(event));
    }
    //switches scenes to manage employee
    public void manageEmployee(ActionEvent event) throws IOException{
        Util.sceneSwitcher("../View/adminManageEmployees.fxml", Util.getStage(event));
    }
    //switches scenes to manage booking
    public void manageBooking(ActionEvent event) throws IOException
    {
        Util.sceneSwitcher("../View/adminManageBooking.fxml", Util.getStage(event));
    }

    public void generateCSV() throws IOException
    {
        Util.popButtonUpWindow("../View/adminGenerateCSV.fxml", btnLockdown);
    }

    public void graphicVisual() throws IOException
    {
        Util.popButtonUpWindow("../View/adminGraphicalVisual.fxml", btnVisual);
    }
}
