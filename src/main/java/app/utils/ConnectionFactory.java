package app.utils;
import app.Main;
import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = ConnectionConfig.URL;
    private static final String user = ConnectionConfig.user;
    private static final String password = ConnectionConfig.password;

    public static Connection getConnection() {
        try {
            Main.CONNECTIONS_CREATED++;
            Connection con = DriverManager.getConnection(URL, user, password);
            return con;

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error connecting to database");
        }
    }

}
