package app;

import java.sql.Connection;
import java.sql.DriverManager;
//import com.mysql.jdbc.Driver;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try
        {

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://192.168.1.100:3306/html_game","sammy","21012000");

            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from users");
//            System.out.println(rs.next());
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
