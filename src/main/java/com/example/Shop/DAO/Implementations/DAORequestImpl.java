package com.example.Shop.DAO.Implementations;

import com.example.Shop.DAO.DAORequest;
import com.example.Shop.tables.TableRequest;
import com.example.Shop.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class DAORequestImpl implements DAORequest {
    @Override
    public void addRequest(TableRequest request) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(request);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateRequest(TableRequest request) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(request);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteRequest(TableRequest request) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(request);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public TableRequest getRequestByID(Long Id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<TableRequest> query = session.createQuery("FROM TableRequest WHERE request_id = :thisID", TableRequest.class)
                .setParameter("thisID", Id);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList().get(0);
    }

    @Override
    public List<TableRequest> getAllRequests() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<TableRequest> query = session.createQuery("FROM TableRequest", TableRequest.class);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }

    @Override
    public List<TableRequest> getRequestsByClientID(Long Id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<TableRequest> query = session.createQuery("FROM TableRequest WHERE client_id = :thisID", TableRequest.class)
                .setParameter("thisID", Id);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }
}
