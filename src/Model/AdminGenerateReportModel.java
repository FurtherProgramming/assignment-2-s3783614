package Model;

import main.SQLConnection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class AdminGenerateReportModel {

    Connection connection;

    public AdminGenerateReportModel()
    {
        connection = SQLConnection.connect();

        if(connection == null)
        {
            System.exit(1);
        }
    }


    public void generateBookingReport() throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM Booking WHERE booking_status = 'Approved'";

        try
        {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            //Instantiation for file work
            File bookingCSV = new File("Booking_Report_For_" + LocalDate.now() + ".csv");

            PrintWriter printWriter = new PrintWriter(bookingCSV);

            printWriter.println("booking_ID, emp_username, date, booked_table, booking_status, reservation");

            while (resultSet.next())
            {
                int booking_ID = resultSet.getInt("booking_ID");
                String emp_username = resultSet.getString("emp_username");
                String date = resultSet.getString("date");
                String booked_table = resultSet.getString("booked_table");
                String booking_status = resultSet.getString("booking_status");
                String reservation = resultSet.getString("reservation");

                printWriter.printf("%d,%s,%s,%s,%s,%s%n",booking_ID,emp_username,date,booked_table,booking_status,reservation);

            }

            printWriter.close();
        }
        catch (SQLException | FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally {
            assert preparedStatement != null;
            preparedStatement.close();
            assert resultSet != null;
            resultSet.close();
        }
    }

    public void generateEmployeeReport() throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM Employee";

        try
        {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            //Instantiation for file work
            File employeeCSV = new File("Employee_Report_For_" + LocalDate.now() + ".csv");

            PrintWriter printWriter = new PrintWriter(employeeCSV);

            printWriter.println("Employee ID, First Name, Last Name, Username, Password, Secret Question, Secret Password, Role, Status, Previous Table");

            while (resultSet.next())
            {
                int emp_ID = resultSet.getInt("emp_ID");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String secret_question = resultSet.getString("secret_question");
                String secret_answer = resultSet.getString("secret_answer");
                String role = resultSet.getString("role");
                String status = resultSet.getString("status");
                String previous_table = resultSet.getString("previous_table");

                printWriter.printf("%d,%s,%s,%s,%s,%s,%s,%s,%s,%s%n",
                        emp_ID,first_name,last_name,username,password,secret_question,secret_answer,role,status,previous_table);

            }

            printWriter.close();
        }
        catch (SQLException | FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally {
            assert preparedStatement != null;
            preparedStatement.close();
            assert resultSet != null;
            resultSet.close();
        }
    }

    public void generateLockdownReport() throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM Lockdown";

        try
        {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            //Instantiation for file work
            File lockdownCSV = new File("Lockdown_Report_For_" + LocalDate.now() + ".csv");

            PrintWriter printWriter = new PrintWriter(lockdownCSV);

            printWriter.println("Lockdown ID, Lockdown Status, Lockdown Date");

            while (resultSet.next())
            {
                int lockdown_ID = resultSet.getInt("lockdown_ID");
                String lockdown_status = resultSet.getString("lockdown_status");
                String lockdown_date = resultSet.getString("lockdown_date");

                printWriter.printf("%d,%s,%s%n",
                        lockdown_ID,lockdown_status,lockdown_date);

            }

            printWriter.close();
        }
        catch (SQLException | FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally {
            assert preparedStatement != null;
            preparedStatement.close();
            assert resultSet != null;
            resultSet.close();
        }
    }


}
