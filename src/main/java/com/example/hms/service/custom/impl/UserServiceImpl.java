package com.example.hms.service.custom.impl;

import com.example.hms.dao.DaoFactory;
import com.example.hms.dao.custom.UserDao;
import com.example.hms.dao.util.DaoTypes;
import com.example.hms.dto.UserDto;
import com.example.hms.entity.User;
import com.example.hms.service.custom.UserService;
import com.example.hms.service.util.Converter;
import com.example.hms.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl() {
        userDao = DaoFactory.getInstance().getDao(DaoTypes.UserDao);
    }

    @Override
    public Boolean save(UserDto dto) throws RuntimeException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        User user = new User();
        user.setId(dto.getId());
        user.setPassword(dto.getPassword());
        user.setPasswordHint(dto.getPasswordHint());

        Transaction transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            userDao.save(user, session);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Boolean update(UserDto dto) throws RuntimeException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        User user = new User();
        user.setId(dto.getId());
        user.setPassword(dto.getPassword());
        user.setPasswordHint(dto.getPasswordHint());

        Transaction transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            userDao.update(user, session);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Boolean delete(String id) throws RuntimeException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            userDao.delete(id, session);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException(exception);
        }
    }

    @Override
    public UserDto view(String id) throws RuntimeException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        User entity = userDao.view(id, session);
        if (entity != null) {
            UserDto dto = new UserDto();
            dto.setId(entity.getId());
            dto.setPassword(entity.getPassword());
            dto.setPasswordHint(entity.getPasswordHint());
            return dto;
        }
        throw new RuntimeException("User Not Found !");
    }

    @Override
    public List<UserDto> getAll() throws RuntimeException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        List<User> allUsers = userDao.getAll(session);
        if (allUsers.size() > 0) {
            return allUsers.stream().map(user -> Converter.getInstance().toUserDto(user)).collect(Collectors.toList());
        }
        throw new RuntimeException("Empty users list!");
    }

    @Override
    public String getLastId() {
        return null;
    }
}
