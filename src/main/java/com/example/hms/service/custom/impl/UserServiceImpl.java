package com.example.hms.service.custom.impl;

import com.example.hms.dao.DaoFactory;
import com.example.hms.dao.custom.UserDao;
import com.example.hms.dao.util.DaoTypes;
import com.example.hms.entity.User;
import com.example.hms.service.custom.UserService;
import org.hibernate.Session;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao;

    public UserServiceImpl() {
        userDao = DaoFactory.getDaoFactory().getDao(DaoTypes.UserDao);
    }

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
