package Model;

import main.SQLConnection;

import java.sql.*;

public class RegistrationModel {

    // private PreparedStatement preparedStatement = null;

    Connection connection;

    public RegistrationModel()
    {
        connection = SQLConnection.connect();
    }

    public boolean isRegistered(String firstName, String lastName, String  username,
                                String password, int secretQuestion, String secretAnswer)
    {

        // System.out.println("spot 1");

        String queryString;
        queryString = "INSERT INTO Employee(first_name, last_name, username, password, secret_question, secret_answer) VALUES(?,?,?,?,?,?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(queryString))
        {
            System.out.println(preparedStatement);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);

            preparedStatement.setString(3,username);
            preparedStatement.setString(4,password);
            preparedStatement.setInt(5,secretQuestion);
            preparedStatement.setString(6,secretAnswer);

            preparedStatement.executeUpdate();

        }

        catch (SQLException e)
        {
            // System.out.println("sql error!");
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean usernameExists(String username)
    {
        PreparedStatement preparedStatement= null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM Employee WHERE username = ?";

        try
        {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }


    }


}
