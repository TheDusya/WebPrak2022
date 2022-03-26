package com.example.Shop;

import com.example.Shop.DAO.DAOClient;
import com.example.Shop.DAO.Factory.DAOFactory;
import com.example.Shop.DAO.Implementations.DAOClientImpl;
import com.example.Shop.tables.Client;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyTests {
    @Test
    public void test() {
        DAOClient dao = DAOFactory.getInstance().getCDAO();
        Client client = dao.getClientByID(Long.valueOf(2));
        System.out.println(client);
    }

}
