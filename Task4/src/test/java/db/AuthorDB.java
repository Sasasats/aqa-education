package db;

import dao.IDao;
import models.sqlTables.AuthorTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDB implements IDao<AuthorTable> {
    private static final String SQL_GET_ATTRIBUTES = "SELECT * FROM AUTHOR";
    private static final String SQL_INSERT = "INSERT INTO AUTHOR (name, login, email) values (?, ?, ?)";

    @Override
    public List<AuthorTable> getAll() {
        Connection connection = DatabaseConnection.getConnection();
        List<AuthorTable> authorTables = new ArrayList<>();
        ResultSet resultSet;

        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_ATTRIBUTES)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                authorTables.add(new AuthorTable(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authorTables;
    }

    @Override
    public List<AuthorTable> get(String request) {
        return null;
    }

    @Override
    public void insert(AuthorTable authorTable) {
        Connection connection = DatabaseConnection.getConnection();
        List<AuthorTable> authorTables = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
            statement.setString(1, authorTable.getName());
            statement.setString(2, authorTable.getLogin());
            statement.setString(3, authorTable.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        authorTables.add(authorTable);
    }

    @Override
    public void update(AuthorTable authorTable) {

    }

    @Override
    public void delete(AuthorTable authorTable) {

    }
}
