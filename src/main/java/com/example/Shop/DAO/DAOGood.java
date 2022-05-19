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
    List<Good> getGoodsByKind (List<Good> from, String kind);
    List<Good> getGoodsByKind (String kind);
    List<Good> getGoodsByManufacturer (List<Good> from, String manufacturer);
    List<Good> getGoodsByManufacturer (String manufacturer);
    List<Good> getGoodsByCountry (List<Good> from, String country);
    List<Good> getGoodsByCountry (String country);
    List<Good> getGoodsInStock (List<Good> from);
    List<Good> getGoodsInStock ();
    List<Good> getGoodsPriceBetween (List<Good> from, Integer min, Integer max);
    List<Good> getGoodsPriceBetween (Integer min, Integer max);
    List<Good> getByFilter (List<Good> from, String filter, String text) throws Exception;
    List<Good> getByFilter (String filter, String text) throws Exception;
    List<Good> getByFilter (List<Good> from, String filter, float dec) throws Exception;
    List<Good> getByFilter (String filter, float dec) throws Exception;
    List<Good> getByFilter (List<Good> from, String filter, boolean b) throws Exception;
    List<Good> getByFilter (String filter, boolean b) throws Exception;
    List<Good> searchGoods (String filter);
}
