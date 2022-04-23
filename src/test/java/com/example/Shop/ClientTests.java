package com.example.Shop;

import com.example.Shop.DAO.DAOClient;
import com.example.Shop.DAO.Factory.DAOFactory;
import com.example.Shop.tables.Client;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTests {

    @Test
    public void TestGetClientByID() {
        DAOClient dao = DAOFactory.getInstance().getCDAO();
        Client client = dao.getClientByID(Long.valueOf(3));
        assertNotEquals(client, null);
    }

    @Test
    public void TestDontGetClientByID() {
        boolean alright = true;
        DAOClient dao = DAOFactory.getInstance().getCDAO();
        Client client = dao.getClientByID(Long.valueOf(345));
        assertEquals(client, null);
    }

    @Test
    public void TestGetNameByLogin() {
        DAOClient dao = DAOFactory.getInstance().getCDAO();
        Client client = dao.getClientByLogin("Pushkin");
        assertEquals(client.getReal_name(), "Пушкин Александр Сергеевич");
    }

    @Test
    public void TestGetClientsByName (){
        String someName="Иванов Иван";
        DAOClient dao = DAOFactory.getInstance().getCDAO();
        List<Client> list = dao.getClientsByName(someName);
        assertEquals(list, Arrays.asList(dao.getClientByLogin("Vanya_2002")));
    }

    @Test
    public void TestGetAdmins (){
        DAOClient dao = DAOFactory.getInstance().getCDAO();
        List<Client> list = dao.areAdmins(true);
        List realList = Arrays.asList(dao.getClientByID(Long.valueOf(1)), dao.getClientByID(Long.valueOf(4)));
        assertEquals(list, realList);
    }

    @Test
    public void TestGetCity () {
        String somelLogin = "MrCharms";
        String realCity = "Санкт-Петербург";
        DAOClient dao = DAOFactory.getInstance().getCDAO();
        assertEquals(dao.getCity(dao.getClientByLogin(somelLogin)), realCity);
    }

    @Test
    public void TestAddClient() {
        boolean alright = true;
        Client client = new Client("Human2", "123456", false);
        client.setReal_name("Фыва Олдж");
        DAOClient dao = DAOFactory.getInstance().getCDAO();
        if (dao.getClientByLogin(client.getLogin())==null) dao.addClient(client);
        assertNotEquals(dao.getClientByLogin("Human2"), null);
    }

    @Test
    public void TestDeleteClient() {
        DAOClient dao = DAOFactory.getInstance().getCDAO();
        Client client = dao.getClientByLogin("Human2");
        boolean alright = true;
        if (client!=null) dao.deleteClient(client);
        assertEquals(dao.getClientByLogin("Human2"), null);
    }

    @Test
    public void TestUpdateClient() {
        String someAddress = "страна: Россия, город: Самара, улица: Заречная, дом: 28";
        String login = "A";
        DAOClient dao = DAOFactory.getInstance().getCDAO();
        Client client = dao.getClientByLogin(login);
        client.setAddress(someAddress);
        dao.updateClient(client);
        assertEquals(client.getAddress(), someAddress);
    }

}
