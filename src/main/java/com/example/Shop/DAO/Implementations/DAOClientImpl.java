package com.example.Shop.DAO.Implementations;

import com.example.Shop.DAO.DAOClient;
import com.example.Shop.tables.Client;
import com.example.Shop.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class DAOClientImpl implements DAOClient {
    @Override
    public void addClient(Client client) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(client);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateClient(Client client) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(client);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteClient(Client client) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(client);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Client> getClientByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Client> query = session.createQuery("FROM Client WHERE real_name LIKE :thisName", Client.class)
                .setParameter("thisName", "%" + name + "%");
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }

    @Override
    public Client getClientByID(Long Id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Client> query = session.createQuery("FROM Client WHERE client_id = :thisID", Client.class)
                .setParameter("thisID", Id);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList().get(0);
    }

    @Override
    public List<Client> getAllClients() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Client> query = session.createQuery("FROM Client", Client.class);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }
}
