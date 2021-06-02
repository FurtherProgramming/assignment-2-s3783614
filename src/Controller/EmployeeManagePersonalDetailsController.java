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

public class EmployeeManagePersonalDetailsController implements Initializable {

    UpdateDetailsModel udModel = new UpdateDetailsModel();

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
        UserHolder holder = UserHolder.getInstance();
        User user = new User();
        user = holder.getUser();

        // String userRole = user.getRole();

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
        String FirstName = txtFirstName.getText();
        String SecondName = txtSecondName.getText();
        String username = user.getUsername();
        if(FirstName.equals("") || SecondName.equals(""))
        {
            //TODO: Alert Box
            lblStatus.setTextFill(Color.RED);
            lblStatus.setText("Field cannot be left Empty");
        }
        else
        {
            try {
                udModel.changeFname(FirstName, username);
                udModel.changeLastname(SecondName, username);
                udModel.updateUser(username);
                Util.sceneSwitcher("../View/employeeManageAccount.fxml", Util.getStage(event));
            } catch (SQLException e) {
                e.printStackTrace();
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
