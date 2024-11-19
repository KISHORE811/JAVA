import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection connect() {
        try 
        {
            String url = "jdbc:mysql://localhost:3306/StudentDB";
            String username = "root";
            String password = "";  
            return DriverManager.getConnection(url, username, password);
        } 
        catch (SQLException e) 
        {
            System.out.println("Database connection failed!");
            e.printStackTrace();
            return null;
        }
    }
}
