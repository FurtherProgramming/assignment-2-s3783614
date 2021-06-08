package Controller;

import Model.EmployeeBookingModel;
import Model.LockdownConditionsModel;
import Model.ManageEmpsModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeMakeBookingController implements Initializable {
    User user = new User();
    EmployeeBookingModel ebModel = new EmployeeBookingModel();
    ManageEmpsModel manageEmpsModel = new ManageEmpsModel();
    LockdownConditionsModel lcm = new LockdownConditionsModel();


    private final Color emptySeat = Color.LIGHTGREEN;
    private final Color bookedSeat = Color.RED;
    private final Color pendingSeat = Color.	SKYBLUE;
    private final Color socialDistance = Color.ORANGE;
    private final Color totalLock = Color.DARKRED;

    @FXML
    private DatePicker dtpBooking;
    @FXML
    private Label lblWelcome;
    @FXML
    private Label lblCurrentBooking;
    @FXML
    private Label lblLockdown;
    @FXML
    private Button btnManageBooking;
    @FXML
    private Button btnCheckIn;

    @FXML private Rectangle Table1;
    @FXML private Rectangle Table2;
    @FXML private Rectangle Table3;
    @FXML private Rectangle Table4;
    @FXML private Rectangle Table5;
    @FXML private Rectangle Table6;
    @FXML private Rectangle Table7;
    @FXML private Rectangle Table8;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        user = UserHolder.getInstance().getUser();
        lblWelcome.setText(user.getFirstName().substring(0,1).toUpperCase() + user.getFirstName().substring(1).toLowerCase() +
                            " which table would you like to book?");
        dtpBooking.setValue(LocalDate.now().plusDays(3));


        //TODO: explain

        // dtpBooking.setDayCellFactory(d ->
        //             new DateCell()
        //             {
        //                 @Override
        //                 public void updateItem(LocalDate item, boolean empty)
        //                 {
        //                     super.updateItem(item, empty);
        //                     setDisable(item.isBefore(LocalDate.now()));
        //                 }
        //             }
        //         );

        try
        {
            setUp();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public void setUp() throws SQLException
    {
        // String condition = UserHolder.getInstance().getCondition();

        LocalDate date = dtpBooking.getValue();
        if(lcm.conditionExists(date))
        {
            Lockdown lockdown = lcm.retriveConditions(date);
            String lockdownCondition = lockdown.getLockdownStatus();
            ArrayList<Booking> bookings = ebModel.getBookings(date);
            if(lockdownCondition.equals("Social Distancing"))
            {
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
                }
                else
                {
                    assignTables(bookings, "Social Distancing");
                }

            }
            else if(lockdownCondition.equals("Full Lockdown"))
            {
                Table1.setFill(totalLock);
                Table2.setFill(totalLock);
                Table3.setFill(totalLock);
                Table4.setFill(totalLock);
                Table5.setFill(totalLock);
                Table6.setFill(totalLock);
                Table7.setFill(totalLock);
                Table8.setFill(totalLock);
            }
            else
            {
                setTables(date);
            }


        }
        // if no condition default to what we had
        else
        {
            setTables(date);
        }




        user = UserHolder.getInstance().getUser();

        // Managing the delete selection
        if(ebModel.bookingExists(user.getUsername()))
        {
            btnManageBooking.setVisible(true);
            lblCurrentBooking.setText("You have already have a booking");

            Booking booking = ebModel.getBookingDetails(user.getUsername());

            LocalDate bookingDate = booking.getDate();
            LocalDate cutOffDate = bookingDate.minusDays(2);
            LocalDate today = LocalDate.now();
            int compareValue = today.compareTo(cutOffDate);

            if(compareValue > 0)
            {
                //TODO: SPLIT INTO 2 LATER
                lblCurrentBooking.setText( "Current booking: " + bookingDate +
                        " Booking Status: " + booking.getStatus());
                btnManageBooking.setVisible(false);

                System.out.println("Today is later than Cutoff Data");
                System.out.println("booking date : " + bookingDate);
                System.out.println("cutoff date : "  + cutOffDate);
                System.out.println("today: " + today);
            }

            //TODO: check these conditions later
            else if (compareValue < 0)
            {
                System.out.println("Today is earlier than Cutoff Data");
                System.out.println("booking date : " + bookingDate);
                System.out.println("cutoff date : "  + cutOffDate);
                System.out.println("today: " + today);
            }
            else
            {
                System.out.println("both dates are equal");
                System.out.println("booking date : " + bookingDate);
                System.out.println("cutoff date : "  + cutOffDate);
                System.out.println("today: " + today);
            }

            //allowing check in
            int compareCheckIn = today.compareTo(bookingDate);
            if(compareCheckIn != 0 || (booking.getStatus().equals("Pending")) || booking.getReservation().equals("In")){
                btnCheckIn.setVisible(false);
            }
        }
        else
        {
            lblCurrentBooking.setText("No booking to manage!");
            btnCheckIn.setVisible(false);
            btnManageBooking.setVisible(false);
        }

    }

    public void setTables(LocalDate date) throws SQLException {
        ArrayList<Booking> bookings = ebModel.getBookings(date);
        // System.out.println( "setTables method : " + date);
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
                        System.out.println(tableStr + " : pending");
                        tables.get(i).setFill(pendingSeat);
                    }
                    else if(value.getStatus().equals("Approved"))
                    {
                        System.out.println(tableStr + " : booked");
                        tables.get(i).setFill(bookedSeat);
                    }
                }
            }
        }
    }

    //Employee delete their own bookings
    public void manageBooking(ActionEvent event) throws IOException
    {
        user = UserHolder.getInstance().getUser();
        try
        {
            if(ebModel.bookingExists(user.getUsername()))
            {
                Util.popButtonUpWindow("../View/employeeManageBooking.fxml", btnManageBooking);
                setUp();
            }
            else
            {
                Util.alertError("No Booking To Manage!");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        // Util.sceneSwitcher("../View/employeeManageBooking.fxml",Util.getStage(event));
    }

    //TODO: set alert box for booking when there is a pending status on the table
    public void selectTable(MouseEvent event) throws IOException
    {
        //Grabs the details of the rectangle that gets clicked
        Rectangle rectangleClicked = (Rectangle) event.getSource();
        user = UserHolder.getInstance().getUser();
        if(user.getPreviousTable() == null)
        {
            System.out.println("previous table: " + user.getPreviousTable());

        }

        try {

            if (rectangleClicked.getFill().equals(bookedSeat)) {
                Util.alertError("This seat has already been booked!");
            }//covid restrictions
            else if(rectangleClicked.getFill().equals(socialDistance) || rectangleClicked.getFill().equals(totalLock))
            {
                Util.alertError("Seat has been locked due to COVID restrictions!");
            }
            else if(ebModel.bookingExists(user.getUsername()))
            {
                Util.alertError("Please delete your previous booking, to make a new one");
            }
            else if(user.getPreviousTable().equals(rectangleClicked.getId()))
            {
                Util.alertError("Please be a bit more social, Pick another table!");
            }
            else
            {
                if(rectangleClicked.getFill().equals(pendingSeat))
                {
                    Util.alertWarning("Other employees are waiting to get the seat approved!");
                }
                String tableId = rectangleClicked.getId();

                UserHolder.getInstance().setTableNo(tableId);
                UserHolder.getInstance().setDate(dtpBooking.getValue());

                Util.popUpRectangleWindow("../View/employeeBookingConfirmation.fxml", Table1);

                //To reset the tables after a booking has been made
                setUp();


            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }


    }


    public void checkIn(ActionEvent event) throws IOException
    {
        Util.popButtonUpWindow("../View/employeeCheckIn.fxml",btnCheckIn);
    }

    //Move back to previous page
    public void previousPage(ActionEvent event) throws IOException
    {
        Util.sceneSwitcher("../View/employeeDashboard.fxml", Util.getStage(event));
    }

}
