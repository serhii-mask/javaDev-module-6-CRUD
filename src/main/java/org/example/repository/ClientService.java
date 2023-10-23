package org.example.repository;

import org.example.entities.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.example.utils.Validator.validateName;

public class ClientService {

    private static final String INSERN_STRING = "INSERT INTO client (name) VALUES (?)";
    private static final String SELECT_MAX_ID_STRING = "SELECT max(id) AS maxId FROM client";
    private static final String SELECT_BY_ID_STRING = "SELECT name FROM client WHERE id = ?";
    private static final String UPDATE_USER_NAME_STRING = "UPDATE client SET name = ? WHERE id = ?";
    private static final String DELETE_USER_BY_ID_STRING = "DELETE FROM client WHERE id = ?";
    private static final String SELECT_ALL_USERS_STRING = "SELECT id, name FROM CLIENT";

    private PreparedStatement insertStatement;
    private PreparedStatement selectMaxIdStatement;
    private PreparedStatement selectByIdStatement;
    private PreparedStatement updateUserNameStatement;
    private PreparedStatement deleteUserByIdStatement;
    private PreparedStatement selectAllUsersStatement;


    public ClientService(Connection connection) {
        try {
            insertStatement = connection.prepareStatement(INSERN_STRING);
            selectMaxIdStatement = connection.prepareStatement(SELECT_MAX_ID_STRING);
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

            try (ResultSet resultSet = selectMaxIdStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getLong("maxId");
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
                throwClientNotFoundException(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(long id) {
        try {
            deleteUserByIdStatement.setLong(1, id);
            int result = deleteUserByIdStatement.executeUpdate();
            if (result == 0) {
                throwClientNotFoundException(id);
            }
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

    private void throwClientNotFoundException(long id) {
        throw new IllegalArgumentException("Client with ID " + id + " not found");
    }
}