package Model;

import main.SQLConnection;
import org.sqlite.SQLiteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {

    Connection connection;

    public LoginModel(){

        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);

    }

    public Boolean isDbConnected(){
        try {
            return !connection.isClosed();
        }
        catch(Exception e){
            return false;
        }
    }

    public Boolean isLogin(String user, String pass, String role) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM Employee WHERE username = ? AND password= ? AND role = ?;";
        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);
            preparedStatement.setString(3, role);

            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
        catch (Exception e)
        {
            return false;
        }finally {
            assert preparedStatement != null;
            preparedStatement.close();
            assert resultSet != null;
            resultSet.close();
        }

    }

    // public boolean isAdmin(String status) throws SQLException
    // {
    //     PreparedStatement preparedStatement = null;
    //     ResultSet resultSet = null;
    //     String query = "SELECT * FROM Employee WHERE role = ?;";
    //     try {
    //
    //         preparedStatement = connection.prepareStatement(query);
    //         preparedStatement.setString(1, status);
    //
    //         resultSet = preparedStatement.executeQuery();
    //         return resultSet.next();
    //
    //     }
    //     catch (Exception e)
    //     {
    //         return false;
    //     }finally {
    //         assert preparedStatement != null;
    //         preparedStatement.close();
    //         assert resultSet != null;
    //         resultSet.close();
    //     }
    // }



}
