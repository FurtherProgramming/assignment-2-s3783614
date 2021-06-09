package Model;

import main.SQLConnection;
import main.User;
import main.UserHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateDetailsModel {
    Connection connection;

    public UpdateDetailsModel()
    {
        connection = SQLConnection.connect();
    }

    public void changeFname(String fName, String user) throws SQLException {
        //TODO: update the user details
        PreparedStatement preparedStatement = null;
        String query = "UPDATE Employee SET first_name = ? WHERE username = ?;";
        try
        {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,fName);
            preparedStatement.setString(2, user);
            preparedStatement.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            assert preparedStatement != null;
            preparedStatement.close();
        }
    }

    public void changeLastname(String lName, String user) throws SQLException {
        PreparedStatement preparedStatement = null;
        String query = "UPDATE Employee SET last_name = ? WHERE username = ?;";
        try
        {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, lName);
            preparedStatement.setString(2, user);
            preparedStatement.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            assert preparedStatement != null;
            preparedStatement.close();
        }
    }

    public void changeSecQuestion(String question,String user) throws SQLException
    {
        PreparedStatement preparedStatement = null;
        String query = "UPDATE Employee SET secret_question = ? WHERE username = ?;";
        try
        {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, question);
            preparedStatement.setString(2, user);
            preparedStatement.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            assert preparedStatement != null;
            preparedStatement.close();
        }
    }
    public void changeSecAnswer(String Answer,String user) throws SQLException
    {
        PreparedStatement preparedStatement = null;
        String query = "UPDATE Employee SET secret_answer = ? WHERE username = ?;";
        try
        {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, Answer);
            preparedStatement.setString(2, user);
            preparedStatement.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            assert preparedStatement != null;
            preparedStatement.close();
        }
    }

    public void changePassword(String password, String user) throws SQLException
    {
        PreparedStatement preparedStatement = null;
        String query = "UPDATE Employee SET password = ? WHERE username = ?;";
        try
        {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, user);
            preparedStatement.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            assert preparedStatement != null;
            preparedStatement.close();
        }
    }

    public void updateUser(String userName) throws SQLException {
        User user = new User();
        UserHolder holder = UserHolder.getInstance();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM Employee WHERE username = ?;";

        try
        {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userName);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
            {
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setSecQuestion(resultSet.getString("secret_question"));
                user.setSecAnswer(resultSet.getString("secret_answer"));
                user.setRole(resultSet.getString("role"));
                user.setPreviousTable(resultSet.getString("previous_table"));
                holder.setUser(user);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            assert preparedStatement !=  null;
            preparedStatement.close();
        }

    }

}
