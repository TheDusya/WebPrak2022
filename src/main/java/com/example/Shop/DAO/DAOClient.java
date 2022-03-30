package com.example.Shop.DAO;

import com.example.Shop.tables.Client;

import java.util.List;

public interface DAOClient {
    void addClient(Client client);
    void updateClient(Client client);
    void deleteClient(Client client);

    Client getClientByID (Long client_ID);
    Client getClientByLogin (String name);
    List<Client> getClientsByName (String name);
    List<Client> getAllClients ();
    List<Client> areAdmins (boolean param);
    String getCity (Client client);
}
