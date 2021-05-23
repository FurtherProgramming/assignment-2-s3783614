package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.User;
import main.UserHolder;
import main.Util;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeManageAccount implements Initializable {

    User user = new User();

    @FXML
    private Label lblWelcomeUser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserHolder holder = UserHolder.getInstance();
        user = holder.getUser();
        String name = user.getfName();
        // lblWelcomeUser.setText(name);
        lblWelcomeUser.setText("Hello " +
                name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase());
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
