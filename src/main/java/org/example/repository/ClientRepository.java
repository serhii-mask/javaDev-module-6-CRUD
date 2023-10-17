package org.example.repository;

import org.example.entities.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientRepository {
    private static final String PREPARED_STATEMENT_INSERT = "INSERT INTO client (name) VALUES (?)";
    private PreparedStatement insertStatement;

    public ClientRepository(Connection connection) {
        try {
            this.insertStatement = connection.prepareStatement(PREPARED_STATEMENT_INSERT);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveClient(Client client) {
        try {
            this.insertStatement.setString(1, client.getName());
            this.insertStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
