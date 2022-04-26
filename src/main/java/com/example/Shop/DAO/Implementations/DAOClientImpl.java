package com.example.Shop.DAO.Implementations;

import com.example.Shop.DAO.DAOClient;
import com.example.Shop.tables.Client;
import com.example.Shop.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class DAOClientImpl implements DAOClient {

    @Override
    public void addClient(Client client) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        /*Query<Client> query = session.createQuery("INSERT INTO Client (login, pass, real_name, is_admin, mail, phone) VALUES (:logval, :pasval, :nameval, :adm, :mailval, :phoneval)", Client.class)
                .setParameter("logval", client.getLogin()).setParameter("pasval", client.getPass())
                .setParameter("nameval", client.setReal_name()).setParameter("adm", client.getIs_admin())
                .setParameter("mailval", client.getMail()).setParameter("phoneval", client.getPhone());

        */
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
    public List<Client> getClientsByName(String name) {
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
    public Client getClientByID(int Id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Client> query = session.createQuery("FROM Client WHERE client_id = :thisID", Client.class)
                .setParameter("thisID", Long.valueOf(Id));
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

    @Override
    public Client getClientByLogin(String login) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Client> query = session.createQuery("FROM Client WHERE login = :thisLogin", Client.class)
                .setParameter("thisLogin", login);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList().get(0);
    }

    @Override
    public List<Client> areAdmins(boolean param){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Client> query = session.createQuery("FROM Client WHERE is_admin = :param", Client.class)
                .setParameter("param", param);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }

    @Override
    public String getCity (Client client){
        if (client.getAddress() == null) return "";
        String adr = client.getAddress();
        for (String str : Set.of("город: ", "посёлок: ", "село: ", "деревня: ", "населённый пункт: ")){
            int k = adr.indexOf(str);
            if (k>=0){
                adr = adr.substring(k+str.length());
                return adr.substring(0, adr.indexOf(","));
            }
        }
        return "";
    }

}
