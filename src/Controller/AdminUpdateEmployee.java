package Controller;

import Model.ManageEmpsModel;
import Model.RegistrationModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.User;
import main.UserHolder;
import main.Util;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminUpdateEmployee implements Initializable {

    ManageEmpsModel manageEmpsModel = new ManageEmpsModel();
    RegistrationModel registrationModel = new RegistrationModel();

    User user = new User();
    @FXML
    private ComboBox<String> cboxSecretQuestion;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtConfirmPassword;
    @FXML
    private TextField txtSecAnswer;
    @FXML
    private TextField txtConfirmSecPassword;
    @FXML
    private Button btnUpdate;

    ObservableList<String> qList = FXCollections.observableArrayList("What was your first pet?",
                                                                            "What city did your parents meet?",
                                                                            "What was your first job?",
                                                                            "Whats your oldest child's name?",
                                                                            "What was your first movie you watched?",
                                                                            "What was childhood nickname?",
                                                                            "Where is your favourite place to eat food?");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cboxSecretQuestion.setItems(qList);
        try
        {
            fillFields();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void fillFields() throws SQLException
    {
        int empId = UserHolder.getInstance().getEmpId();
        user = manageEmpsModel.fillUserDetails(empId);
        System.out.println(empId);
        txtFirstName.setText(user.getFirstName());
        txtLastName.setText(user.getLastName());
        txtUsername.setText(user.getUsername());
        txtPassword.setText(user.getPassword());
        txtConfirmPassword.setText(user.getPassword());
        cboxSecretQuestion.setValue(user.getSecQuestion());
        txtSecAnswer.setText(user.getSecAnswer());
        txtConfirmSecPassword.setText(user.getSecAnswer());
    }

    public void updateEmployee(ActionEvent event)
    {
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String secretQuestion = cboxSecretQuestion.getValue();
        String secretAnswer = txtSecAnswer.getText();
        int empId = UserHolder.getInstance().getEmpId();

        if(firstName.equals("") || lastName.equals("") || password.equals("") ||
                (cboxSecretQuestion.getValue() == null) || secretAnswer.equals(""))
        {

            Util.alertError("Fields Cannot Be Left Empty!");
            // System.out.println("No empty fields!");
        }
        else
        {
            try {
                //TODO: USERNAME CHECK
                //usernameExists() && username != current username
                if(registrationModel.usernameExists(username) && !username.equals(user.getUsername()))
                {
                    Util.alertError("Username is already taken!");
                }
                else {
                    if (password.equals(txtConfirmPassword.getText())) {
                        if (secretAnswer.equals(txtConfirmSecPassword.getText())) {
                            manageEmpsModel.editEmployee(empId, firstName, lastName, username,
                                    password, secretQuestion, secretAnswer);
                            Util.alertSuccessPopUp("Employee Successfully Updated!", btnUpdate);
                        } else {
                            Util.alertError("Secret answers does not match!");
                            // System.out.println("Secret answer does not match!");
                        }
                    } else {
                        Util.alertError("Passwords does not match!");
                        // System.out.println("Password does not match!");
                    }
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }


    }
}
