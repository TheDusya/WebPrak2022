package com.example.Shop.DAO.Implementations;

import com.example.Shop.DAO.DAOClient;
import com.example.Shop.tables.TableClient;
import com.example.Shop.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class DAOClientImpl implements DAOClient {

    @Override
    public void addClient(TableClient client) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(client);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateClient(TableClient client) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(client);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteClient(TableClient client) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(client);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<TableClient> getClientByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<TableClient> query = session.createQuery("FROM TableClient WHERE real_name LIKE :thisName", TableClient.class)
                .setParameter("thisName", "%" + name + "%");
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }

    @Override
    public TableClient getClientByID(Integer Id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<TableClient> query = session.createQuery("FROM TableClient WHERE client_id = :thisID", TableClient.class)
                .setParameter("thisID", Id);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList().get(0);
    }

    @Override
    public List<TableClient> getAllClients() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<TableClient> query = session.createQuery("FROM TableClient", TableClient.class);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }
}
