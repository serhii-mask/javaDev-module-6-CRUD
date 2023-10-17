package org.example.database;

import org.example.props.Props;

import java.sql.*;

public class Database {
    private static final Database INSTANCE = new Database();

    private Connection connection;

    private Database() {
        String connectionUrl = Props.getConnectionUrl();

        try {
            connection = DriverManager.getConnection(connectionUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() throws SQLException {
        if (connection == null) {
            String connectionUrl = Props.getConnectionUrl();
            connection = DriverManager.getConnection(connectionUrl);
        }
        return connection;
    }

    public int initDB(String sql) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return -1;
        }
    }

    public int executeUpdate(String sql) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return -1;
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
