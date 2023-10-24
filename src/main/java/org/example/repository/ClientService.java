package org.example.repository;

import org.example.entities.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.utils.Validator.validateName;

public class ClientService {

    private static final String INSERN_STRING = "INSERT INTO client (name) VALUES (?)";
    private static final String SELECT_BY_ID_STRING = "SELECT name FROM client WHERE id = ?";
    private static final String UPDATE_USER_NAME_STRING = "UPDATE client SET name = ? WHERE id = ?";
    private static final String DELETE_USER_BY_ID_STRING = """
            SET REFERENTIAL_INTEGRITY FALSE;
            BEGIN TRANSACTION;
            DELETE FROM client
               WHERE id IN (SELECT id FROM project WHERE id = ?);
            DELETE FROM project WHERE client_id = ?;
            SET REFERENTIAL_INTEGRITY TRUE;
            """;
    private static final String SELECT_ALL_USERS_STRING = "SELECT id, name FROM CLIENT";

    private PreparedStatement insertStatement;
    private PreparedStatement selectByIdStatement;
    private PreparedStatement updateUserNameStatement;
    private PreparedStatement deleteUserByIdStatement;
    private PreparedStatement selectAllUsersStatement;


    public ClientService(Connection connection) {
        try {
            insertStatement = connection.prepareStatement(INSERN_STRING, Statement.RETURN_GENERATED_KEYS);
            selectByIdStatement = connection.prepareStatement(SELECT_BY_ID_STRING);
            updateUserNameStatement = connection.prepareStatement(UPDATE_USER_NAME_STRING);
            deleteUserByIdStatement = connection.prepareStatement(DELETE_USER_BY_ID_STRING);
            selectAllUsersStatement = connection.prepareStatement(SELECT_ALL_USERS_STRING);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long create(String name) {
        validateName(name);

        try {
            insertStatement.setString(1, name);
            insertStatement.executeUpdate();

            try (ResultSet generatedKeys = insertStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public String getById(long id) {
        try {
            this.selectByIdStatement.setLong(1, id);
            try (ResultSet resultSet = selectByIdStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new IllegalArgumentException("Client with ID " + id + " not found");
    }

    public void setName(long id, String name) {
        validateName(name);

        try {
            updateUserNameStatement.setString(1, name);
            updateUserNameStatement.setLong(2, id);

            int result = updateUserNameStatement.executeUpdate();
            if (result == 0) {
                throw new IllegalArgumentException("Client with ID " + id + " not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(long id) {
        try {
            deleteUserByIdStatement.setLong(1, id);
            deleteUserByIdStatement.setLong(2, id);
            deleteUserByIdStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//
    public List<Client> listAll() {
        List<Client> clients = new ArrayList<>();

        try (ResultSet resultSet = selectAllUsersStatement.executeQuery()) {
            while (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getLong("id"));
                client.setName(resultSet.getString("name"));
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }
}