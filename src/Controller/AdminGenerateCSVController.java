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
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AdminGenerateCSVController implements Initializable
{
    User user = new User();
    AdminGenerateReportModel reportModel = new AdminGenerateReportModel();

    @FXML private Label lblWelcome;
    @FXML private Button btnBack;
    @FXML private Button btnEmployee;
    @FXML private Button btnLockdown;
    @FXML private Button btnBooking;

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
        try {

            reportModel.generateEmployeeReport();
            String message = LocalDate.now() +" Employee report generated!";
            // Util.alertSuccessPopUp(message,btnEmployee);
            Util.alertSuccess(message);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void lockdownCSV(ActionEvent event)
    {
        try {

            reportModel.generateLockdownReport();
            String message = LocalDate.now() +" Lockdown report generated!";
            // Util.alertSuccessPopUp(message,btnLockdown);
            Util.alertSuccess(message);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void bookingCSV(ActionEvent event)
    {
        try {

            reportModel.generateBookingReport();
            String message = LocalDate.now() +" Booking report generated!";
            // Util.alertSuccessPopUp(message,btnBooking);
            Util.alertSuccess(message);

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void closeWindow(ActionEvent event)
    {
        Util.exitBtn(btnBack);
    }
}
