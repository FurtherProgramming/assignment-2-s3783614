package Controller;

import Model.EmployeeBookingModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import main.UserHolder;
import main.Util;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EmployeeBookingConfirmationController implements Initializable {
    EmployeeBookingModel ebModel = new EmployeeBookingModel();

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtTableNo;
    @FXML
    private TextField txtDate;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnConfirm;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        String firstName = UserHolder.getInstance().getUser().getFirstName();
        txtName.setText(firstName);

        String tableNo = UserHolder.getInstance().getTableNo();
        txtTableNo.setText(tableNo);

        LocalDate date = UserHolder.getInstance().getDate();
        txtDate.setText(String.valueOf(date));
    }

    public void cancel()
    {
        Util.exitBtn(btnCancel);
    }

    public void confirm()
    {
        String username = UserHolder.getInstance().getUser().getUsername();
        String tableNo = UserHolder.getInstance().getTableNo();
        LocalDate date = UserHolder.getInstance().getDate();

        try {
            if(ebModel.addBooking(username, tableNo, date))
            {
                Util.alertSuccessPopUp("Booking successful!", btnConfirm);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
