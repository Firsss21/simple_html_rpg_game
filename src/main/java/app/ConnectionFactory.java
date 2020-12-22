package app;
import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jbdc:mysql://192.168.1.100:3306/html_game";
    private static final String user = "root";
    private static final String password = "21012000";

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(URL, user, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error connecting to database");
        }
    }

    public static void main(String[] args) {
        ConnectionFactory.getConnection();
    }
}
