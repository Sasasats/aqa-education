package db;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import utils.enums.DBValue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final ISettingsFile DB_SETTINGS = new JsonSettingsFile("dbSettings.json");

    private static Connection connection;

    public DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(DB_SETTINGS.getValue(DBValue.URI.getValue()).toString(),
                    DB_SETTINGS.getValue(DBValue.USER.getValue()).toString(),
                    DB_SETTINGS.getValue(DBValue.PASSWORD.getValue()).toString());
            if (connection != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.format("SQL state: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            new DatabaseConnection();
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}