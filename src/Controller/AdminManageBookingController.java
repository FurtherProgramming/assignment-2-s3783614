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

    @FXML
    private Button btnApprove;
    @FXML
    private TableView<Booking> tblBooking;
    @FXML
    private TableColumn<Booking, Integer> tblBookingID;
    @FXML
    private TableColumn<Booking, String> tblUsername;
    @FXML
    private TableColumn<Booking, LocalDate> tblBookDate;
    @FXML
    private TableColumn<Booking, String> tblBookedTable;
    @FXML
    private TableColumn<Booking, String> tblStatus;
    @FXML
    private TableColumn<Booking, String> tblReservation;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

        try
        {
            //TODO: Possibly write a setup method just calling these two
            deleteOldBookings();
            fillTable();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void fillTable() throws SQLException
    {
        // TODO: Manual whitelisting technique
        //  Will have to use method from manageEmployees to retrieve
        //  the user list and just bring over a previous desk
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
        // System.out.println(yesterday);
        boolean check = manageBookingModel.bookingsExistsOnDate(yesterday);
        // System.out.println(check);
        if(check)
        {
            manageBookingModel.deletePreviousBookings(yesterday);
        }
    }

    public void acceptBooking(ActionEvent event)
    {
        if(tblBooking.getSelectionModel().getSelectedItem() == null)
        {
            Util.alertError("Please select a field to proceed!");
        }
        else
        {
            try {

                LocalDate bookingDate = tblBooking.getSelectionModel().getSelectedItem().getDate();
                LocalDate today = LocalDate.now();
                int compareDate = today.compareTo(bookingDate);

                // if (compareDate == 0)
                // {
                //     //TODO: better message and also figure out a way to make button disappear.
                //     Util.alertError("Cannot alter booking status");
                //     // btnApprove.setVisible(false);
                //
                // }
                // else
                // {
                    //TODO confirmation alert -> write in message this will delete all emps with same table :)
                    boolean confirm = Util.alertConfirmation();
                    if(confirm)
                    {
                        int bookingID = tblBooking.getSelectionModel().getSelectedItem().getBookingId();
                        System.out.println("selected booking Id is: " + bookingID);

                        manageBookingModel.approveBooking(bookingID, "Approved");
                        LocalDate date = tblBooking.getSelectionModel().getSelectedItem().getDate();
                        manageBookingModel.deletePreviousBookings(date);
                        fillTable();
                    // }

                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void rejectBooking(ActionEvent event)
    {
        if(tblBooking.getSelectionModel().getSelectedItem() == null)
        {
            Util.alertError("Please select a field to proceed!");
        }
        else
        {
            try
            {
                //TODO possibly delete later -> idea is cannot delete once approved

                // String status = tblBooking.getSelectionModel().getSelectedItem().getStatus();
                // if(status.equals("Approved"))
                // {
                //     Util.alertError("Cannot delete approved seat!");
                // }
                // else
                // {
                    int bookingID = tblBooking.getSelectionModel().getSelectedItem().getBookingId();
                    manageBookingModel.rejectBookings(bookingID);
                    fillTable();
                // }
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
