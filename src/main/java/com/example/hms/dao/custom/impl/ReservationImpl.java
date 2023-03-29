package com.example.hms.dao.custom.impl;

import com.example.hms.dao.custom.ReservationDao;
import com.example.hms.entity.Reservation;
import org.hibernate.Session;

import java.util.List;

public class ReservationImpl implements ReservationDao {

    @Override
    public Boolean save(Reservation entity, Session session) {
        return null;
    }

    @Override
    public Boolean update(Reservation entity, Session session) {
        return null;
    }

    @Override
    public Boolean delete(Reservation entity, Session session) {
        return null;
    }

    @Override
    public Reservation view(Reservation entity, Session session) {
        return null;
    }

    @Override
    public List<Reservation> getAll(Session session) {
        return null;
    }
}
