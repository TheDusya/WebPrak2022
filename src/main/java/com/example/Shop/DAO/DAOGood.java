package com.example.Shop.DAO;

import com.example.Shop.tables.Good;
import com.example.Shop.types.tech_type;

import java.util.List;

public interface DAOGood {
    void addGood(Good good);
    void updateGood(Good good);
    void deleteGood(Good good);

    Good getGoodByID (Long good_ID);
    List<Good> getAllGoods ();
    List<Good> getGoodsByKind (tech_type kind);
    List<Good> getGoodsByManufacturer (String manufacturer);
    List<Good> getGoodsByCountry (String country);
}
