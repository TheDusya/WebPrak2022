package com.example.Shop.DAO.Implementations;

import com.example.Shop.DAO.DAOGood;
import com.example.Shop.tables.TableGood;
import com.example.Shop.tables.tech_type;
import com.example.Shop.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class DAOGoodImpl implements DAOGood {
    @Override
    public void addGood(TableGood good) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(good);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateGood(TableGood good) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(good);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteGood(TableGood good) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(good);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public TableGood getGoodByID(Integer Id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<TableGood> query = session.createQuery("FROM TableGood WHERE good_id = :thisID", TableGood.class)
                .setParameter("thisID", Id);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList().get(0);
    }

    @Override
    public List<TableGood> getAllGoods() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<TableGood> query = session.createQuery("FROM TableGood", TableGood.class);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }

    @Override
    public List<TableGood> getGoodsByCountry(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<TableGood> query = session.createQuery("FROM TableGood WHERE country LIKE :thisCountry", TableGood.class)
                .setParameter("thisCountry", name );
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }

    @Override
    public List<TableGood> getGoodsByManufacturer(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<TableGood> query = session.createQuery("FROM TableGood WHERE manufacturer LIKE :thisManufacturer", TableGood.class)
                .setParameter("thisManufacturer", name );
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }

    @Override
    public List<TableGood> getGoodsByKind(tech_type tech_kind) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<TableGood> query = session.createQuery("FROM TableGood WHERE kind = :thiskind", TableGood.class)
                .setParameter("thiskind", tech_kind);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }
}
