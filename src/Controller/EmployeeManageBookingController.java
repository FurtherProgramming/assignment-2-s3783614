package Controller;

import Model.EmployeeBookingModel;
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

public class EmployeeManageBookingController implements Initializable {
    EmployeeBookingModel ebModel = new EmployeeBookingModel();
    User user = new User();

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtDate;
    @FXML
    private TextField txtTable;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnCancel;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        user = UserHolder.getInstance().getUser();
        // txtName.setText(user.getFirstName());
        Booking booking;
        try
        {
            booking = ebModel.getBookingDetails(user.getUsername());
            System.out.println(booking.getEmpUsername());
            txtName.setText(booking.getEmpUsername());
            String date = booking.getDate().toString();
            txtDate.setText(date);
            txtTable.setText(booking.getTable());

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public void deleteBooking(ActionEvent event)
    {
        user = UserHolder.getInstance().getUser();
        if(Util.alertConfirmation())
        {
            try
            {
                ebModel.deleteBooking(user.getUsername());
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            Util.alertSuccessPopUp("Booking Successfully Deleted!", btnDelete);
        }

    }

    public void cancel()
    {
        Util.exitBtn(btnCancel);
    }

}
