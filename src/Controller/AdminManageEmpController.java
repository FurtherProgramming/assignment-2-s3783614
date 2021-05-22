package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Util;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminManageEmpController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
