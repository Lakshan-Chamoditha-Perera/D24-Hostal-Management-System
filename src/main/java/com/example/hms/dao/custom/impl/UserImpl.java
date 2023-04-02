package com.example.hms.dao.custom.impl;

import com.example.hms.dao.custom.UserDao;
import com.example.hms.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserImpl implements UserDao {

    @Override
    public Boolean save(User entity, Session session) throws RuntimeException {
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.save(entity);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException(exception);
        } finally {
            session.close();
        }
    }

    @Override
    public Boolean update(User entity, Session session) throws RuntimeException {
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.update(entity);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException(exception);
        } finally {
            session.close();
        }
    }

    @Override
    public Boolean delete(User entity, Session session) throws  RuntimeException {
        Transaction transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            session.delete(entity);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException(exception);
        }
    }

    @Override
    public User view(User entity, Session session){
        try (session) {
            return session.get(User.class, entity.getId());
        }
    }

    @Override
    public List getAll(Session session) {
        try (session) {
            String sql = "From User";
            List list = session.createQuery(sql).list();
            return list;
        }
    }
}
