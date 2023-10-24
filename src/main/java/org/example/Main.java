package org.example;

import org.example.repository.ClientService;
import org.example.database.Database;
import org.example.entities.Client;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        Connection connection = Database.getInstance().getConnection();
        ClientService clientService = new ClientService(connection);

        System.out.println("create = " + clientService.create("Mark Avreliy"));
        System.out.println("getById = " + clientService.getById(3));
        clientService.setName(5, "Platon");
        clientService.deleteById(3);
        System.out.println("create = " + clientService.create("Karl Marks"));
        List<Client> clients = clientService.listAll();

        connection.close();

        for (Client client : clients) {
            System.out.println("client = " + client);
        }
    }
}