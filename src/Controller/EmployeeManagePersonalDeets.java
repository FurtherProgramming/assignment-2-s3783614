package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.Util;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeManagePersonalDeets implements Initializable {

    @FXML
    private Label lblStatus;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtSecondName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblStatus.setText("");
    }

    public void updatePersonalDetails(ActionEvent event) throws IOException
    {

        // if(txtFirstName.getText() == null || txtFirstName.getText().trim().isEmpty())
        // {
        //     if(txtSecondName.getText() == null || txtSecondName.getText().trim().isEmpty())
        //     {
        //         lblStatus.setText("nahhh g fill it");
        //     }
        //     else
        //     {
        //         lblStatus.setText("it works");
        //     }
        // }
        // else
        // {
        //     lblStatus.setText("ya yeet!");
        // }

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
