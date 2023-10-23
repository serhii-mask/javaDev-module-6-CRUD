package org.example.database;

import org.example.props.Props;
import org.flywaydb.core.Flyway;

import java.sql.*;

public class Database {
    private static final Database INSTANCE = new Database();

    private Connection connection;

    private Database() {
        String connectionUrl = Props.getConnectionUrl();

        try {
            this.connection = DriverManager.getConnection(connectionUrl);
            flywayMigration(connectionUrl);
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

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void  flywayMigration(String url) {
        Flyway flyway = Flyway.configure().dataSource(url, null, null).load();

        flyway.migrate();
    }
}
