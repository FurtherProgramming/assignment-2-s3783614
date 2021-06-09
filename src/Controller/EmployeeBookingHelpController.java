package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.Util;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeBookingHelpController implements Initializable {
    private final Color emptySeat = Color.LIGHTGREEN;
    private final Color bookedSeat = Color.LIGHTCORAL;
    private final Color pendingSeat = Color.SKYBLUE;
    private final Color socialDistance = Color.DARKORANGE;
    private final Color totalLock = Color.CRIMSON;

    @FXML private Rectangle rect1;
    @FXML private Rectangle rect2;
    @FXML private Rectangle rect3;
    @FXML private Rectangle rect4;
    @FXML private Rectangle rect5;

    @FXML private Button btnBack;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rect1.setFill(emptySeat);
        rect2.setFill(pendingSeat);
        rect3.setFill(bookedSeat);
        rect4.setFill(socialDistance);
        rect5.setFill(totalLock);
    }

    public void back()
    {
        Util.exitBtn(btnBack);
    }
}
