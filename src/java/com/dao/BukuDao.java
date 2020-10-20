/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.hibernate.HibernateUtil;
import com.hibernate.Buku;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author java
 */
public class BukuDao implements AutoCloseable {

    private Session sesion = null;
    private Transaction transaction = null;
    
    private Session getSesionHibernate() throws Throwable {
        if (sesion == null) {
            sesion = HibernateUtil.getSessionFactory().openSession();
            transaction = sesion.beginTransaction();
        }
        return sesion;
    }
    
    public List<Buku> getAll() throws Throwable {
        Session s = getSesionHibernate();
        return s.createCriteria(Buku.class).list();
    }
    
    public Buku getById(int id) throws Throwable {
        Session s = getSesionHibernate();
        return (Buku)s.get(Buku.class, id);
    }
    
    public List<Buku> getByTitle(String title) throws Throwable {
        Criteria q
            = getSesionHibernate().createCriteria(Buku.class)
                .add(Restrictions.ilike("title", "%" + title + "%"));
        return q.list();
    }
    
    public void delete(Buku buku) throws Throwable {
        getSesionHibernate().delete(buku);
    }
    
    public void update(Buku buku) throws Throwable {
        Session s = getSesionHibernate();
        s.saveOrUpdate(buku);
    }
    
    @Override
    public void close() throws Exception {
        if (sesion != null) {
            if (sesion.isOpen()) {
                if (transaction != null) {
                    if (transaction.isActive()) {
                        try {
                            transaction.commit();
                        } catch(Throwable t) {
                            transaction.rollback();
                        }
                    }
                    transaction = null;
                }
                sesion.close();
            }
            sesion = null;
        }
    }
    
}
