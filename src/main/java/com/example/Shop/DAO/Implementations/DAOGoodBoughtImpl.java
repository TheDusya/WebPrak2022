package com.example.Shop.DAO.Implementations;

import com.example.Shop.DAO.DAOGood;
import com.example.Shop.DAO.DAOGoodBought;
import com.example.Shop.DAO.Factory.DAOFactory;
import com.example.Shop.tables.Good;
import com.example.Shop.tables.GoodBought;
import com.example.Shop.tables.Request;
import com.example.Shop.util.HibernateUtil;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

@Getter
public class DAOGoodBoughtImpl implements DAOGoodBought {

    @Override
    public void addGoodBought(GoodBought goodBought) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(goodBought);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateGoodBought(GoodBought goodBought) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(goodBought);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteGoodBought(GoodBought goodBought) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(goodBought);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public GoodBought getGoodBoughtByID(Long Id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<GoodBought> query = session.createQuery("FROM GoodBought WHERE good_bought_id = :thisID", GoodBought.class)
                .setParameter("thisID", Id);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList().get(0);
    }

    @Override
    public List<GoodBought> getAllGoodsBought() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<GoodBought> query = session.createQuery("FROM GoodBought", GoodBought.class);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }

    @Override
    public List<GoodBought> getGoodsBoughtByGood(Good good) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<GoodBought> query = session.createQuery("FROM GoodBought WHERE good.good_id = :thisID", GoodBought.class)
                .setParameter("thisID", good.getGood_id());
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }

    @Override
    public List<GoodBought> getGoodsBoughtByRequest(Request request) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<GoodBought> query = session.createQuery("FROM GoodBought WHERE request.request_id = :thisID", GoodBought.class)
                .setParameter("thisID", request.getRequest_id());
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }
    
    @Override
    public Integer sumCosts(List<GoodBought> list){
        DAOGood dao = DAOFactory.getInstance().getGDAO();
        Integer ans = 0;
        for (GoodBought goodBought : list) {
            Good good = goodBought.getGood();
            ans+= goodBought.getAmount()*good.getPrice();
        }
        return ans;
    }

}
