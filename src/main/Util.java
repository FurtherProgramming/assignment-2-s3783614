package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Util {

    public static Stage getStage(ActionEvent event)
    {
        return (Stage)((Node)event.getSource()).getScene().getWindow();
    }

    public static void sceneSwitcher(String page, Stage stage) throws IOException {
        Parent root;

        root = FXMLLoader.load(Util.class.getResource(page));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
