package com.example.hms.service.custom.impl;

import com.example.hms.dao.DaoFactory;
import com.example.hms.dao.custom.UserDao;
import com.example.hms.dao.util.DaoTypes;
import com.example.hms.dto.UserDto;
import com.example.hms.entity.User;
import com.example.hms.service.custom.UserService;
import com.example.hms.service.util.Converter;
import org.hibernate.Session;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl() {
        userDao = DaoFactory.getDaoFactory().getDao(DaoTypes.UserDao);
    }

    @Override
    public Boolean save(UserDto dto, Session session) throws RuntimeException {
        Boolean save = userDao.save(Converter.getInstance().convert(dto), session);
        return save;
    }

    @Override
    public Boolean update(UserDto dto, Session session) throws RuntimeException {
        return userDao.update(Converter.getInstance().convert(dto), session);
    }

    @Override
    public Boolean delete(UserDto dto, Session session) throws RuntimeException {
        return userDao.delete(Converter.getInstance().convert(dto), session);
    }

    @Override
    public UserDto view(UserDto dto, Session session) throws RuntimeException {
        return Converter.getInstance().convert(userDao.view(Converter.getInstance().convert(dto), session));
    }

    @Override
    public List<UserDto> getAll(Session session) {
        return null;
    }
}
