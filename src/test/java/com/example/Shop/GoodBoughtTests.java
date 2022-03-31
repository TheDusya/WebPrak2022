package com.example.Shop;

import com.example.Shop.DAO.DAOClient;
import com.example.Shop.DAO.DAOGood;
import com.example.Shop.DAO.DAOGoodBought;
import com.example.Shop.DAO.DAORequest;
import com.example.Shop.DAO.Factory.DAOFactory;
import com.example.Shop.tables.Client;
import com.example.Shop.tables.Good;
import com.example.Shop.tables.GoodBought;
import com.example.Shop.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GoodBoughtTests {
    @Test
    public void TestGetGoodsBoughtByGood () {
        DAOGood dao = DAOFactory.getInstance().getGDAO();
        Good good = dao.getGoodByID(Long.valueOf(1));
        DAOGoodBought dao2 = DAOFactory.getInstance().getGBDAO();
        List<GoodBought> list = dao2.getGoodsBoughtByGood(good);
        List<GoodBought> realList = List.of(dao2.getGoodBoughtByID(Long.valueOf(2)));
        assertEquals(list, realList);
    }

    @Test
    public void TestAddGoodBought() {
        boolean alright = true;
        try {
            DAOGood dao = DAOFactory.getInstance().getGDAO();
            DAORequest dao2 = DAOFactory.getInstance().getRDAO();
            GoodBought goodBought = new GoodBought(dao2.getRequestByID(2), dao.getGoodByID(15), 7);
            DAOGoodBought dao3 = DAOFactory.getInstance().getGBDAO();
            dao3.addGoodBought(goodBought);
        }
        catch (Exception e){
            alright = false;
        }
        assertEquals(alright, true);
    }

    @Test
    public void TestDontAddGoodBought() {
        boolean alright = true;
        try {
            DAOGood dao = DAOFactory.getInstance().getGDAO();
            DAORequest dao2 = DAOFactory.getInstance().getRDAO();
            GoodBought goodBought = new GoodBought(dao2.getRequestByID(2678), dao.getGoodByID(15), 7);
            DAOGoodBought dao3 = DAOFactory.getInstance().getGBDAO();
            dao3.addGoodBought(goodBought);
        }
        catch (Exception e){
            alright = false;
        }
        assertEquals(alright, false);
    }


    @Test
    public void TestUpdateGoodBought() {
        boolean alright = true;
        Long id = Long.valueOf(7);
        try {
            DAOGoodBought dao3 = DAOFactory.getInstance().getGBDAO();
            GoodBought goodBought = dao3.getGoodBoughtByID(id);
            goodBought.setAmount(5);
            dao3.updateGoodBought(goodBought);
            alright = (dao3.getGoodBoughtByID(id).getAmount()==5);
        }
        catch (Exception e){
            alright = false;
        }
        assertEquals(alright, true);
    }

    @Test
    public void TestDeleteGoodBought() {
        boolean alright = true;
        try {
            DAOGoodBought dao3 = DAOFactory.getInstance().getGBDAO();
            GoodBought goodBought = dao3.getGoodBoughtByID(7);
            dao3.deleteGoodBought(goodBought);
        }
        catch (Exception e){
            alright = false;
        }
        assertEquals(alright, true);
    }

}
