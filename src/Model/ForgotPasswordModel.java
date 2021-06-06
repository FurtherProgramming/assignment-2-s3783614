package Model;

import main.SQLConnection;
import main.User;
import main.UserHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ForgotPasswordModel {

    Connection connection;

    public ForgotPasswordModel()
    {
        connection = SQLConnection.connect();
    }

    public void changePassword(String password, String user) throws SQLException
    {
        PreparedStatement preparedStatement = null;
        String query = "UPDATE Employee SET password = ? WHERE username = ?;";
        try
        {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,password);
            preparedStatement.setString(2, user);
            preparedStatement.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public boolean usernameExists(String username)
    {
        User user = new User();
        UserHolder holder = UserHolder.getInstance();

        PreparedStatement preparedStatement= null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM Employee WHERE username = ?";
        boolean doesExist = false;


        try
        {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            String usernameCheck;
            if(resultSet.next())
            {
                usernameCheck = resultSet.getString("username");
                if(usernameCheck.equals(username))
                {
                    doesExist = true;
                    user.setFirstName(resultSet.getString("first_name"));
                    user.setLastName(resultSet.getString("last_name"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setSecQuestion(resultSet.getString("secret_question"));
                    user.setSecAnswer(resultSet.getString("secret_answer"));
                    user.setRole(resultSet.getString("role"));
                    user.setStatus(resultSet.getString("status"));
                    user.setPreviousTable(resultSet.getString("previous_table"));
                    holder.setUser(user);
                }
            }


        }
        catch(SQLException e)
        {
            e.printStackTrace();

        }

        return doesExist;
    }

    //TODO
    //user validation methods

}
