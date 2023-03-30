package com.example.hms.dao;

import com.example.hms.dao.custom.impl.ReservationImpl;
import com.example.hms.dao.custom.impl.RoomImpl;
import com.example.hms.dao.custom.impl.StudentImpl;
import com.example.hms.dao.custom.impl.UserImpl;
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
                return (T) new UserImpl();
            case RoomDao:
                return (T) new RoomImpl();
            case StudentsDao:
                return (T) new StudentImpl();
            case ReservationDao:
                return (T) new ReservationImpl();
            default:
                throw new DaoNotFoundException();
        }
    }
}
