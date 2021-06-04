package Controller;

import Model.EmployeeBookingModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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

    @FXML
    private DatePicker dtpBooking;
    @FXML
    private Label lblWelcome;
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

        setTables();
        // LocalDate date = dtpBooking.getValue();
        // System.out.println(date);

        // try
        // {
        // }
        // catch (SQLException e)
        // {
        //     e.printStackTrace();
        // }


    }

    public void setTables()/* throws SQLException*/ {
        LocalDate date = dtpBooking.getValue();
        System.out.println( "setTables method : " + date);
        try {
            ArrayList<Booking> bookings = ebModel.getBookings(date);
            if (bookings.isEmpty()) {
                Table1.setFill(Color.LAWNGREEN);
                Table2.setFill(Color.LAWNGREEN);
                Table3.setFill(Color.LAWNGREEN);
                Table4.setFill(Color.LAWNGREEN);
                Table5.setFill(Color.LAWNGREEN);
                Table6.setFill(Color.LAWNGREEN);
                Table7.setFill(Color.LAWNGREEN);
                Table8.setFill(Color.LAWNGREEN);
            } else {
                assignTables(bookings);
            }
        }
         catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // public void setTable1(ArrayList<Booking> booking)
    // {
    //     for(int i = 0; i < booking.size(); i++)
    //     {
    //        if(booking.get(i).getTable().equals("Table1"))
    //        {
    //            Table1.setFill(Color.RED);
    //        }
    //     }
    // }


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

        for (Booking value : booking)
        {
            for (Rectangle table : tables)
            {
                String tableStr = table.getId();
                if (value.getTable().equals(tableStr))
                {
                    table.setFill(Color.RED);
                    //TODO: Pending status and Accepted status change
                }
                else
                {
                    table.setFill(Color.LAWNGREEN);
                }
            }
        }

    }




    public void selectTable(MouseEvent event) throws IOException {
        //Grabs the details of the rectangle that gets clicked
        Rectangle tableClicked = (Rectangle) event.getSource();
        String tableId = tableClicked.getId();

        UserHolder.getInstance().setTableNo(tableId);
        UserHolder.getInstance().setDate(dtpBooking.getValue());

        Util.popUpRectangleWindow("../View/employeeBookingConfirmation.fxml",Table1);

    }

    public void previousPage(ActionEvent event) throws IOException
    {
        Util.sceneSwitcher("../View/employeeDashboard.fxml", Util.getStage(event));
    }

}
