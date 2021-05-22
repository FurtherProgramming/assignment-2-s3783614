package main;
import java.sql.*;
//TODO:
// EXPLAIN WHY :BASICALLY CAUSE IT CLOSES AND OPENS A NEW CONNECTION

public class SQLConnection {
private static Connection connection;
    public static Connection connect(){
        try{
          // Class.forName("org.sqlite.JDBC");
          //   return DriverManager.getConnection("jdbc:sqlite:assignment.db");

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
            System.out.println(e);
            return null;
        }
    }

    // public static Connection getConnection()
    // {
    //     return instance
    // }


}
