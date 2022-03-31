package com.example.Shop;

import com.example.Shop.DAO.DAOClient;
import com.example.Shop.DAO.DAOGood;
import com.example.Shop.DAO.DAOGoodBought;
import com.example.Shop.DAO.DAORequest;
import com.example.Shop.DAO.Factory.DAOFactory;
import com.example.Shop.DAO.Implementations.DAOClientImpl;
import com.example.Shop.tables.Client;
import com.example.Shop.tables.Good;
import com.example.Shop.tables.GoodBought;
import com.example.Shop.tables.Request;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyTests {

    @Test
    public void testSomeGoodBought() {
        DAOGoodBought dao = DAOFactory.getInstance().getGBDAO();
        GoodBought goodBought = dao.getGoodBoughtByID(Long.valueOf(3));
        System.out.println(goodBought);
    }

    @Test
    public void testSomeRequest() {
        DAORequest dao = DAOFactory.getInstance().getRDAO();
        Request request = dao.getRequestByID(Long.valueOf(1));
        System.out.println(request);
    }
}
