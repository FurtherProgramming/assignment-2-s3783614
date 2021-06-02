package Model;

import main.SQLConnection;
import main.User;
import main.UserHolder;

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

    //SYSTEMS DONT LIKE THIS->might need to modify
    // public void retrieveInfo(String username) {
    //     User user = new User();
    //     UserHolder holder = UserHolder.getInstance();
    //
    //     PreparedStatement preparedStatement = null;
    //     ResultSet resultSet = null;
    //     String query = "SELECT * FROM Employee WHERE username = ? ;";
    //     System.out.println("method");
    //     try {
    //
    //         preparedStatement = connection.prepareStatement(query);
    //         preparedStatement.setString(1, username);
    //
    //         resultSet = preparedStatement.executeQuery();
    //         // System.out.println(resultSet.next());
    //         // System.out.println("before here");
    //         if (resultSet.next())
    //         {
    //             // System.out.println("here");
    //             user.setfName(resultSet.getString("first_name"));
    //             user.setlName(resultSet.getString("last_name"));
    //             user.setUserName(resultSet.getString("username"));
    //             user.setPassword(resultSet.getString("password"));
    //             user.setSecQuestion(resultSet.getString("secret_question"));
    //             user.setSecAnswer(resultSet.getString("secret_answer"));
    //             user.setRole(resultSet.getString("role"));
    //             holder.setUser(user);
    //         }
    //
    //     }
    //     catch (SQLException e)
    //     {
    //         e.printStackTrace();
    //     }
    //
    // }

    public Boolean isLogin(String username, String pass, String role) throws SQLException {

        boolean logInCheck = false;

        User user = new User();
        UserHolder holder = UserHolder.getInstance();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM Employee WHERE username = ? AND password= ? AND role = ?;";

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, pass);
            preparedStatement.setString(3, role);

            resultSet = preparedStatement.executeQuery();
            // System.out.println(resultSet.next());
            // if(resultSet.next())
            // {
            //     newUser.setfName(resultSet.getString("first_name"));
            // }
            // holder.setUser(newUser);
            // resultSet.absolute(0);
            // resultSet.beforeFirst();
            // System.out.println(resultSet.next());
            if(resultSet.next())
            {
                logInCheck = true;
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setSecQuestion(resultSet.getString("secret_question"));
                user.setSecAnswer(resultSet.getString("secret_answer"));
                user.setRole(resultSet.getString("role"));
                holder.setUser(user);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();

            logInCheck = false;
        }
        finally
        {
            assert preparedStatement != null;
            preparedStatement.close();
            assert resultSet != null;
            resultSet.close();
        }

        return logInCheck;

    }


}
