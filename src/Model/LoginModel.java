package Model;

import main.SQLConnection;
import main.User;
import main.UserHolder;
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

    public void retriveInfo(String username) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM Employee WHERE username = ? ;";
        User user = new User();
        UserHolder holder = UserHolder.getInstance();
        System.out.println("method");
        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            // preparedStatement.setString(2, password);
            // preparedStatement.setString(3, role);

            resultSet = preparedStatement.executeQuery();
            // System.out.println(resultSet.next());
            // System.out.println("before here");
            if (resultSet.next())
            {
                // System.out.println("here");
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
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }


        public Boolean isLogin(String user, String pass, String role) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM Employee WHERE username = ? AND password= ? AND role = ?;";
        // User newUser = new User();
        // UserHolder holder = UserHolder.getInstance();
        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);
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
            return resultSet.next();

            //todo -- if()

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }finally {
            assert preparedStatement != null;
            preparedStatement.close();
            // assert resultSet != null;
            // resultSet.close();
        }

    }

    // public void userAccess(String)

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
