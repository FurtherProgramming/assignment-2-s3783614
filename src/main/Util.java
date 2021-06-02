package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Util {

    public static Stage getStage(ActionEvent event)
    {
        return (Stage)((Node)event.getSource()).getScene().getWindow();
    }

    public static void popUpWindow(String page, Button button, Stage stage) throws IOException
    {
        Parent root;
        stage = new Stage();
        root = FXMLLoader.load(Util.class.getResource(page));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(button.getScene().getWindow());
        stage.showAndWait();
    }

    public static void sceneSwitcher(String page, Stage stage) throws IOException
    {
        Parent root;

        root = FXMLLoader.load(Util.class.getResource(page));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public static void exitBtn(Button button)
    {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
}
