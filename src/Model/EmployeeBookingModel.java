package Model;

import main.Booking;
import main.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class EmployeeBookingModel {

    Connection connection;

    public EmployeeBookingModel()
    {
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }



    public ArrayList<Booking> getBookings(LocalDate date) throws SQLException
    {

        ArrayList<Booking> bookings = new ArrayList<>();
        String dateString = date.toString();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM Booking WHERE date = ?";
        try
        {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, dateString);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                int bookingId = resultSet.getInt("booking_ID");
                String empUsername = resultSet.getString("emp_username");
                String dateStr = resultSet.getString("date");
                String table = resultSet.getString("booked_table");
                String status = resultSet.getString("booking_status");
                LocalDate  dbDate = LocalDate.parse(dateStr);

                Booking booking = new Booking(bookingId, empUsername, dbDate, table, status);
                bookings.add(booking);
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

        return bookings;
    }

}
