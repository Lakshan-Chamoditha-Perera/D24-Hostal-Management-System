package com.example.hms.dao.custom.impl;

import com.example.hms.dao.custom.UserDao;
import com.example.hms.entity.Student;
import com.example.hms.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserImpl implements UserDao {

    @Override
    public Boolean save(User entity, Session session) throws RuntimeException {
        Transaction transaction = session.getTransaction();
        try {
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
        try {
            session.delete(entity);
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
    public User view(User entity, Session session) {
        try {
            return session.get(User.class, entity.getId());
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAll(Session session) {
        try {
            String sql = "From User";
            Query query = session.createQuery(sql);
            List<User> list = query.list();
            return list;
        } finally {
            session.close();
        }
    }
}
