package Controller;

import Model.EmployeeBookingModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.Booking;
import main.User;
import main.UserHolder;
import main.Util;

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
        LocalDate date = dtpBooking.getValue();
        System.out.println( "setTables method : " + date);

        ArrayList<Booking> bookings = ebModel.getBookings(date);
        if (bookings.isEmpty()) {
            Table1.setFill(emptySeat);
            Table2.setFill(emptySeat);
            Table3.setFill(emptySeat);
            Table4.setFill(emptySeat);
            Table5.setFill(emptySeat);
            Table6.setFill(emptySeat);
            Table7.setFill(emptySeat);
            Table8.setFill(emptySeat);
        } else {
            assignTables(bookings);
        }
        user = UserHolder.getInstance().getUser();


        if(ebModel.bookingExists(user.getUsername()))
        {
            lblCurrentBooking.setText("You have already have a booking");

            Booking booking = ebModel.getBookingDetails(user.getUsername());
            // if(booking == null)
            // {
            //     System.out.println("No bookings bud!");
            // }
            // else
            // {
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
            // }
        }
        else
        {
            lblCurrentBooking.setText("No booking to manage!");
        }

    }


    public void assignTables(ArrayList<Booking> booking)
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

        // for (Rectangle table : tables) {
        // }
        for (Rectangle table : tables)
        {
            table.setFill(emptySeat);
            for (Booking value : booking)
            {
                String tableStr = table.getId();
                if (value.getTable().equals(tableStr))
                {
                    if(value.getStatus().equals("Pending"))
                    {
                        System.out.println(tableStr + " : pending");
                        table.setFill(pendingSeat);
                    }
                    else if(value.getStatus().equals("Approved"))
                    {
                        System.out.println(tableStr + " : booked");
                        table.setFill(bookedSeat);
                    }
                }
                // else if(!table.getFill().equals(bookedSeat) && !table.getFill().equals(pendingSeat))
                // {
                //     // System.out.println(table.getId() +  " being set to green");
                //     table.setFill(emptySeat);
                // }
            }
        }
    }

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


    public void previousPage(ActionEvent event) throws IOException
    {
        Util.sceneSwitcher("../View/employeeDashboard.fxml", Util.getStage(event));
    }

}
