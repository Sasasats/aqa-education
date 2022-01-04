package db;

import dao.IDao;
import models.sqlTables.TestTable;
import utils.SQLUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestDB implements IDao<TestTable> {
    private static final String SQL_GET_ATTRIBUTES = "SELECT * FROM TEST";

    private static final String SQL_INSERT = "INSERT INTO TEST (name, status_id, method_name, project_id, " +
            "session_id, start_time, end_time, env, browser, author_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE TEST SET " +
            "name = ?, " +
            "status_id = ?, " +
            "method_name = ?, " +
            "project_id = ?, " +
            "session_id = ?, " +
            "start_time = ?, " +
            "end_time = ?, " +
            "env = ?, " +
            "browser = ?, " +
            "author_id = ? " +
            "WHERE id = ?";

    private static final String SQL_DELETE = "DELETE FROM TEST WHERE id = ?";

    @Override
    public List<TestTable> getAll() {
        Connection connection = DatabaseConnection.getConnection();
        List<TestTable> testTables = new ArrayList<>();
        ResultSet resultSet;

        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_ATTRIBUTES)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                testTables.add(new TestTable(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        SQLUtils.defaultForeignKeyValuesTestTable(testTables);

        return testTables;
    }

    @Override
    public List<TestTable> get(String request) {
        Connection connection = DatabaseConnection.getConnection();
        List<TestTable> testTables = new ArrayList<>();
        ResultSet resultSet;

        try (PreparedStatement statement = connection.prepareStatement(request)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                testTables.add(new TestTable(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return testTables;
    }

    @Override
    public void insert(TestTable testTable) {
        Connection connection = DatabaseConnection.getConnection();
        List<TestTable> testTables = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
            statement.setString(1, testTable.getName());
            statement.setInt(2, testTable.getStatusId());
            statement.setString(3, testTable.getMethodName());
            statement.setInt(4, testTable.getProjectId());
            statement.setInt(5, testTable.getSessionId());
            statement.setTimestamp(6, testTable.getStartTime());
            statement.setTimestamp(7, testTable.getEndTime());
            statement.setString(8, testTable.getEnv());
            statement.setString(9, testTable.getBrowser());
            statement.setInt(10, testTable.getAuthorId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        testTables.add(testTable);
    }

    @Override
    public void update(TestTable testTable) {
        Connection connection = DatabaseConnection.getConnection();
        List<TestTable> testTables = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
            statement.setString(1, testTable.getName());
            statement.setInt(2, testTable.getStatusId());
            statement.setString(3, testTable.getMethodName());
            statement.setInt(4, testTable.getProjectId());
            statement.setInt(5, testTable.getSessionId());
            statement.setTimestamp(6, testTable.getStartTime());
            statement.setTimestamp(7, testTable.getEndTime());
            statement.setString(8, testTable.getEnv());
            statement.setString(9, testTable.getBrowser());
            statement.setInt(10, testTable.getAuthorId());
            statement.setInt(11, testTable.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        testTables.add(testTable);
    }

    @Override
    public void delete(TestTable testTable) {
        Connection connection = DatabaseConnection.getConnection();
        List<TestTable> testTables = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
            statement.setInt(1, testTable.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        testTables.remove(testTable);
    }
}