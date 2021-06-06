package Model;

import main.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeCheckInModel {
    Connection connection;

    public EmployeeCheckInModel()
    {
        connection = SQLConnection.connect();
        if(connection == null)
        {
            System.exit(1);
        }
    }

    public void updateCheckIn(String username, String res) throws SQLException
    {
        connection = SQLConnection.connect();

        PreparedStatement preparedStatement = null;

        String query = "UPDATE Booking SET reservation = ? WHERE emp_username = ?";

        try
        {
            assert connection != null;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, res);
            preparedStatement.setString(2,username);
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

    public void addPrevDesk(String username, String table) throws SQLException
    {
        connection = SQLConnection.connect();


        PreparedStatement preparedStatement = null;

        String query = "UPDATE Employee SET previous_table = ? WHERE username = ?";

        try
        {
            assert connection != null;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, table);
            preparedStatement.setString(2,username);
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
