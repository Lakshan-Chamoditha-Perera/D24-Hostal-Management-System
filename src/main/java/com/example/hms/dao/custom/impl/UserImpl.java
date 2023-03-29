package com.example.hms.dao.custom.impl;

import com.example.hms.dao.custom.UserDao;
import com.example.hms.entity.User;
import org.hibernate.Session;

import java.util.List;

public class UserImpl implements UserDao {

    @Override
    public Boolean save(User entity, Session session) {
        return null;
    }

    @Override
    public Boolean update(User entity, Session session) {
        return null;
    }

    @Override
    public Boolean delete(User entity, Session session) {
        return null;
    }

    @Override
    public User view(User entity, Session session) {
        return null;
    }

    @Override
    public List<User> getAll(Session session) {
        return null;
    }
}
