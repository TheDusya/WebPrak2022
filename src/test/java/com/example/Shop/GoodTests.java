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
    public void TestFilter () throws Exception {
        boolean alright = true;
        DAOGood dao = DAOFactory.getInstance().getGDAO();
        List<Good> list = dao.getByFilter("Количество камер", 3);
        List<Good>realList = List.of(dao.getGoodByID(12));
        assertEquals(list, realList);
        assertEquals(alright, true);
    }

    @Test
    public void TestAddGood() {
        Good good = new Good("Qwerty", "UIOP", "Фен", 2357, 98);
        DAOGood dao = DAOFactory.getInstance().getGDAO();
        int size1 = dao.getAllGoods().size();
        dao.addGood(good);
        assertEquals(size1+1, dao.getAllGoods().size());
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
        Long someID = Long.valueOf(57);
        DAOGood dao = DAOFactory.getInstance().getGDAO();
        Good good = dao.getGoodByID(someID);
        if (good!=null) dao.deleteGood(good);
        assertEquals(dao.getGoodByID(someID), null);
    }

}
