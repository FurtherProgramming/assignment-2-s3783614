package main;
import java.sql.*;

public class SQLConnection {
private static Connection connection;
    public static Connection connect(){
        try{
            //Checks for established connection
            //if connection exists then it closes the existent connection and opens  a new one
          if(connection == null)
          {
              Class.forName("org.sqlite.JDBC");
              connection = DriverManager.getConnection("jdbc:sqlite:assignment.db");
              return connection;
          }
          else
          {
              connection.close();
              connection = DriverManager.getConnection("jdbc:sqlite:assignment.db");
              return connection;
          }

        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
