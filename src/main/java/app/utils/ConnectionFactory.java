package app.utils;
import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:mysql://192.168.1.100:3306/html_game";
    private static final String user = "sammy";
    private static final String password = "21012000";

    public static Connection getConnection() {
        try {

            Connection con = DriverManager.getConnection(URL, user, password);
            return con;

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error connecting to database");
        }
    }

}
