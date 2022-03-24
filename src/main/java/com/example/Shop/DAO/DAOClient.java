package com.example.Shop.DAO;

import com.example.Shop.tables.TableClient;
import com.example.Shop.tables.tech_type;

import java.util.List;

public interface DAOClient {
    void addClient(TableClient client);
    void updateClient(TableClient client);
    void deleteClient(TableClient client);

    TableClient getClientByID (Integer client_ID);
    List<TableClient> getClientByName (String name);
    List<TableClient> getAllClients ();
    //List<TableClient> getClientsByCity (String City);
}
