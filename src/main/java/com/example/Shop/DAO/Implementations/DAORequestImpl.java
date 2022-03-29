package com.example.Shop.DAO.Implementations;

import com.example.Shop.DAO.DAOClient;
import com.example.Shop.DAO.DAOGoodBought;
import com.example.Shop.DAO.DAORequest;
import com.example.Shop.DAO.Factory.DAOFactory;
import com.example.Shop.tables.Client;
import com.example.Shop.tables.GoodBought;
import com.example.Shop.tables.Request;
import com.example.Shop.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

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
    public List<Request> getAllRequests() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Request> query = session.createQuery("FROM Request", Request.class);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }

    @Override
    public List<Request> getRequestsByClientID(Long Id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Request> query = session.createQuery("FROM Request WHERE client.client_id = :thisID", Request.class)
                .setParameter("thisID", Id);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }

    @Override
    public Integer getCost(Request request) {
        DAOGoodBought dao = DAOFactory.getInstance().getGBDAO();
        List <GoodBought> goodsBought = dao.getGoodsBoughtByRequestID(request.getRequest_id());
        return dao.sumCosts(goodsBought)+request.getDelivery_cost();
    }
}
