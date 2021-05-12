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
        queryString = "INSERT INTO EMPLOYEE(first_name, last_name, username, password, secret_question, secret_answer) VALUES(?,?,?,?,?,?)";
        // System.out.println("spot 2");
        try(PreparedStatement preparedStatement = connection.prepareStatement(queryString))
        {
            System.out.println(preparedStatement);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            // preparedStatement.setInt(3,1);
            preparedStatement.setString(3,username);
            preparedStatement.setString(4,password);
            preparedStatement.setInt(5,secretQuestion);
            preparedStatement.setString(6,secretAnswer);
            // preparedStatement.setInt(8, prevDesk);
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




}
