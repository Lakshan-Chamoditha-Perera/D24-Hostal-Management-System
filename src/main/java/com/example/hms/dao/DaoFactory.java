package com.example.hms.dao;

import com.example.hms.dao.custom.impl.*;
import com.example.hms.dao.util.DaoTypes;
import com.example.hms.dao.util.exception.DaoNotFoundException;

public class DaoFactory {
    private static DaoFactory instance;

    private DaoFactory() {
    }

    public static DaoFactory getDaoFactory() {
        return instance == null ? instance = new DaoFactory() : instance;
    }

    public <T extends SuperDao> T getDao(DaoTypes type) {
        switch (type) {
            case UserDao:
                return (T) new UserDaoImpl();
            case RoomDao:
                return (T) new RoomDaoImpl();
            case StudentsDao:
                return (T) new StudentDaoImpl();
            case ReservationDao:
                return (T) new ReservationDaoImpl();
            case QueryDao:
                return (T) new QueryDaoImpl();
            default:
                throw new DaoNotFoundException();
        }
    }
}
