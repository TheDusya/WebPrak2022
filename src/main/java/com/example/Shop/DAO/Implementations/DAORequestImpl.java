package com.example.Shop.DAO.Implementations;

import com.example.Shop.DAO.DAOClient;
import com.example.Shop.DAO.DAOGoodBought;
import com.example.Shop.DAO.DAORequest;
import com.example.Shop.DAO.Factory.DAOFactory;
import com.example.Shop.tables.Client;
import com.example.Shop.tables.Good;
import com.example.Shop.tables.GoodBought;
import com.example.Shop.tables.Request;
import com.example.Shop.types.request_state;
import com.example.Shop.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.List;

public class DAORequestImpl implements DAORequest {
    @Override
    public void addRequest(Request request) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(request);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateRequest(Request request) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(request);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteRequest(Request request) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(request);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Request getRequestByID(Long Id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Request> query = session.createQuery("FROM Request WHERE request_id = :thisID", Request.class)
                .setParameter("thisID", Id);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList().get(0);
    }

    @Override
    public Request getRequestByID(int Id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Request> query = session.createQuery("FROM Request WHERE request_id = :thisID", Request.class)
                .setParameter("thisID", Long.valueOf(Id));
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList().get(0);
    }


    @Override
    public List<Request> getAllRequests() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Request> query = session.createQuery("FROM Request", Request.class);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }

    @Override
    public List<Request> getRequestsByClient(List<Request> from, Client client) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Request> query = session.createQuery("FROM Request WHERE client.client_id = :thisID", Request.class)
                .setParameter("thisID", client.getClient_id());
        if (query.getResultList().size() == 0) {
            return null;
        }
        List<Request>res = query.getResultList();
        if (from == null) return res;
        for (Request request : res) if (!from.contains(request)) res.remove(request);
        return res;
    }


    @Override
    public List<Request> getRequestsInState (List<Request> from, request_state state) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Request> query = session.createQuery
                        ("FROM Request WHERE cur_state =: i1", Request.class)
                .setParameter("i1", state);
        if (query.getResultList().size() == 0) return null;
        List<Request>res = query.getResultList();
        if (from == null) return res;
        for (Request request : res) if (!from.contains(request)) res.remove(request);
        return res;
    }

    @Override
    public List<Request> getRequestsInInterval (List<Request> from, Timestamp min, Timestamp max) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Request> query = session.createQuery
                        ("FROM Request WHERE registration_time BETWEEN :i1 AND :i2", Request.class)
                .setParameter("i1", min).setParameter("i2", max);
        if (query.getResultList().size() == 0) return null;
        List<Request>res = query.getResultList();
        if (from == null) return res;
        for (Request request : res) if (!from.contains(request)) res.remove(request);
        return res;
    }

    @Override
    public Integer getCost(Request request) {
        DAOGoodBought dao = DAOFactory.getInstance().getGBDAO();
        List <GoodBought> goodsBought = dao.getGoodsBoughtByRequest(request);
        return dao.sumCosts(goodsBought)+request.getDelivery_cost();
    }
}
