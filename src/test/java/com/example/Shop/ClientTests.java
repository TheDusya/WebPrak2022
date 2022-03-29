package com.example.Shop;

import com.example.Shop.DAO.DAOClient;
import com.example.Shop.DAO.Factory.DAOFactory;
import com.example.Shop.tables.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTests {

    @Test
    public void testSomeClient() {
        DAOClient dao = DAOFactory.getInstance().getCDAO();
        Client client = dao.getClientByID(Long.valueOf(3));
        assertNotEquals(client, null);
    }

    @Test
    public void DontGetSomeClient() {
        boolean alright = true;
        DAOClient dao = DAOFactory.getInstance().getCDAO();
        Client client = dao.getClientByID(Long.valueOf(345));
        assertEquals(client, null);
    }

    @Test
    public void GetNameByLogin() {
        DAOClient dao = DAOFactory.getInstance().getCDAO();
        Client client = dao.getClientByLogin("Pushkin");
        assertEquals(client.getReal_name(), "Пушкин Александр Сергеевич");
    }

    @Test
    public void AddingClient() {
        boolean alright = true;
        try {
            Client client = new Client("Human", "123456", false);
            client.setReal_name("Фыва Олдж");
            DAOClient dao = DAOFactory.getInstance().getCDAO();
            if (dao.getClientByLogin(client.getLogin())==null) dao.addClient(client);
            else System.out.println("aaa");
        }
        catch (Exception e){
            alright = false;
        }
        assertEquals(alright, true);
    }
}
