package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.User;
import main.UserHolder;
import main.Util;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ForgotPasswordP2Controller implements Initializable
{
    User user = new User();

    @FXML
    private Label lblSecretQ;

    @FXML
    private TextField txtAnswer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserHolder holder = UserHolder.getInstance();
        user = holder.getUser();
        String secretQuestion = user.getSecQuestion();

        lblSecretQ.setText(secretQuestion);

    }

    public void correctAnswer(ActionEvent event) throws IOException {
        UserHolder holder = UserHolder.getInstance();
        user = holder.getUser();

        String userInput = txtAnswer.getText();
        String storedAnswer = user.getSecAnswer();
        if(userInput.equals(""))
        {
            Util.alertError("Field Cannot Be Left Empty!");
        }
        else
        {
            if(userInput.equals(storedAnswer))
            {
                Util.sceneSwitcher("../View/forgotPasswordP3.fxml", Util.getStage(event));
            }
            else
            {
                Util.alertError("Incorrect Answer!");
            }
        }
    }

    public void previousPage(ActionEvent event) throws IOException {
        Util.sceneSwitcher("../View/loginPage.fxml", Util.getStage(event));

    }

}
