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

}
