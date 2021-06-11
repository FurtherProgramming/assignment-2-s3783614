package Controller;

import Model.EmployeeBookingModel;
import Model.LockdownConditionsModel;
import Model.ManageEmpsModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminGraphicalVisualController implements Initializable {

    User user = new User();
    EmployeeBookingModel ebModel = new EmployeeBookingModel();
    LockdownConditionsModel lcm = new LockdownConditionsModel();

    private final Color emptySeat = Color.LIGHTGREEN;
    private final Color bookedSeat = Color.LIGHTCORAL;
    private final Color pendingSeat = Color.SKYBLUE;
    private final Color socialDistance = Color.DARKORANGE;
    private final Color totalLock = Color.CRIMSON;

    @FXML
    private DatePicker dtpBooking;
    @FXML
    private Label lblWelcome;
    @FXML
    private Label lblLockdown;
    @FXML
    private Button btnClose;
    @FXML
    private Button btnHelp;


    @FXML private Rectangle Table1;
    @FXML private Rectangle Table2;
    @FXML private Rectangle Table3;
    @FXML private Rectangle Table4;
    @FXML private Rectangle Table5;
    @FXML private Rectangle Table6;
    @FXML private Rectangle Table7;
    @FXML private Rectangle Table8;
    @FXML private Rectangle Table9;
    @FXML private Rectangle Table10;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user = UserHolder.getInstance().getUser();
        lblWelcome.setText("Hello: " + user.getFirstName().substring(0,1).toUpperCase() +
                user.getFirstName().substring(1).toLowerCase());
        dtpBooking.setValue(LocalDate.now().plusDays(3));

        dtpBooking.setDayCellFactory(d ->
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

        try {
            setUp();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void setUp() throws SQLException
    {

        LocalDate date = dtpBooking.getValue();
        if(lcm.conditionExists(date))
        {
            Lockdown lockdown = lcm.retriveConditions(date);
            String lockdownCondition = lockdown.getLockdownStatus();
            ArrayList<Booking> bookings = ebModel.getBookings(date);
            if(lockdownCondition.equals("Social Distancing"))
            {
                lblLockdown.setText(lockdownCondition);

                if(bookings.isEmpty())
                {
                    Table1.setFill(socialDistance);
                    Table2.setFill(emptySeat);
                    Table3.setFill(socialDistance);
                    Table4.setFill(emptySeat);
                    Table5.setFill(socialDistance);
                    Table6.setFill(emptySeat);
                    Table7.setFill(socialDistance);
                    Table8.setFill(emptySeat);
                    Table9.setFill(socialDistance);
                    Table10.setFill(emptySeat);
                }
                else
                {
                    assignTables(bookings, "Social Distancing");
                }

            }
            else if(lockdownCondition.equals("Full Lockdown"))
            {
                lblLockdown.setText(lockdownCondition);

                Table1.setFill(totalLock);
                Table2.setFill(totalLock);
                Table3.setFill(totalLock);
                Table4.setFill(totalLock);
                Table5.setFill(totalLock);
                Table6.setFill(totalLock);
                Table7.setFill(totalLock);
                Table8.setFill(totalLock);
                Table9.setFill(totalLock);
                Table10.setFill(totalLock);
            }
            else
            {
                lblLockdown.setText(lockdownCondition);

                setTables(date);
            }

        }
        // if no condition default to what we had
        else
        {
            lblLockdown.setText("No Restrictions");
            setTables(date);
        }

    }

    public void setTables(LocalDate date) throws SQLException {
        ArrayList<Booking> bookings = ebModel.getBookings(date);

        //Sets Up all tables
        if (bookings.isEmpty())
        {
            Table1.setFill(emptySeat);
            Table2.setFill(emptySeat);
            Table3.setFill(emptySeat);
            Table4.setFill(emptySeat);
            Table5.setFill(emptySeat);
            Table6.setFill(emptySeat);
            Table7.setFill(emptySeat);
            Table8.setFill(emptySeat);
            Table9.setFill(emptySeat);
            Table10.setFill(emptySeat);
        }
        else
        {
            assignTables(bookings, "No Restriction");
        }
    }

    public void assignTables(ArrayList<Booking> booking, String lockdownStatus)
    {
        ArrayList<Rectangle> tables = new ArrayList<>();
        tables.add(Table1);
        tables.add(Table2);
        tables.add(Table3);
        tables.add(Table4);
        tables.add(Table5);
        tables.add(Table6);
        tables.add(Table7);
        tables.add(Table8);
        tables.add(Table9);
        tables.add(Table10);

        for(int i = 0; i < tables.size(); i++)
        {
            if(lockdownStatus.equals("Social Distancing"))
            {
                tables.get(i).setFill(emptySeat);

                if(i % 2 == 0)
                {
                    tables.get(i).setFill(socialDistance);
                }
            }
            else
            {
                tables.get(i).setFill(emptySeat);
            }
            for (Booking value : booking)
            {
                String tableStr = tables.get(i).getId();
                if (value.getTable().equals(tableStr))
                {
                    if(value.getStatus().equals("Pending"))
                    {
                        tables.get(i).setFill(pendingSeat);
                    }
                    else if(value.getStatus().equals("Approved"))
                    {
                        tables.get(i).setFill(bookedSeat);
                    }
                }
            }
        }
    }

    public void close()
    {
        Util.exitBtn(btnClose);
    }

    public void help() throws IOException
    {
        Util.popButtonUpWindow("../View/employeeBookingHelp.fxml", btnHelp);
    }
}
