package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.SQLConnection;
import main.User;

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
        // System.out.println("Spot 1");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        ObservableList<User> employeeList = FXCollections.observableArrayList();
        String query = "SELECT * FROM Employee /*WHERE role = 'User'*/";
        // System.out.println("Spot 2");
        try
        {
            // System.out.println("Spot 3");
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                // System.out.println("Spot 4");
                String firstName = resultSet.getString("first_name");
                // System.out.print(firstName + " ");
                String lastName = resultSet.getString("last_name");
                // System.out.println(lastName);
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
            // try {
                assert preparedStatement != null;
                preparedStatement.close();
                assert resultSet != null;
                resultSet.close();
            // } catch (SQLException throwables) {
            //     throwables.printStackTrace();
            // }

        }
        // System.out.println(employeeList);

        return employeeList;
    }


}
