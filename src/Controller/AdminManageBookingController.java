package Controller;

import Model.ManageBookingModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Booking;
import main.User;
import main.Util;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AdminManageBookingController implements Initializable
{
    ManageBookingModel manageBookingModel = new ManageBookingModel();

    @FXML private Button btnApprove;
    @FXML private TableView<Booking> tblBooking;
    @FXML private TableColumn<Booking, Integer> tblBookingID;
    @FXML private TableColumn<Booking, String> tblUsername;
    @FXML private TableColumn<Booking, LocalDate> tblBookDate;
    @FXML private TableColumn<Booking, String> tblBookedTable;
    @FXML private TableColumn<Booking, String> tblStatus;
    @FXML private TableColumn<Booking, String> tblReservation;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        try
        {
            deleteOldBookings();
            fillTable();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    //fills the table
    public void fillTable() throws SQLException
    {
        //sets the values of the table
        ObservableList<Booking> bookings;
        bookings = manageBookingModel.observableBookings();
        tblBooking.setItems(bookings);
        tblBookingID.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        tblUsername.setCellValueFactory(new PropertyValueFactory<>("empUsername"));
        tblBookDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblBookedTable.setCellValueFactory(new PropertyValueFactory<>("table"));
        tblStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tblReservation.setCellValueFactory(new PropertyValueFactory<>("reservation"));
    }

    public void deleteOldBookings() throws SQLException
    {
        LocalDate yesterday = LocalDate.now();
        boolean check = manageBookingModel.bookingsExistsOnDate(yesterday);

        if(check)
        {
            //If old booking exists this will delete any old booking before today
            manageBookingModel.deletePreviousBookings(yesterday);
        }
    }

    public void acceptBooking()
    {
        if(tblBooking.getSelectionModel().getSelectedItem() == null)
        {
            Util.alertError("Please select a field to proceed!");
        }
        else
        {
            try {
                // gets the booking date from the table view
                LocalDate bookingDate = tblBooking.getSelectionModel().getSelectedItem().getDate();
                LocalDate today = LocalDate.now();
                //compares today to the booking date for checking whether it is acceptable to book
                int compareDate = today.compareTo(bookingDate);

                if (compareDate == 0)
                {
                    Util.alertError("Cannot Accept booking status");
                }
                else
                {
                    //confirms users choice to accept a request to book a tabel as this will delete the other requests
                    boolean confirm = Util.alertConfirmation("Approving this request will delete other requests to sit on the same table!");
                    if(confirm)
                    {
                        int bookingID = tblBooking.getSelectionModel().getSelectedItem().getBookingId();
                        String bookedTable = tblBooking.getSelectionModel().getSelectedItem().getTable();

                        LocalDate date = tblBooking.getSelectionModel().getSelectedItem().getDate();
                        manageBookingModel.approveBooking(bookingID, "Approved");
                        manageBookingModel.deleteSameBooking(date, bookedTable, "Pending");
                        fillTable();
                    }

                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void rejectBooking()
    {
        if(tblBooking.getSelectionModel().getSelectedItem() == null)
        {
            Util.alertError("Please select a field to proceed!");
        }
        else
        {
            try
            {
                int bookingID = tblBooking.getSelectionModel().getSelectedItem().getBookingId();
                manageBookingModel.rejectBookings(bookingID);
                fillTable();

            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }

        }
    }

    public void previousPage(ActionEvent event) throws IOException
    {
        Util.sceneSwitcher("../View/adminDashboard.fxml", Util.getStage(event));
    }
}
