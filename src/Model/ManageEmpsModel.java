package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.SQLConnection;
import main.User;
import main.UserHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManageEmpsModel
{
    Connection connection;

    public ManageEmpsModel()
    {
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }

    public ObservableList<User> observableEmployeeList() throws SQLException {
        connection = SQLConnection.connect();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        ObservableList<User> employeeList = FXCollections.observableArrayList();
        String query = "SELECT * FROM Employee /*WHERE role = 'User'*/";
        try
        {
            //
            assert connection != null;
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String secQuestion = resultSet.getString("secret_question");
                String secretAnswer = resultSet.getString("secret_answer");
                String role = resultSet.getString("role");
                int empId = resultSet.getInt("emp_ID");
                User user = new User(firstName,lastName,username,password,secQuestion,secretAnswer,role,empId);
                employeeList.add(user);

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally
        {
            assert connection != null;
            connection.close();
            assert preparedStatement != null;
            preparedStatement.close();
            assert resultSet != null;
            resultSet.close();
        }

        return employeeList;
    }

    public void deleteEmployee(int empId) throws SQLException {
        connection = SQLConnection.connect();

        // User user = new User();
        // UserHolder holder = UserHolder.getInstance();

        PreparedStatement preparedStatement = null;


        String sql = "DELETE FROM Employee WHERE emp_ID = ?";
        try
        {
            assert connection != null;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, empId);
            preparedStatement.executeUpdate();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        finally
        {
            assert preparedStatement != null;
            preparedStatement.close();
        }

    }


}
