package Model;

import main.SQLConnection;

import java.sql.*;

public class RegistrationModel {

    Connection connection;

    public RegistrationModel()
    {
        connection = SQLConnection.connect();
    }

    public boolean isRegistered(String firstName, String lastName, String  username,
                                String password, String secretQuestion, String secretAnswer)
    {
        String queryString;
        queryString = "INSERT INTO Employee(first_name, last_name, username, password, secret_question, secret_answer)" +
                                                                                                " VALUES(?,?,?,?,?,?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(queryString))
        {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3,username);
            preparedStatement.setString(4,password);
            preparedStatement.setString(5,secretQuestion);
            preparedStatement.setString(6,secretAnswer);

            preparedStatement.executeUpdate();

        }

        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean usernameExists(String username)
    {
        connection = SQLConnection.connect();
        PreparedStatement preparedStatement= null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM Employee WHERE username = ?";
        boolean doesExist = false;

        try
        {
            assert connection != null;
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
                }
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();

        }
        return doesExist;
    }


}
