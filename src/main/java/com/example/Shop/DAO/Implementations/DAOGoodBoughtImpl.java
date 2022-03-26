package com.example.Shop.DAO.Implementations;

import com.example.Shop.DAO.DAOGoodBought;
import com.example.Shop.tables.GoodBought;
import com.example.Shop.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

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
    public List<GoodBought> getGoodsBoughtByGoodID(Long Id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<GoodBought> query = session.createQuery("FROM GoodBought WHERE good_id = :thisID", GoodBought.class)
                .setParameter("thisID", Id);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }

    @Override
    public List<GoodBought> getGoodsBoughtByRequestID(Long Id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<GoodBought> query = session.createQuery("FROM GoodBought WHERE request_id = :thisID", GoodBought.class)
                .setParameter("thisID", Id);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }

}
