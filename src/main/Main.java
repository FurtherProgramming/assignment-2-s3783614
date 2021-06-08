package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/loginPage.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    //TODO:
    // Previous Desk on admin manage bookings
    // Generate CSV file ->Employee table ->Lockdown Table ->Booking Table
    // Lockdown tableView -> display all booking so far
    // Graphic Visualization for admin
    // Alert confirmation -> provide messages
    // Make a booking needs refactoring
    // Make a booking display the lock down conditions
    // Help page on make a booking -> FXML
}

