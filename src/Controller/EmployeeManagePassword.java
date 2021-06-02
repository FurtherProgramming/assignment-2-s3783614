package Controller;

import Model.UpdateDetailsModel;
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
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeManagePassword implements Initializable {

    UpdateDetailsModel udModel = new UpdateDetailsModel();

    @FXML
    private Label lblStatus;
    @FXML
    private TextField txtNewPass;
    @FXML
    private TextField txtConfirmNewPass;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblStatus.setText("");
    }

    public void updatePassword(ActionEvent event) throws IOException
    {
        UserHolder holder = UserHolder.getInstance();
        User user = new User();
        user = holder.getUser();

        String newPass = txtNewPass.getText();
        String confirmNewPass = txtConfirmNewPass.getText();
        String userName = user.getUsername();

        if(newPass.equals("") || confirmNewPass.equals(""))
        {
            //TODO: Alert Box
            lblStatus.setTextFill(Color.RED);
            lblStatus.setText("Field cannot be left Empty");
        }
        else
        {
            if(newPass.equals(confirmNewPass))
            {
                try
                {
                    udModel.changePassword(newPass,userName);
                    udModel.updateUser(userName);
                    Util.sceneSwitcher("../View/employeeManageAccount.fxml", Util.getStage(event));

                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                lblStatus.setTextFill(Color.RED);
                lblStatus.setText("Passwords, do not match!");
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