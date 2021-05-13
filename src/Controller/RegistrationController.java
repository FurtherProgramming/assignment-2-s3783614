package Controller;

import Model.RegistrationModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable
{
    RegistrationModel registrationModel = new RegistrationModel();

    @FXML
    private Label isConnected;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtSecondName;
    @FXML
    private TextField txtRegoUsername;
    @FXML
    private TextField txtRegoPassword;
    @FXML
    private TextField txtSecretAnswer;




    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // isConnected.setText("Registration Time!");
    }


    @FXML
    public void userRegistration(ActionEvent event) {
        String firstName = txtFirstName.getText();
        String secondName = txtSecondName.getText();
        String username = txtRegoUsername.getText();
        String password = txtRegoPassword.getText();
        String secretAnswer = txtSecretAnswer.getText();
        int secretQuestion = 2;


        try {

            if(registrationModel.isRegistered(firstName, secondName, username,
                    password, secretQuestion, secretAnswer))
            {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("../View/employeeDashboard.fxml"));
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
