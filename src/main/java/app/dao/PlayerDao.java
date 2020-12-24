package app.dao;

import app.models.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerDao extends AbstractJbdcDao<Player>{
    @Override
    protected String getSelectQuery() {
        return "SELECT * from players";
    }

    @Override
    protected String getCreateQuery() {
        //System.out.println("players create query");
        return "INSERT INTO players (login, HealthPoints, AttackRate, Elo) \n" +
                "VALUES (?, ?, ?, ?);";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE players SET login = ?, HealthPoints= ?, AttackRate = ?, Elo = ? WHERE id= ?;";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM players WHERE id= ?;";
    }

    @Override
    public int getId(Player player) {
        return player.getId();
    }

    @Override
    protected List<Player> parseResultSet(ResultSet rs){
        ArrayList<Player> result = new ArrayList<>();
        try {
            while (rs.next()) {
                Player player = new Player();
                player.setAttackRate(rs.getInt("AttackRate"));
                player.setHealthPoints(rs.getInt("HealthPoints"));
                player.setElo(rs.getInt("Elo"));
                player.setId(rs.getInt("id"));
                player.setName(rs.getString("login"));
                result.add(player);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement st, Player player) throws SQLException {
        st.setString(1, player.getName());
        st.setInt(2, player.getHealthPoints());
        st.setInt(3, player.getAttackRate());
        st.setInt(4, player.getElo());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement st, Player player) throws SQLException {
        st.setString(1, player.getName());
        st.setInt(2, player.getHealthPoints());
        st.setInt(3, player.getAttackRate());
        st.setInt(4, player.getElo());

        st.setInt(5, player.getId());
    }

    // @TODO добавить зависимость
}
