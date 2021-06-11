package Controller;

import Model.RegistrationModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.Util;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable
{
    RegistrationModel registrationModel = new RegistrationModel();

    @FXML private TextField txtFirstName;
    @FXML private TextField txtSecondName;
    @FXML private TextField txtRegoUsername;
    @FXML private TextField txtRegoPassword;
    @FXML private TextField txtRegoConfirmPassword;
    @FXML private TextField txtSecretAnswer;
    @FXML private TextField txtConfirmSecretAnswer;
    @FXML private ComboBox<String> cboSecretQuestion;

    ObservableList<String> qList = FXCollections.observableArrayList("What was your first pet?",
                                                                            "What city did your parents meet?",
                                                                            "What was your first job?",
                                                                            "Whats your oldest child's name?",
                                                                            "What was your first movie you watched?",
                                                                            "What was childhood nickname?",
                                                                            "Where is your favourite place to eat food?");

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        cboSecretQuestion.setItems(qList);
    }


    @FXML
    public void userRegistration(ActionEvent event) {
        String firstName = txtFirstName.getText();
        String secondName = txtSecondName.getText();
        String username = txtRegoUsername.getText();
        String password = txtRegoPassword.getText();
        String confirmPassword = txtRegoConfirmPassword.getText();
        String secretAnswer = txtSecretAnswer.getText();
        String confirmSecretAnswer = txtConfirmSecretAnswer.getText();
        String secretQuestion = cboSecretQuestion.getValue();
        if(firstName.equals("") || secondName.equals("") || username.equals("") || password.equals("") || confirmPassword.equals("") ||
                                    secretAnswer.equals("") || confirmSecretAnswer.equals("") ||cboSecretQuestion.getValue() == null)
        {
            Util.alertError("Fields Cannot Be Left Empty!");
        }
        else
        {

            try
            {
                if(password.equals(confirmPassword))
                {
                    if(secretAnswer.equals(confirmSecretAnswer))
                    {
                        if (!registrationModel.usernameExists(username))
                        {

                            if (registrationModel.isRegistered(firstName, secondName, username,
                                    password, secretQuestion, secretAnswer))
                            {
                                Util.sceneSwitcher("../View/loginPage.fxml", Util.getStage(event));
                            }
                        }
                        else
                        {
                            Util.alertError("This username has already been taken, try again!");
                        }
                    }
                    else
                    {
                        Util.alertError("Secret Passwords do not match, try again!");
                    }
                }
                else
                {
                    Util.alertError("Passwords do not match, try again!");
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void previousPage(ActionEvent event) throws IOException
    {
        Util.sceneSwitcher("../View/loginPage.fxml", Util.getStage(event));
    }
}
