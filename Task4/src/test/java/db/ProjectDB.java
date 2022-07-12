package db;

import dao.IDao;
import models.sqlTables.ProjectTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectDB implements IDao<ProjectTable> {
    private static final String SQL_GET_ATTRIBUTES = "SELECT * FROM PROJECT";
    private static final String SQL_INSERT = "INSERT INTO PROJECT (name) values (?)";

    @Override
    public List<ProjectTable> getAll() {
        Connection connection = DatabaseConnection.getConnection();
        List<ProjectTable> projectTables = new ArrayList<>();
        ResultSet resultSet;

        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_ATTRIBUTES)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                projectTables.add(new ProjectTable(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projectTables;
    }

    @Override
    public List<ProjectTable> get(String request) {
        return null;
    }

    @Override
    public void insert(ProjectTable projectTable) {
        Connection connection = DatabaseConnection.getConnection();
        List<ProjectTable> projectTables = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
            statement.setString(1, projectTable.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        projectTables.add(projectTable);
    }

    @Override
    public void update(ProjectTable projectTable) {

    }

    @Override
    public void delete(ProjectTable projectTable) {

    }
}
