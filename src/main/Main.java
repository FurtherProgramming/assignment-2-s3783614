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
    // Lockdown tableView -> display all booking so far
    // Graphic Visualization for admin
    //
}
// Add more tables :
// Alert confirmation -> provide messages
// Help page on make a booking -> FXML
// Make a booking needs refactoring
// Previous Desk on admin manage bookings
// Change table colours
// Deleting previous bookings -> look at only deleting pending bookings before yesterday
// Make a booking display the lock down conditions
// Generate CSV file ->Employee table ->Lockdown Table ->Booking Table

