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

public class ManageAccountEntrance implements Initializable {

    User user = new User();

    @FXML
    private Label lblWelcomeUser;
    @FXML
    private Label lblSecQ;
    @FXML
    private TextField txtAnswer;
    @FXML
    private Label lblStatus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserHolder holder = UserHolder.getInstance();
        user = holder.getUser();

        String name = user.getfName();
        String secQ = user.getSecQuestion();

        lblStatus.setText("");
        lblSecQ.setText(secQ);
        lblWelcomeUser.setText(name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase() +
                                        " what's the answer to this?");
        //TODO:DELETE LATER
        String secAnswer = user.getSecAnswer();
        System.out.println(secAnswer + " : Secret answer");
    }

    public void validation(ActionEvent event) throws IOException {
        String secAnswer = txtAnswer.getText();
        String userSecAnswer = user.getSecAnswer();
        System.out.println("validating and printing the users answer: " + userSecAnswer);

        if(secAnswer.equals(userSecAnswer))
        {
            Util.sceneSwitcher("../View/employeeManageAccount.fxml", Util.getStage(event));
        }
        else
        {
            lblStatus.setTextFill(Color.RED);
            lblStatus.setText("Incorrect Answer Try Again!");
        }
    }

    public void previousPage(ActionEvent event) throws IOException
    {
        Util.sceneSwitcher("../View/employeeDashboard.fxml", Util.getStage(event));
        // Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Parent root = FXMLLoader.load(getClass().getResource("../View/loginPage.fxml"));
        // Scene scene = new Scene(root);
        // stage.setScene(scene);
        // stage.show();
    }
}
