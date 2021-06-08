package Controller;

import Model.AdminGenerateReportModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.User;
import main.UserHolder;
import main.Util;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminGenerateCSV implements Initializable
{
    User user = new User();
    AdminGenerateReportModel reportModel = new AdminGenerateReportModel();

    @FXML private Label lblWelcome;
    @FXML private Button btnBack;
    // @FXML private

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        user = UserHolder.getInstance().getUser();
        String fName = user.getFirstName();
        lblWelcome.setText(fName.substring(0,1).toUpperCase() + fName.substring(1).toLowerCase() +
                " What report would you like generated today?");
    }

    public void employeeCSV(ActionEvent event)
    {

    }

    public void lockdownCSV(ActionEvent event)
    {

    }

    public void bookingCSV(ActionEvent event)
    {
        try {
            reportModel.generateBookingReport();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void closeWindow(ActionEvent event)
    {
        Util.exitBtn(btnBack);
    }
}
