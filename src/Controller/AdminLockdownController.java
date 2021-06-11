package Controller;

import Model.LockdownConditionsModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import main.UserHolder;
import main.Util;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AdminLockdownController implements Initializable
{
    LockdownConditionsModel lcm = new LockdownConditionsModel();


    @FXML private Button btnCancel;
    @FXML private Button btnConfirm;
    @FXML private ComboBox<String> cBoxType;
    @FXML private DatePicker dtpLockdownDate;

    ObservableList<String> lockdown = FXCollections.observableArrayList("No Restriction", "Social Distancing", "Full Lockdown");

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        //Gets the current level restriction
        String restrictionType = UserHolder.getInstance().getCondition();
        dtpLockdownDate.setValue(LocalDate.now().plusDays(3));
        cBoxType.setValue(restrictionType);
        cBoxType.setItems(lockdown);

        //Disables the date before today
        dtpLockdownDate.setDayCellFactory(d ->
            new DateCell()
            {
                @Override
                public void updateItem(LocalDate item, boolean empty)
                {
                    super.updateItem(item, empty);
                    setDisable(item.isBefore(LocalDate.now()));
                }
            }
        );
    }

    public void setLockdown()
    {
        String status = cBoxType.getValue();
        LocalDate restrictionDate = dtpLockdownDate.getValue();
        try
        {
            if(lcm.conditionExists(restrictionDate))
            {
                if(Util.alertConfirmation("Updating conditions might delete bookings!"))
                {
                    lcm.updateCondition(restrictionDate,status);
                    if(!status.equals("No Restriction"))
                    {
                        lcm.deleteBookings(restrictionDate);
                    }
                    Util.alertSuccessPopUp("Restriction successfully Updated!", btnConfirm);
                }
            }
            else
            {
                if(Util.alertConfirmation("Updating conditions might delete bookings!"))
                {
                    if(lcm.newCondition(status,restrictionDate))
                    {
                        if(!status.equals("No Restriction"))
                        {
                            lcm.deleteBookings(restrictionDate);
                        }
                        Util.alertSuccessPopUp("Restriction Successfully Created!", btnConfirm);
                    }
                }
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void cancel()
    {
        Util.exitBtn(btnCancel);
    }
}
