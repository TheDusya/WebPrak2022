package com.example.Shop.DAO;

import com.example.Shop.tables.Client;

import java.util.List;

public interface DAOClient {
    void addClient(Client client);
    void updateClient(Client client);
    void deleteClient(Client client);

    Client getClientByID (Long client_ID);
    List<Client> getClientByName (String name);
    List<Client> getAllClients ();
    //List<TableClient> getClientsByCity (String City);
}
