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
            // System.out.println(resultSet.next());
            // return resultSet.next();
            String usernameCheck;
            if(resultSet.next())
            {
                usernameCheck = resultSet.getString("username");
                if(usernameCheck.equals(username))
                {
                    doesExist = true;
                    user.setfName(resultSet.getString("first_name"));
                    user.setlName(resultSet.getString("last_name"));
                    user.setUserName(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setSecQuestion(resultSet.getString("secret_question"));
                    user.setSecAnswer(resultSet.getString("secret_answer"));
                    user.setRole(resultSet.getString("role"));
                    holder.setUser(user);
                }
            }


        }
        catch(SQLException e)
        {
            e.printStackTrace();

        }
        System.out.println(doesExist);
        return doesExist;
    }

    //TODO
    //user validation methods

}
