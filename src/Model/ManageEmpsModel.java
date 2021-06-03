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
                String status = resultSet.getString("status");
                int empId = resultSet.getInt("emp_ID");
                User user = new User(firstName,lastName,username,password,secQuestion,secretAnswer,role,empId,status);
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

    public User fillUserDetails(int empId) throws SQLException {
        User user = new User();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM Employee WHERE emp_ID = ?;";

        try
        {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,empId);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                user.setEmpId(resultSet.getInt("emp_ID"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setSecQuestion(resultSet.getString("secret_question"));
                user.setSecAnswer(resultSet.getString("secret_answer"));
                user.setRole(resultSet.getString("role"));
                user.setStatus(resultSet.getString("status"));

            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            assert preparedStatement != null;
            preparedStatement.close();
            assert resultSet != null;
            resultSet.close();
        }

        return user;
    }

    public void editEmployee(int empId, String firstName, String lastName,
                             String username, String password, String secretQuestion, String secretAnswer) throws SQLException {
        PreparedStatement preparedStatement = null;

        String query = "UPDATE Employee SET first_name = ?, last_name = ?, username = ?, password = ?, secret_question = ?, secret_answer = ? WHERE emp_ID = ?";
        try
        {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,lastName);
            preparedStatement.setString(3,username);
            preparedStatement.setString(4,password);
            preparedStatement.setString(5, secretQuestion);
            preparedStatement.setString(6,secretAnswer);
            preparedStatement.setInt(7,empId);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            assert preparedStatement != null;
            preparedStatement.close();

        }

    }

    public void updateStatus(String status, int empId) throws SQLException
    {
        connection = SQLConnection.connect();

        PreparedStatement preparedStatement = null;

        String query = "UPDATE Employee SET status = ? WHERE emp_ID = ?";

        try
        {
            assert connection != null;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, empId);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            assert preparedStatement != null;
            preparedStatement.close();
        }
    }


    public void deleteEmployee(int empId) throws SQLException {
        connection = SQLConnection.connect();

        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM Employee WHERE emp_ID = ?";
        try
        {
            assert connection != null;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, empId);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            assert preparedStatement != null;
            preparedStatement.close();
        }

    }


}
