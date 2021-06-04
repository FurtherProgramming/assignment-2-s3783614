package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Util {

    public static Stage getStage(ActionEvent event)
    {
        return (Stage)((Node)event.getSource()).getScene().getWindow();
    }


    public static void popUpRectangleWindow(String page, Rectangle rectangle) throws IOException
    {
        Parent root;
        Stage stage = new Stage();
        root = FXMLLoader.load(Util.class.getResource(page));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(rectangle.getScene().getWindow());
        stage.showAndWait();
    }

    public static void popButtonUpWindow(String page, Button button) throws IOException
    {
        Parent root;
        Stage stage = new Stage();
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

    @FXML
    public static void alertError(String message)
    {
        Alert a1 = new Alert(Alert.AlertType.ERROR);
        a1.setTitle("ERROR!");
        a1.setContentText(message);
        a1.setHeaderText(null);
        a1.showAndWait();
    }

    @FXML
    public static void alertSuccessPopUp(String message, Button button)
    {
        Alert a1 = new Alert(AlertType.INFORMATION);
        a1.setTitle("SUCCESS!");
        a1.setContentText(message);
        a1.setHeaderText(null);
        a1.showAndWait();
        ((Stage) button.getScene().getWindow()).close();
    }

    public static void alertSuccess(String message)
    {
        Alert a1 = new Alert(AlertType.INFORMATION);
        a1.setTitle("SUCCESS!");
        a1.setContentText(message);
        a1.setHeaderText(null);
        a1.showAndWait();
    }

    @FXML
    public static boolean alertConfirmation()
    {
        boolean status = false;
        Alert a1 = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
        a1.setTitle("CONFIRMATION");
        a1.setContentText("ARE YOU SURE?");
        a1.setHeaderText(null);

        Optional<ButtonType> result = a1.showAndWait();
        if(result.get() == ButtonType.YES)
        {
            status = true;
        }
        else
        {
            a1.close();
        }

        return status;
    }

}
