package com.example.Shop.DAO;

import com.example.Shop.tables.TableGoodBought;
import com.example.Shop.tables.tech_type;

import java.util.List;

public interface DAOGoodBought {
    void addGoodBought(TableGoodBought goodBought);
    void updateGoodBought(TableGoodBought goodBought);
    void deleteGoodBought(TableGoodBought goodBought);

    TableGoodBought getGoodBoughtByID (Integer goodBought_ID);
    List<TableGoodBought> getAllGoodsBought ();
    List<TableGoodBought> getGoodsBoughtByGoodID (Integer good_id);
    List<TableGoodBought> getGoodsBoughtByRequestID (Integer request_id);
}
