package com.example.Shop.DAO.Implementations;

import com.example.Shop.DAO.DAOGoodBought;
import com.example.Shop.DAO.DAORequest;
import com.example.Shop.util.DAOFactory;
import com.example.Shop.tables.Client;
import com.example.Shop.tables.GoodBought;
import com.example.Shop.tables.Request;
import com.example.Shop.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
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
        List<Request> res2 = query.getResultList();
        if (from == null) return null;
        for (Request request : res2) if (!from.contains(request)) res.remove(request);
        return res;
    }

    @Override
    public List<Request> getRequestsByClient(Client client) {
        return getRequestsByClient(this.getAllRequests(), client);
    }

    @Override
    public List<Request> getRequestsInState (List<Request> from, String state) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Request> query = session.createQuery
                        ("FROM Request WHERE cur_state LIKE :i1", Request.class)
                .setParameter("i1", state);
        if (query.getResultList().size() == 0) return null;
        List<Request>res = query.getResultList();
        List<Request> res2 = query.getResultList();
        if (from == null) return null;
        for (Request request : res2)
            if (!from.contains(request)) res.remove(request);
        return res;
    }

    @Override
    public List<Request> getRequestsInState (String state) {
        return getRequestsInState(this.getAllRequests(), state);
    }

    @Override
    public List<Request> getRequestsInInterval (List<Request> from, Date min, Date max) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Request> query = session.createQuery
                        ("FROM Request WHERE registration_time BETWEEN :i1 AND :i2", Request.class)
                .setParameter("i1", min).setParameter("i2", max);
        if (query.getResultList().size() == 0) return null;
        List<Request>res = query.getResultList();
        List<Request> res2 = query.getResultList();
        if (from == null) return null;
        for (Request request : res2) if (!from.contains(request)) res.remove(request);
        return res;
    }

    @Override
    public List<Request> getRequestsInInterval (Date min, Date max) {
        return getRequestsInInterval(this.getAllRequests(), min, max);
    }


    @Override
    public Integer getCost(Request request) {
        DAOGoodBought dao = DAOFactory.getInstance().getGBDAO();
        List <GoodBought> goodsBought = dao.getGoodsBoughtByRequest(request);
        if (goodsBought==null) return 0;
        else return dao.sumCosts(goodsBought)+request.getDelivery_cost();
    }
}
