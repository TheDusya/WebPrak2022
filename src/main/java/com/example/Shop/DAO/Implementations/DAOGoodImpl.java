package com.example.Shop.DAO.Implementations;

import com.example.Shop.DAO.DAOGood;
import com.example.Shop.tables.Client;
import com.example.Shop.tables.Good;
import com.example.Shop.tables.Request;
import com.example.Shop.types.tech_type;
import com.example.Shop.util.GoodsInfo;
import com.example.Shop.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class DAOGoodImpl implements DAOGood {

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
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

    public List<Good> FromFunc(Query<Good> query, List<Good> from){
        if (query.getResultList().size() == 0) return null;
        List<Good>res = query.getResultList();
        List<Good> res2 = query.getResultList();
        if (from == null) return null;
        for (Good good : res2) if (!from.contains(good)) res.remove(good);
        return res;
    }

    @Override
    public Good getGoodByID(Long Id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Good> query = session.createQuery("FROM Good WHERE good_id = :thisID", Good.class)
                .setParameter("thisID", Id);
        if (query.getResultList().size() == 0) return null;
        return query.getResultList().get(0);
    }

    @Override
    public Good getGoodByID(int Id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Good> query = session.createQuery("FROM Good WHERE good_id = :thisID", Good.class)
                .setParameter("thisID", Long.valueOf(Id));
        if (query.getResultList().size() == 0) return null;
        return query.getResultList().get(0);
    }

    @Override
    public List<Good> getAllGoods() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Good> query = session.createQuery("FROM Good", Good.class);
        if (query.getResultList().size() == 0) return null;
        return query.getResultList();
    }

    @Override
    public List<Good> getGoodsByCountry(List<Good> from, String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<Good> query = session.createQuery("FROM Good WHERE country LIKE :thisCountry", Good.class)
                .setParameter("thisCountry", name );
        return FromFunc(query, from);
    }

    @Override
    public List<Good> getGoodsByCountry(String name) {
        return getGoodsByCountry(this.getAllGoods(), name);
    }

    @Override
    public List<Good> getGoodsByManufacturer(List<Good> from, String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Good> query = session.createQuery("FROM Good WHERE manufacturer LIKE :thisManufacturer", Good.class)
                .setParameter("thisManufacturer", name );
        return FromFunc(query, from);
    }

    @Override
    public List<Good> getGoodsByManufacturer(String name) {
        return getGoodsByManufacturer(this.getAllGoods(), name);
    }

    @Override
    public List<Good> getGoodsByKind(List<Good> from, String tech_kind) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Good> query = session.createQuery("FROM Good WHERE kind = :thiskind", Good.class)
                .setParameter("thiskind", tech_kind);
        List<Good> res = FromFunc(query, from);
        res.removeAll(Collections.singleton(null));
        return res;
    }

    @Override
    public List<Good> getGoodsByKind(String tech_kind) {
        return getGoodsByKind(this.getAllGoods(), tech_kind);
    }

    @Override
    public List<Good> getGoodsInStock(List<Good> from){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Good> query = session.createQuery("FROM Good WHERE in_stock > 0", Good.class);
        return FromFunc(query, from);
    }

    @Override
    public List<Good> getGoodsInStock(){
        return getGoodsInStock(this.getAllGoods());
    }

    @Override
    public List<Good> getGoodsPriceBetween (List<Good> from, Integer min, Integer max){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Good> query = session.createQuery
                ("FROM Good WHERE price BETWEEN :i1 AND :i2", Good.class)
                .setParameter("i1", min).setParameter("i2", max);
        return FromFunc(query, from);
    }

    @Override
    public List<Good> getGoodsPriceBetween(Integer min, Integer max){
        return getGoodsPriceBetween(this.getAllGoods(), min, max);
    }

    @Override
    public  List<Good> getByFilter (List<Good> from, String filter, String text) throws Exception {
        if (GoodsInfo.StringChars.contains(filter)){
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query<Good> query = session.createQuery
                            ("FROM Good WHERE chars LIKE : expr", Good.class)
                    .setParameter("expr", "%"+filter+": "+text+"%");
            return FromFunc(query, from);
        }
        else if (GoodsInfo.StringArrChars.contains(filter)){
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query<Good> query = session.createQuery
                            ("FROM Good WHERE chars LIKE : expr", Good.class)
                    .setParameter("expr", "%"+filter+": [%"+text+"%]");
            return FromFunc(query, from);
        }
        else throw new Exception();///
    }

    @Override
    public  List<Good> getByFilter (String filter, String text) throws Exception {
        return getByFilter(this.getAllGoods(), filter, text);
    }

    @Override
    public  List<Good> getByFilter (List<Good> from, String filter, float dec) throws Exception {
        if (GoodsInfo.DecChars.contains(filter)){
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query<Good> query = session.createQuery
                            ("FROM Good WHERE chars LIKE : expr", Good.class)
                    .setParameter("expr", "%"+filter+": "+String.valueOf(dec)+"%");
            return FromFunc(query, from);
        }
        else if (GoodsInfo.IntChars.contains(filter)){
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query<Good> query = session.createQuery
                            ("FROM Good WHERE chars LIKE : expr", Good.class)
                    .setParameter("expr", "%"+filter+": "+String.valueOf((int)dec)+"%");
            return FromFunc(query, from);
        }
        else throw new Exception();
    }

    @Override
    public  List<Good> getByFilter (String filter, float dec) throws Exception {
        return getByFilter(this.getAllGoods(), filter, dec);
    }

    @Override
    public  List<Good> getByFilter (List<Good> from, String filter, boolean b) throws Exception {
        if (GoodsInfo.DecChars.contains(filter)){
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query<Good> query = session.createQuery
                            ("FROM Good WHERE chars LIKE : expr", Good.class)
                    .setParameter("expr", "%"+filter+": "+String.valueOf(b)+"%");
            return FromFunc(query, from);
        }
        else throw new Exception();///
    }
    @Override
    public  List<Good> getByFilter (String filter, boolean b) throws Exception {
        return getByFilter(this.getAllGoods(), filter, b);
    }

    @Override
    public  List<Good> searchGoods (String request) {
        List<Good> all = getAllGoods();
        Session session = HibernateUtil.getSessionFactory().openSession();
        String requestL = request.toLowerCase(Locale.ROOT);
        List<Good> byModel = FromFunc(session.createQuery ("FROM Good WHERE LOWER(model) =: expr", Good.class)
                .setParameter("expr",requestL), all);
        List<Good> byManufacturer = FromFunc(session.createQuery ("FROM Good WHERE LOWER(manufacturer)=: expr", Good.class)
                .setParameter("expr",requestL), all);
        List<Good> byKind = FromFunc(session.createQuery ("FROM Good WHERE LOWER(kind) LIKE: expr", Good.class)
                .setParameter("expr",requestL), all);
        List<Good> byCountry = FromFunc(session.createQuery ("FROM Good WHERE LOWER(country) LIKE: expr", Good.class)
                .setParameter("expr",requestL), all);
        List<Good> byChars = FromFunc(session.createQuery ("FROM Good WHERE LOWER(chars) LIKE : expr", Good.class)
                .setParameter("expr", "%"+requestL+"%"), all);
        List<Good> res = new ArrayList<>();
        if (byModel != null)res.addAll(byModel);
        if (byManufacturer != null)res.addAll(byManufacturer);
        if (byKind != null)res.addAll(byKind);
        if (byCountry != null)res.addAll(byCountry);
        if (byChars!=null)res.addAll(byChars);
        if (isNumeric(request)){
            int i=Integer.parseInt(request);
            List<Good> byGoodId = FromFunc(session.createQuery ("FROM Good WHERE good_id =: expr", Good.class)
                    .setParameter("expr", i), all);
            if (byGoodId!=null) res.addAll(byGoodId);
        }
        return res;

    }
}
