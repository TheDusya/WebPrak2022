package com.example.Shop.DAO.Factory;

import com.example.Shop.DAO.Implementations.DAOClientImpl;
import com.example.Shop.DAO.Implementations.DAOGoodBoughtImpl;
import com.example.Shop.DAO.Implementations.DAOGoodImpl;
import com.example.Shop.DAO.DAOClient;
import com.example.Shop.DAO.DAOGood;
import com.example.Shop.DAO.DAOGoodBought;
import com.example.Shop.DAO.DAORequest;
import com.example.Shop.DAO.Implementations.DAORequestImpl;

public class DAOFactory {
    private static DAOClient CDAO = null;
    private static DAOGoodBought GBDAO = null;
    private static DAOGood GDAO = null;
    private static DAORequest RDAO = null;
    private static DAOFactory instance = null;

    public static synchronized DAOFactory getInstance(){
        if (instance == null){
            instance = new DAOFactory();
        }
        return instance;
    }
    
    public DAOClient getCDAO(){
        if (CDAO == null){
            CDAO = new DAOClientImpl();
        }
        return CDAO;
    }

    public DAOGood getGDAO(){
        if (GDAO == null){
            GDAO = new DAOGoodImpl();
        }
        return GDAO;
    }

    public DAOGoodBought getGBDAO(){
        if (GBDAO == null){
            GBDAO = new DAOGoodBoughtImpl();
        }
        return GBDAO;
    }

    public DAORequest getRDAO(){
        if (RDAO == null){
            RDAO = new DAORequestImpl();
        }
        return RDAO;
    }
}
