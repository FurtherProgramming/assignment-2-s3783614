package Model;

import main.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ForgotPasswordModel {

    Connection connection;

    public ForgotPasswordModel()
    {
        connection = SQLConnection.connect();
    }

    public void changePassword(String password) throws SQLException
    {
        String query = "UPDATE Employee SET password = ? WHERE ;";
        // PreparedStatement preparedStatement = connection.prepareStatement();

    }

    //TODO
    //user validation methods

}
