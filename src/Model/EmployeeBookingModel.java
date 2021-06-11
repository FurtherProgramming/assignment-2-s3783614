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

    public EmployeeBookingModel() {
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }


    //Returns Booking For Particular date
    public ArrayList<Booking> getBookings(LocalDate date) throws SQLException {
        connection = SQLConnection.connect();

        ArrayList<Booking> bookings = new ArrayList<>();
        String dateString = date.toString();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM Booking WHERE date = ?";
        try {
            assert connection != null;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, dateString);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int bookingId = resultSet.getInt("booking_ID");
                String empUsername = resultSet.getString("emp_username");
                String dateStr = resultSet.getString("date");
                String table = resultSet.getString("booked_table");
                String status = resultSet.getString("booking_status");
                String reservation = resultSet.getString("reservation");
                LocalDate dbDate = LocalDate.parse(dateStr);

                Booking booking = new Booking(bookingId, empUsername, dbDate, table, status, reservation);
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            assert preparedStatement != null;
            preparedStatement.close();
            assert resultSet != null;
            resultSet.close();
        }

        return bookings;
    }

    //Returns booking details for particular employee
    public Booking getBookingDetails(String username) throws SQLException {
        Booking booking = new Booking();
        connection = SQLConnection.connect();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM Booking WHERE emp_username = ?";

        try
        {
            assert connection != null;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                int bookingId = resultSet.getInt("booking_ID");
                String empUsername = resultSet.getString("emp_username");
                String dateStr = resultSet.getString("date");
                String table = resultSet.getString("booked_table");
                String status = resultSet.getString("booking_status");
                String reservation = resultSet.getString("reservation");
                LocalDate dbDate = LocalDate.parse(dateStr);

                booking = new Booking(bookingId, empUsername, dbDate, table, status, reservation);

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

        return booking;
    }


    public boolean bookingExists(String username/*, LocalDate date*/) throws SQLException
    {
        connection = SQLConnection.connect();

        boolean exists = false;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM Booking WHERE emp_username = ? /*AND date = ?*/";
        try
        {
            assert connection != null;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,username);
            // String dateStr = date.toString();
            // preparedStatement.setString(2,dateStr);
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
            assert connection != null;
            connection.close();
            assert preparedStatement != null;
            preparedStatement.close();
            assert resultSet != null;
            resultSet.close();
        }

        return exists;
    }

    public boolean addBooking(String empUsername, String bookedTable, LocalDate date) throws SQLException {
        connection = SQLConnection.connect();

        PreparedStatement preparedStatement = null;

        String query = "INSERT INTO Booking(emp_username, date, booked_table)" +
                                                            "VALUES(?,?,?)";
        try
        {
            assert connection != null;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, empUsername);
            String dateStr = date.toString();
            preparedStatement.setString(2, dateStr);
            preparedStatement.setString(3, bookedTable);
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
        return true;
    }

    public void deleteBooking(String empUsername) throws SQLException {
        PreparedStatement preparedStatement = null;

        String query = "DELETE FROM Booking WHERE emp_username = ?";

        try
        {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, empUsername);
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



}
