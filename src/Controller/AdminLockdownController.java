package Controller;

import Model.LockdownConditionsModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import main.UserHolder;
import main.Util;

import java.io.IOException;
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
    }

    // public void setLockdown(ActionEvent event)
    // {
    //     String previousCondition = UserHolder.getInstance().getCondition();
    //     String condition = cBoxType.getValue();
    //
    //     if(previousCondition.equals(condition))
    //     {
    //         Util.alertError("No change to current condition!");
    //     }
    //     // else if(condition.equals("No Restriction"))
    //     // {
    //     //     if(Util.alertConfirmation())
    //     //     {
    //     //         Util.alertSuccessPopUp("Successfully Set New Restriction", btnConfirm);
    //     //
    //     //     }
    //     // }
    //     //TODO: have to customize message!
    //     else if(Util.alertConfirmation())
    //     {
    //         LocalDate restrictionDate = dtpLockdownDate.getValue();
    //         System.out.println("restrictionDate: " + restrictionDate);
    //
    //         try
    //         {
    //             if(!condition.equals("No Restriction"))
    //             {
    //                 lcm.deleteBookings(restrictionDate);
    //             }
    //             UserHolder.getInstance().setCondition(condition);
    //             UserHolder.getInstance().setConditionDate(restrictionDate);
    //             Util.alertSuccessPopUp("Successfully Set New Restriction", btnConfirm);
    //
    //         }
    //         catch (SQLException e)
    //         {
    //             e.printStackTrace();
    //         }
    //
    //
    //     }
    // }

    public void setLockdown(ActionEvent event)
    {
        String status = cBoxType.getValue();
        LocalDate restrictionDate = dtpLockdownDate.getValue();
        try
        {
            if(lcm.conditionExists(restrictionDate))
            {
                if(Util.alertConfirmation())
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
                if(Util.alertConfirmation())
                {
                    if(lcm.newCondition(status,restrictionDate))
                    {
                        if(!status.equals("No Restriction"))
                        {
                            lcm.deleteBookings(restrictionDate);
                        }
                        // lcm.deleteBookings(restrictionDate);
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

    public void cancel(ActionEvent event) throws IOException
    {
        Util.exitBtn(btnCancel);
    }
}
