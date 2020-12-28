package app.dao;

import app.Main;
import app.utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractJbdcDao<T> implements Dao<T> {

    private Connection connection = ConnectionFactory.getConnection();

    protected abstract String getSelectQuery();
    protected abstract String getCreateQuery();
    protected abstract String getUpdateQuery();
    protected abstract String getDeleteQuery();

    public abstract int getId(T t);

    protected abstract List<T> parseResultSet(ResultSet rs);

    protected abstract void prepareStatementForInsert(PreparedStatement st, T t) throws SQLException;
    protected abstract void prepareStatementForUpdate(PreparedStatement st, T t) throws SQLException;

    @Override
    public T get(int key) {

        List<T> list = new ArrayList<>();
        String sql = getSelectQuery();
        sql += " WHERE id = ?";

        long time = System.currentTimeMillis();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }

        time = System.currentTimeMillis() - time;
        Main.TOTAL_CONNECTION_TIME += time;

        if (list.size() == 1)
            return list.iterator().next();
        else {
            assert list.size() == 1 : "get result more than one";
            return null;
        }
}
    public T get(String key, String column) {

        List<T> list = new ArrayList<>();
        String sql = getSelectQuery();
        sql += " WHERE "+ column + " = ?";

        long time = System.currentTimeMillis();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, key);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }

        time = System.currentTimeMillis() - time;
        Main.TOTAL_CONNECTION_TIME += time;

        if (list.size() == 1)
            return list.iterator().next();
        else {
            assert list.size() == 1 : "get result more than one";
            return null;
        }
    }

    @Override
    public List<T> getAll() {

            List<T> list = new ArrayList<>();
            String sql = getSelectQuery();

            long time = System.currentTimeMillis();

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet rs = statement.executeQuery();
                list = parseResultSet(rs);
            } catch (Exception e) {
               e.printStackTrace();
            }

            time = System.currentTimeMillis() - time;
            Main.TOTAL_CONNECTION_TIME += time;

            return list;
    }

    @Override
    public boolean exist(int key) {
        List<T> list = new ArrayList<>();
        String sql = getSelectQuery();
        sql += " WHERE id = ?";

        long time = System.currentTimeMillis();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }

        time = System.currentTimeMillis() - time;
        Main.TOTAL_CONNECTION_TIME += time;

        if (list.size() > 0)
            return true;
        else
            return false;
    }

    public boolean existByStringValueColumn(String key, String column) {
        List<T> list = new ArrayList<>();
        String sql = getSelectQuery();
        sql += " WHERE "+ column +" = ?";

        long time = System.currentTimeMillis();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, key);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }

        time = System.currentTimeMillis() - time;
        Main.TOTAL_CONNECTION_TIME += time;

        if (list != null && list.size() == 1)
            return true;
        else
            return false;
    }

    @Override
    public void save(T t) {

        assert getId(t) == 0 : "creating new row by old row";

        String sql = getCreateQuery();

        long time = System.currentTimeMillis();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForInsert(statement, t);
            int count = statement.executeUpdate();
            assert count == 1 : "creating more then 1 el";
        } catch (Exception e) {
            e.printStackTrace();
        }

        time = System.currentTimeMillis() - time;
        Main.TOTAL_CONNECTION_TIME += time;
    }

    @Override
    public void update(T t) {

        String sql = getUpdateQuery();

        long time = System.currentTimeMillis();

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            prepareStatementForUpdate(statement, t);
            int count = statement.executeUpdate();

            assert count == 1 : "updated more then 1 el";

        } catch (Exception e) {
            e.printStackTrace();
        }

        time = System.currentTimeMillis() - time;
        Main.TOTAL_CONNECTION_TIME += time;
    }

    @Override
        public void delete(T t) {

        if (getId(t) == -1) return;

        long time = System.currentTimeMillis();

        String sql = getDeleteQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, getId(t));
            int count = statement.executeUpdate();
            assert count == 1 : "deleted more then 1 el";
        } catch (Exception e) {
            e.printStackTrace();
        }

        time = System.currentTimeMillis() - time;
        Main.TOTAL_CONNECTION_TIME += time;

    }
}
