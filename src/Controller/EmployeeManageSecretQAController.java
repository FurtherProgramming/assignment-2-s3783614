package Controller;

import Model.UpdateDetailsModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.User;
import main.UserHolder;
import main.Util;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeManageSecretQAController implements Initializable {
    UpdateDetailsModel udModel = new UpdateDetailsModel();
    User user = new User();

    @FXML
    private TextField txtSecAnswer;
    @FXML
    private  TextField txtConfirmSecAnswer;
    @FXML
    private Label lblSecAnswerStatus;
    @FXML
    private Label lblSecQuest;
    @FXML
    private Label lblPreviousQuest;
    @FXML
    private ComboBox<String> cboxSecQuest;

    ObservableList<String> qList = FXCollections.observableArrayList("What was your first pet?",
            "What city did your parents meet?",
            "What was your first job?",
            "Whats your oldest child's name?",
            "What was your first movie you watched?",
            "What was childhood nickname?",
            "Where is your favourite place to eat food?");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserHolder holder = UserHolder.getInstance();
        user = holder.getUser();

        String secQuestion = user.getSecQuestion();
        lblPreviousQuest.setText("Your previous question was: " + secQuestion);

        cboxSecQuest.setItems(qList);

    }

    public void updateSecretQA(ActionEvent event) throws IOException
    {
        //Used to get user details
        UserHolder holder = UserHolder.getInstance();
        User user = new User();
        user = holder.getUser();
        String username = user.getUserName();

        String answer = txtSecAnswer.getText();
        String confirmAnswer = txtConfirmSecAnswer.getText();
        String newQuestion = cboxSecQuest.getValue();
        /*&& txtSecAnswer.getText() == null && txtConfirmSecAnswer.getText() == null*/
        if(cboxSecQuest.getValue() == null )
        {
            lblSecQuest.setText("Please pick a new secret question!");
            //TODO: Add alert Box
        }
        else if(txtSecAnswer.getText().equals("") || txtConfirmSecAnswer.getText().equals(""))
        {
            lblSecAnswerStatus.setText("Please pick a new secret answer!");
            //TODO: Add alert Box
        }
        else
        {
            if (answer.equals(confirmAnswer) && cboxSecQuest.getValue() != null) {
                try {
                    udModel.changeSecAnswer(confirmAnswer, username);
                    udModel.changeSecQuestion(newQuestion, username);
                    udModel.updateUser(username);
                    Util.sceneSwitcher("../View/employeeManageAccount.fxml", Util.getStage(event));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void previousPage(ActionEvent event) throws IOException
    {
        Util.sceneSwitcher("../View/employeeManageAccount.fxml", Util.getStage(event));
        // Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Parent root = FXMLLoader.load(getClass().getResource("../View/loginPage.fxml"));
        // Scene scene = new Scene(root);
        // stage.setScene(scene);
        // stage.show();
    }

}
