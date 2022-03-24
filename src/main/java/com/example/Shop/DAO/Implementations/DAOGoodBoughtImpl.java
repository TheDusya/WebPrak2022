package com.example.Shop.DAO.Implementations;

import com.example.Shop.DAO.DAOGoodBought;
import com.example.Shop.tables.TableGoodBought;
import com.example.Shop.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class DAOGoodBoughtImpl implements DAOGoodBought {

    @Override
    public void addGoodBought(TableGoodBought goodBought) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(goodBought);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateGoodBought(TableGoodBought goodBought) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(goodBought);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteGoodBought(TableGoodBought goodBought) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(goodBought);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public TableGoodBought getGoodBoughtByID(Integer Id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<TableGoodBought> query = session.createQuery("FROM TableGoodBought WHERE good_bought_id = :thisID", TableGoodBought.class)
                .setParameter("thisID", Id);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList().get(0);
    }

    @Override
    public List<TableGoodBought> getAllGoodsBought() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<TableGoodBought> query = session.createQuery("FROM TableGoodBought", TableGoodBought.class);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }

    @Override
    public List<TableGoodBought> getGoodsBoughtByGoodID(Integer Id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<TableGoodBought> query = session.createQuery("FROM TableGoodBought WHERE good_id = :thisID", TableGoodBought.class)
                .setParameter("thisID", Id);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }

    @Override
    public List<TableGoodBought> getGoodsBoughtByRequestID(Integer Id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<TableGoodBought> query = session.createQuery("FROM TableGoodBought WHERE request_id = :thisID", TableGoodBought.class)
                .setParameter("thisID", Id);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }

}
