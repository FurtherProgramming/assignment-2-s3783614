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

public class ForgotPasswordP2Controller implements Initializable
{
    User user = new User();

    @FXML
    private Label lblSecretQ;
    @FXML
    private Label lblStatus;
    @FXML
    private TextField txtAnswer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserHolder holder = UserHolder.getInstance();
        user = holder.getUser();
        String secretQuestion = user.getSecQuestion();

        System.out.println(secretQuestion);
        System.out.println(user.getSecAnswer());

        lblSecretQ.setText(secretQuestion);
        lblStatus.setText("");
    }

    public void correctAnswer(ActionEvent event) throws IOException {
        UserHolder holder = UserHolder.getInstance();
        user = holder.getUser();

        String userInput = txtAnswer.getText();
        String storedAnswer = user.getSecAnswer();
        if(userInput.equals(storedAnswer))
        {
            Util.sceneSwitcher("../View/forgotPasswordP3.fxml", Util.getStage(event));
        }
        else
        {
            lblStatus.setTextFill(Color.RED);
            lblStatus.setText("Incorrect Answer!");
        }
    }

    public void previousPage(ActionEvent event) throws IOException {
        // Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Parent root = FXMLLoader.load(getClass().getResource("../View/loginPage.fxml"));
        // Scene scene = new Scene(root);
        // stage.setScene(scene);
        // stage.show();
        //GO BACK HOME ALWAYS
        Util.sceneSwitcher("../View/loginPage.fxml", Util.getStage(event));

    }

}
