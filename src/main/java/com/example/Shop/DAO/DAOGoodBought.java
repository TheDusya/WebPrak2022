package com.example.Shop.DAO;

import com.example.Shop.tables.GoodBought;

import java.util.List;

public interface DAOGoodBought {
    void addGoodBought(GoodBought goodBought);
    void updateGoodBought(GoodBought goodBought);
    void deleteGoodBought(GoodBought goodBought);

    GoodBought getGoodBoughtByID (Long goodBought_ID);
    List<GoodBought> getAllGoodsBought ();
    List<GoodBought> getGoodsBoughtByGoodID (Long good_id);
    List<GoodBought> getGoodsBoughtByRequestID (Long request_id);
    Integer sumCosts (List<GoodBought> list);
}
