package Model;

import main.SQLConnection;

import java.sql.Connection;

public class ForgotPasswordModel {

    Connection connection;

    public ForgotPasswordModel()
    {
        connection = SQLConnection.connect();
    }

    //TODO
    //user validation methods

}
