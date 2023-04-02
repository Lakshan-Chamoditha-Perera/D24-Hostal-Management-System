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
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl() {
        userDao = DaoFactory.getDaoFactory().getDao(DaoTypes.UserDao);
    }

    @Override
    public Boolean save(UserDto dto, Session session) throws RuntimeException {
        User user = new User();
        user.setId(dto.getId());
        user.setPassword(dto.getPassword());
        user.setPasswordHint(dto.getPasswordHint());

        return userDao.save(user, session);
    }

    @Override
    public Boolean update(UserDto dto, Session session) throws RuntimeException {
        User user = new User();
        user.setId(dto.getId());
        user.setPassword(dto.getPassword());
        user.setPasswordHint(dto.getPasswordHint());

        return userDao.update(user, session);
    }

    @Override
    public Boolean delete(UserDto dto, Session session) throws RuntimeException {
        User user = new User();
        user.setId(dto.getId());

        return userDao.delete(user, session);
    }

    @Override
    public UserDto view(UserDto dto, Session session) throws RuntimeException {
        User user = new User();
        user.setId(dto.getId());

        User entity = userDao.view(user, session);

        if (entity != null) {
            dto.setId(dto.getId());
            dto.setPassword(dto.getPassword());
            dto.setPasswordHint(dto.getPasswordHint());
            return dto;
        }
        throw new RuntimeException("User Not Found !");
    }

    @Override
    public List<UserDto> getAll(Session session) throws RuntimeException {
        List<User> allUsers = userDao.getAll(session);
        if (allUsers.size() > 0) {
            return allUsers.stream().map(user -> Converter.getInstance().toUserDto(user)).collect(Collectors.toList());
        }
        throw new RuntimeException("Empty users list!");
    }

    @Override
    public String getLastId(Session session) {
        return null;
    }
}
