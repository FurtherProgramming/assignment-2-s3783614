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
import javafx.stage.Stage;
import main.Util;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminManageAccController implements Initializable
{

    @FXML
    private ComboBox<String> cboSecretQuestion;

    // ObservableList<String> qList = FXCollections.observableArrayList("What was your first pet?",
    //         "What city did your parents meet?",
    //         "What was your first job?",
    //         "Whats your oldest child's name?",
    //         "What was your first movie you watched?",
    //         "What was childhood nickname?",
    //         "Where is your favourite place to eat food?");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // cboSecretQuestion.setItems(qList);
    }


    public void previousPage(ActionEvent event) throws IOException {
        // Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Parent root = FXMLLoader.load(getClass().getResource("../View/adminDashboard.fxml"));
        // Scene scene = new Scene(root);
        // stage.setScene(scene);
        // stage.show();

        Util.sceneSwitcher("../View/adminDashboard.fxml", Util.getStage(event));
    }

}
