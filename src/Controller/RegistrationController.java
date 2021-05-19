package Controller;

import Model.RegistrationModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable
{
    RegistrationModel registrationModel = new RegistrationModel();

    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtSecondName;
    @FXML
    private TextField txtRegoUsername;
    @FXML
    private TextField txtRegoPassword;
    @FXML
    private TextField txtRegoConfirmPassword;
    @FXML
    private TextField txtSecretAnswer;
    @FXML
    private TextField txtConfirmSecretAnswer;
    @FXML
    private Label lblStatus;
    @FXML
    private Label lblPasswordStatus;
    @FXML
    private Label lblSecretPassStatus;
    @FXML
    private ComboBox<String> cboSecretQuestion;

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


        try {
            if(password.equals(confirmPassword))
            {
                if(secretAnswer.equals(confirmSecretAnswer))
                {
                    if (!registrationModel.usernameExists(username))
                    {

                        System.out.println(!registrationModel.usernameExists(username));
                        if (registrationModel.isRegistered(firstName, secondName, username,
                                password, secretQuestion, secretAnswer))
                        {
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            Parent root = FXMLLoader.load(getClass().getResource("../View/loginPage.fxml"));
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                        }
                        else
                        {
                            System.out.println("Ur cooked");
                            System.exit(1);
                        }
                    }
                    else
                    {
                        lblStatus.setTextFill(Color.RED);
                        lblStatus.setText("This username has already been taken, try again!");
                    }
                }
                else
                {
                    lblSecretPassStatus.setTextFill(Color.RED);
                    lblSecretPassStatus.setText("Passwords do not match!, try again!");
                }
            }
            else
            {
                lblPasswordStatus.setTextFill(Color.RED);
                lblPasswordStatus.setText("Passwords do not match!, try again!");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void previousPage(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../View/loginPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}
