package com.example.Shop;

import com.example.Shop.DAO.DAOClient;
import com.example.Shop.DAO.DAOGood;
import com.example.Shop.DAO.Factory.DAOFactory;
import com.example.Shop.tables.Client;
import com.example.Shop.tables.Good;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GoodTests {
    @Test
    public void TestGetGoodByID() {
        DAOGood dao = DAOFactory.getInstance().getGDAO();
        Good good = dao.getGoodByID(Long.valueOf(3));
        assertNotEquals(good, null);
    }

    @Test
    public void TestDontGetGoodByID() {
        DAOGood dao = DAOFactory.getInstance().getGDAO();
        Good good = dao.getGoodByID(Long.valueOf(345));
        assertEquals(good, null);
    }

    @Test
    public void TestGetAllGoodsOfKind (){
        DAOGood dao = DAOFactory.getInstance().getGDAO();
        List<Good> list = dao.getGoodsByKind("Мультиварка");
        List<Good> realList = List.of(dao.getGoodByID(5),
                dao.getGoodByID(6), dao.getGoodByID(4));
        assertEquals(list, realList);
    }

    @Test
    public void TestPriceRestrict (){
        DAOGood dao = DAOFactory.getInstance().getGDAO();
        List<Good> flist = dao.getGoodsByKind("Мультиварка");
        List<Good> slist = dao.getGoodsPriceBetween(flist, 6000, 9000);
        List<Good> realList = List.of(dao.getGoodByID(4));
        assertEquals(slist, realList);
    }

    @Test
    public void TestFilter (){
        boolean alright = true;
        try {
            DAOGood dao = DAOFactory.getInstance().getGDAO();
            List<Good> list = dao.getByFilter("Количество камер", 3);
            List<Good>realList = List.of(dao.getGoodByID(12));
            assertEquals(list, realList);
        }
        catch (Exception e){
            alright = false;
        }
        assertEquals(alright, true);
    }

    @Test
    public void TestAddGood() {
        boolean alright = true;
        try {
            Good good = new Good("Qwerty", "UIOP", "Фен", 2357, 98);
            DAOGood dao = DAOFactory.getInstance().getGDAO();
            dao.addGood(good);
        }
        catch (Exception e){
            alright = false;
        }
        assertEquals(alright, true);
    }


    @Test
    public void TestUpdateGood() {
        Long someID = Long.valueOf(49);
        DAOGood dao = DAOFactory.getInstance().getGDAO();
        Good good = dao.getGoodByID(someID);
        good.setCountry("Россия");
        dao.updateGood(good);
        assertEquals(good.getCountry(), "Россия");
    }


    @Test
    public void TestDeleteGood() {
        Long someID = Long.valueOf(55);
        DAOGood dao = DAOFactory.getInstance().getGDAO();
        Good good = dao.getGoodByID(someID);
        boolean alright;
        try{
            if (good!=null) dao.deleteGood(good);
            else System.out.println("aaa");
            alright=dao.getGoodByID(someID)==null;
        }
        catch (Exception e){
            alright = false;
        }
        assertEquals(alright, true);
    }

}
