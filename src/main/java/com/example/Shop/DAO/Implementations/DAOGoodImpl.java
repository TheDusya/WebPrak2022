package com.example.Shop.DAO.Implementations;

import com.example.Shop.DAO.DAOGood;
import com.example.Shop.tables.Good;
import com.example.Shop.types.tech_type;
import com.example.Shop.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class DAOGoodImpl implements DAOGood {
    @Override
    public void addGood(Good good) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(good);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateGood(Good good) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(good);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteGood(Good good) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(good);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Good getGoodByID(Long Id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Good> query = session.createQuery("FROM Good WHERE good_id = :thisID", Good.class)
                .setParameter("thisID", Id);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList().get(0);
    }

    @Override
    public List<Good> getAllGoods() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Good> query = session.createQuery("FROM Good", Good.class);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }

    @Override
    public List<Good> getGoodsByCountry(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Good> query = session.createQuery("FROM Good WHERE country LIKE :thisCountry", Good.class)
                .setParameter("thisCountry", name );
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }

    @Override
    public List<Good> getGoodsByManufacturer(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Good> query = session.createQuery("FROM Good WHERE manufacturer LIKE :thisManufacturer", Good.class)
                .setParameter("thisManufacturer", name );
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }

    @Override
    public List<Good> getGoodsByKind(tech_type tech_kind) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Good> query = session.createQuery("FROM Good WHERE kind = :thiskind", Good.class)
                .setParameter("thiskind", tech_kind);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }
}
