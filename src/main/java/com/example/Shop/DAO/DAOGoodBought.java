package com.example.Shop.DAO;

import com.example.Shop.tables.Good;
import com.example.Shop.tables.GoodBought;
import com.example.Shop.tables.Request;

import java.util.List;

public interface DAOGoodBought {
    void addGoodBought(GoodBought goodBought);
    void updateGoodBought(GoodBought goodBought);
    void deleteGoodBought(GoodBought goodBought);

    GoodBought getGoodBoughtByID (Long goodBought_ID);
    List<GoodBought> getAllGoodsBought ();
    List<GoodBought> getGoodsBoughtByGood (Good good);
    List<GoodBought> getGoodsBoughtByRequest (Request request);
    Integer sumCosts (List<GoodBought> list);
}
