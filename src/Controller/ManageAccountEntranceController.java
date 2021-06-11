package Controller;

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
import java.util.ResourceBundle;

public class ManageAccountEntranceController implements Initializable {

    User user = new User();

    @FXML
    private Label lblWelcomeUser;
    @FXML
    private Label lblSecQ;
    @FXML
    private TextField txtAnswer;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserHolder holder = UserHolder.getInstance();
        user = holder.getUser();

        String name = user.getFirstName();
        String secQ = user.getSecQuestion();

        lblSecQ.setText(secQ);
        lblWelcomeUser.setText(name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase() +
                                        " what's the answer to this?");
    }

    public void validation(ActionEvent event) throws IOException {
        String secAnswer = txtAnswer.getText();
        String userSecAnswer = user.getSecAnswer();

        if(secAnswer.equals(""))
        {
            Util.alertError("Field cannot be left empty");
        }
        else
        {
            if(secAnswer.equals(userSecAnswer))
            {
                Util.sceneSwitcher("../View/employeeManageAccount.fxml", Util.getStage(event));
            }
            else
            {
                Util.alertError("Incorrect Answer Try Again!");
            }
        }

    }

    public void previousPage(ActionEvent event) throws IOException
    {
        UserHolder holder = UserHolder.getInstance();
        user = holder.getUser();
        String userType = user.getRole();

        if(userType.equals("User")) {
            Util.sceneSwitcher("../View/employeeDashboard.fxml", Util.getStage(event));
        }
        else if(userType.equals("Admin"))
        {
            Util.sceneSwitcher("../View/adminDashboard.fxml", Util.getStage(event));
        }
    }
}
