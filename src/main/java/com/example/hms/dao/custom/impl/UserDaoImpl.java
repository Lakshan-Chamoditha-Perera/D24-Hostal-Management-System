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
    public Boolean delete(String id, Session session) throws RuntimeException {
        User user = new User();
        user.setId(id);
        session.delete(user);
        return true;
    }

    @Override
    public User view(String id, Session session) {
        try (session) {
            User user = session.get(User.class, id);
            System.out.println(user);
            return user;
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
