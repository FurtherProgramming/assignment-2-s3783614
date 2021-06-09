package Controller;

import Model.EmployeeBookingModel;
import Model.EmployeeCheckInModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import main.Booking;
import main.User;
import main.UserHolder;
import main.Util;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeCheckInController implements Initializable
{
    User user = new User();
    Booking booking = new Booking();

    EmployeeBookingModel ebModel = new EmployeeBookingModel();
    EmployeeCheckInModel checkInModel = new EmployeeCheckInModel();
    // Booking booking = new Booking();

    @FXML private TextField txtUsername;
    @FXML private TextField txtTable;
    @FXML private TextField txtDate;
    @FXML private Button btnCancel;
    @FXML private Button btnConfirm;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try
        {
            user = UserHolder.getInstance().getUser();
            booking = ebModel.getBookingDetails(user.getUsername());
            txtUsername.setText(user.getFirstName());
            txtTable.setText(booking.getTable());
            txtDate.setText(booking.getDate().toString());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void confirmCheckIn(ActionEvent event)
    {
        user = UserHolder.getInstance().getUser();
        if(Util.alertConfirmation("You are about to check in, Are you sure?"))
        {
            try
            {
                booking = ebModel.getBookingDetails(user.getUsername());

                checkInModel.updateCheckIn(user.getUsername(), "In");
                checkInModel.addPrevDesk(user.getUsername(), booking.getTable());
                Util.alertSuccessPopUp("Successfully Checked In!", btnConfirm);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

        }
    }

    public void cancelCheckIn()
    {
        Util.exitBtn(btnCancel);
    }
}
