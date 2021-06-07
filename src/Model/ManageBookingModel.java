package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Booking;
import main.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ManageBookingModel {

    Connection connection;

    public ManageBookingModel()
    {
        connection = SQLConnection.connect();
        if(connection == null)
        {
            System.exit(1);
        }
    }

    public ObservableList<Booking> observableBookings() throws SQLException
    {
        connection = SQLConnection.connect();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        ObservableList<Booking> bookingList = FXCollections.observableArrayList();
        String query = "SELECT * FROM Booking";

        try
        {
            assert connection != null;
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                int bookingId = resultSet.getInt("booking_ID");
                String empUsername = resultSet.getString("emp_username");
                String dateStr = resultSet.getString("date");
                LocalDate date = LocalDate.parse(dateStr);
                String table = resultSet.getString("booked_table");
                String status = resultSet.getString("booking_status");
                String reservation = resultSet.getString("reservation");

                Booking booking = new Booking(bookingId,empUsername,date,table,status,reservation);
                bookingList.add(booking);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            assert preparedStatement != null;
            preparedStatement.close();
            assert resultSet != null;
            resultSet.close();
        }
        return bookingList;
    }

    //REJECTS BOOKINGS - DELETES EMPLOYEES BOOKING
    public void rejectBookings(int bookingId) throws SQLException
    {
        connection = SQLConnection.connect();
        PreparedStatement preparedStatement = null;
        // ResultSet resultSet = null;
        String query = "DELETE FROM Booking WHERE booking_ID = ?";
        // String dateStr = date.toString();

        try
        {
            assert connection != null;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,bookingId);
            preparedStatement.executeUpdate();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            assert preparedStatement != null;
            preparedStatement.close();
        }
    }

    //APPROVES THE BOOKING STATUS OF EMPLOYEES
    public void approveBooking(int bookingId, String status) throws SQLException {
        connection = SQLConnection.connect();

        PreparedStatement preparedStatement = null;

        String query = "UPDATE Booking SET booking_status = ? WHERE booking_ID = ?";

        try
        {
            assert connection != null;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, bookingId);
            preparedStatement.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            assert connection != null;
            connection.close();
            assert preparedStatement != null;
            preparedStatement.close();
        }
    }


    public void deleteSameBooking(LocalDate date, String bookedTable ,String status) throws SQLException
    {
        connection = SQLConnection.connect();
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM Booking WHERE date = ? AND booked_table = ? AND booking_status = ? ";
        String dateStr = date.toString();

        try
        {
            assert connection != null;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,dateStr);
            preparedStatement.setString(2,bookedTable);
            preparedStatement.setString(3,status);
            preparedStatement.executeUpdate();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            assert preparedStatement != null;
            preparedStatement.close();
        }
    }

    public void deletePreviousBookings(LocalDate date) throws SQLException
    {
        connection = SQLConnection.connect();
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM Booking WHERE date < ?";
        String dateStr = date.toString();

        try
        {
            assert connection != null;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,dateStr);
            // preparedStatement.setString(2,status);
            preparedStatement.executeUpdate();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            assert preparedStatement != null;
            preparedStatement.close();
        }
    }

    //METHOD TO CHECK IF THERE ARE ANY BOOKINGS ON THAT PARTICULAR DATE
    public boolean bookingsExistsOnDate(LocalDate date) throws SQLException
    {
        String dateStr = date.toString();
        boolean exists = false;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM Booking WHERE date = ?";
        try
        {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,dateStr);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                exists = true;

            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            assert preparedStatement != null;
            preparedStatement.close();
            assert resultSet != null;
            resultSet.close();
        }

        return exists;
    }



}
