package app.dao;

import app.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsersDao extends AbstractJbdcDao<User>{

    @Override
    public String getSelectQuery() {
        return "SELECT * from users";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO users (login, password) \n" +
                "VALUES (?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE users SET login= ?, password = ? WHERE id= ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM users WHERE id= ?;";
    }

    @Override
    protected List<User> parseResultSet(ResultSet rs){
        ArrayList<User> result = new ArrayList<>();
        try {
            while (rs.next()) {
                User user = new User();
                user.setName(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setId(rs.getInt("id"));
                result.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
