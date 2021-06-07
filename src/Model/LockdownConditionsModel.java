package Model;

import main.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class LockdownConditionsModel {


    Connection connection;

    public LockdownConditionsModel()
    {
        connection = SQLConnection.connect();
        if(connection == null)
        {
            System.exit(1);
        }
    }

    public void deleteBookings(LocalDate date) throws SQLException
    {
        connection = SQLConnection.connect();

        PreparedStatement preparedStatement = null;

        String query = "DELETE FROM Booking WHERE date = ?";

        try
        {
            assert connection != null;
            preparedStatement = connection.prepareStatement(query);
            String dateStr = date.toString();
            preparedStatement.setString(1,dateStr);
            preparedStatement.executeUpdate();
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
        }
    }

}
