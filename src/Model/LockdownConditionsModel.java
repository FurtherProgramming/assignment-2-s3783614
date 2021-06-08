package Model;

import main.Lockdown;
import main.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public boolean conditionExists(LocalDate date)throws SQLException
    {
        connection = SQLConnection.connect();

        boolean exists = false;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM Lockdown WHERE lockdown_date = ?";
        try
        {
            String dateStr = date.toString();
            assert connection != null;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, dateStr);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                exists = true;
            }

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
            assert resultSet != null;
            resultSet.close();
        }

        return exists;
    }

    //UPDATES EXISTING CONDITION IN THE DATABASE
    public void updateCondition(LocalDate date, String status) throws SQLException {
        connection = SQLConnection.connect();
        PreparedStatement preparedStatement = null;
        String query = "UPDATE Lockdown SET lockdown_status = ? WHERE lockdown_date = ?";

        try
        {
            String dateStr = date.toString();
            assert connection != null;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,status);
            preparedStatement.setString(2,dateStr);
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

    public Lockdown retriveConditions(LocalDate date) throws SQLException {
        connection = SQLConnection.connect();
        Lockdown lockdown = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM Lockdown WHERE lockdown_date = ?";

        try
        {
            assert connection != null;
            preparedStatement = connection.prepareStatement(query);
            String dateStr = date.toString();
            preparedStatement.setString(1, dateStr);
            resultSet = preparedStatement.executeQuery();;

            if(resultSet.next())
            {
                String status = resultSet.getString("lockdown_status");
                String dateString = resultSet.getString("lockdown_date");
                LocalDate lockDate = LocalDate.parse(dateString);
                lockdown = new Lockdown(status,lockDate);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            assert connection != null;
            connection.close();
            assert preparedStatement != null;
            preparedStatement.close();
            assert resultSet != null;
            resultSet.close();
        }
        return lockdown;
    }

    //ENTERS A NEW CONDITION INTO THE TABLE
    public boolean newCondition(String status, LocalDate date)throws SQLException
    {
        connection = SQLConnection.connect();


        String query = "INSERT INTO Lockdown(lockdown_status, lockdown_date) VALUES(?,?)";

        assert connection != null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            preparedStatement.setString(1,status);
            String dateStr = date.toString();
            preparedStatement.setString(2,dateStr);
            preparedStatement.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
        finally {
            connection.close();
        }

        return true;
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
