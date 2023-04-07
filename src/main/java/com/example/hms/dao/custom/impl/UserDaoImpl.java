package com.example.hms.dao.custom.impl;

import com.example.hms.dao.custom.UserDao;
import com.example.hms.entity.User;
import org.hibernate.Session;

import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public Boolean save(User entity, Session session) throws RuntimeException {
        session.save(entity);
        return true;
    }

    @Override
    public Boolean update(User entity, Session session) throws RuntimeException {
        session.update(entity);
        return true;
    }

    @Override
    public Boolean delete(User entity, Session session) throws RuntimeException {
        session.delete(entity);
        return true;
    }

    @Override
    public User view(String id, Session session) {
        try (session) {
            return session.get(User.class, id);
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

    @Override
    public String getLastId(Session session) {
        return null;
    }
}
