package Controller;

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
import javafx.stage.Stage;
import main.User;
import main.UserHolder;
import main.Util;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable
{
    User user = new User();

    @FXML
    private Label lblWelcome;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserHolder holder = UserHolder.getInstance();
        user = holder.getUser();
        String name = user.getfName();
        // lblWelcome.setText(name);
        lblWelcome.setText("Hello " +
                name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase());
    }

    public void previousPage(ActionEvent event) throws IOException {
        // Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Parent root = FXMLLoader.load(getClass().getResource("../View/loginPage.fxml"));
        // Scene scene = new Scene(root);
        // stage.setScene(scene);
        // stage.show();

        Util.sceneSwitcher("../View/loginPage.fxml", Util.getStage(event));

    }

    public void lockdownConditions(ActionEvent event) throws IOException {
        // Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Parent root = FXMLLoader.load(getClass().getResource("../View/adminLockdownCondition.fxml"));
        // Scene scene = new Scene(root);
        // stage.setScene(scene);
        // stage.show();
        Util.sceneSwitcher("../View/adminLockdownCondition.fxml", Util.getStage(event));

    }

    public void  manageAdminAccount(ActionEvent event) throws IOException{
        // Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Parent root = FXMLLoader.load(getClass().getResource("../View/adminManageAccount.fxml"));
        // Scene scene = new Scene(root);
        // stage.setScene(scene);
        // stage.show();

        Util.sceneSwitcher("../View/adminManageAccount.fxml", Util.getStage(event));

    }

    public void  manageEmployee(ActionEvent event) throws IOException{
        // Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Parent root = FXMLLoader.load(getClass().getResource("../View/adminManageEmployees.fxml"));
        // Scene scene = new Scene(root);
        // stage.setScene(scene);
        // stage.show();

        Util.sceneSwitcher("../View/adminManageEmployees.fxml", Util.getStage(event));

    }
}
