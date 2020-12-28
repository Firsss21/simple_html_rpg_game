package app;

import app.dao.UsersDao;
import app.models.Player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

    public static final Map<String, Player> PLAYERS = new ConcurrentHashMap<>();
    public static int CONNECTIONS_CREATED = 0;
    public static int TOTAL_CONNECTION_TIME = 0;

    public static int getAvgMs() {
        if (TOTAL_CONNECTION_TIME != 0 && CONNECTIONS_CREATED != 0)
            return (TOTAL_CONNECTION_TIME / CONNECTIONS_CREATED);
        else
            return 0;
    }
    // in millis
    public static final long WAITING_TIME_BEFORE_FIGHT = 30 * 1000;


}
