package app.dao;

import app.models.Player;
import app.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDao extends AbstractJbdcDao<User>{


    @Override
    protected String getSelectQuery() {
        return "SELECT * from users";
    }


    @Override
    protected String getCreateQuery() {
        return "INSERT INTO users (login, password) \n" +
                "VALUES (?, ?);";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE users SET login= ?, password = ? WHERE id= ?;";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM users WHERE id= ?;";
    }

    @Override
    public int getId(User user) {
        return user.getId();
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

    @Override
    protected void prepareStatementForInsert(PreparedStatement st, User user) throws SQLException {
        st.setString(1, user.getName());
        st.setString(2, user.getPassword());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement st, User user) throws SQLException {
        st.setString(1, user.getName());
        st.setString(2, user.getPassword());
        st.setInt(3, user.getId());
    }

    public Player getPlayer(int id) {
        PlayerDao playerDao = new PlayerDao();
        return playerDao.get(id);
    }
}
