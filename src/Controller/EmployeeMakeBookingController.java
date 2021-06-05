package Controller;

import Model.EmployeeBookingModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Booking;
import main.User;
import main.UserHolder;
import main.Util;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeMakeBookingController implements Initializable {
    User user = new User();
    EmployeeBookingModel ebModel = new EmployeeBookingModel();

    private final Color emptySeat = Color.LIGHTGREEN;
    private final Color bookedSeat = Color.RED;
    private final Color pendingSeat = Color.	SKYBLUE;

    @FXML
    private DatePicker dtpBooking;
    @FXML
    private Label lblWelcome;
    @FXML
    private Label lblCurrentBooking;
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
        dtpBooking.setValue(LocalDate.now().plusDays(2));

       //TODO:disable previous dates, *look at stack post
        // dtpBooking.setDayCellFactory(picker-> new DateCell());

        try
        {
            setTables();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public void setTables() throws SQLException {
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
            // setTable1(bookings);
            // setTable2(bookings);
            // setTable3(bookings);
            // setTable4(bookings);
            // setTable5(bookings);
            // setTable6(bookings);
            // setTable7(bookings);
            // setTable8(bookings);
        }
       user = UserHolder.getInstance().getUser();

        if(ebModel.bookingExists(user.getUsername()))
        {
            lblCurrentBooking.setText("You have already have a booking");
        }
        else
        {
            lblCurrentBooking.setText("You currently have no booking to manage!");
        }

    }

    public void manageBooking(ActionEvent event) throws IOException {
        user = UserHolder.getInstance().getUser();
        try
        {
            if(ebModel.bookingExists(user.getUsername()))
            {
                Util.popButtonUpWindow("../View/employeeManageBooking.fxml", btnManageBooking);
                setTables();
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

    //TODO: set alert box for booking when there is a pending status on the table
    public void selectTable(MouseEvent event) throws IOException {
        //Grabs the details of the rectangle that gets clicked
        Rectangle rectangleClicked = (Rectangle) event.getSource();

        try {

            if (rectangleClicked.getFill().equals(bookedSeat)) {
                Util.alertError("This seat has already been booked!");
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
                setTables();


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
