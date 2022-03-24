package com.example.Shop.DAO;

import com.example.Shop.tables.TableGood;
import com.example.Shop.tables.tech_type;

import java.util.List;

public interface DAOGood {
    void addGood(TableGood good);
    void updateGood(TableGood good);
    void deleteGood(TableGood good);

    TableGood getGoodByID (Integer good_ID);
    List<TableGood> getAllGoods ();
    List<TableGood> getGoodsByKind (tech_type kind);
    List<TableGood> getGoodsByManufacturer (String manufacturer);
    List<TableGood> getGoodsByCountry (String country);
}
