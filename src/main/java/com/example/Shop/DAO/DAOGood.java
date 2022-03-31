package com.example.Shop.DAO;

import com.example.Shop.tables.Good;
import com.example.Shop.types.tech_type;

import java.util.List;

public interface DAOGood {
    void addGood(Good good);
    void updateGood(Good good);
    void deleteGood(Good good);

    Good getGoodByID (Long good_ID);
    Good getGoodByID (int good_ID);
    List<Good> getAllGoods ();
    List<Good> getGoodsByKind (List<Good> from, tech_type kind);
    List<Good> getGoodsByManufacturer (List<Good> from, String manufacturer);
    List<Good> getGoodsByCountry (List<Good> from, String country);
    List<Good> getGoodsInStock (List<Good> from);
    List<Good> getGoodsPriceBetween (List<Good> from, Integer min, Integer max);
    List<Good> getByFilter (List<Good> from, String filter, String text);
    List<Good> getByFilter (List<Good> from, String filter, float dec);
    List<Good> getByFilter (List<Good> from, String filter, boolean b);
}
