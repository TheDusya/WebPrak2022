package com.example.Shop;

import com.example.Shop.DAO.DAOGood;
import com.example.Shop.DAO.DAOGoodBought;
import com.example.Shop.DAO.Factory.DAOFactory;
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
        boolean b = list.equals(realList);
        boolean c = list.get(0).equals(realList.get(0));
        assertEquals(list, realList);
    }

    @Test
    public void TestGetSumCosts (){
        
    }
}
