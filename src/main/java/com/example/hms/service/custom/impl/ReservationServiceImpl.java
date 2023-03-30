package com.example.hms.service.custom.impl;

import com.example.hms.dao.DaoFactory;
import com.example.hms.dao.custom.ReservationDao;
import com.example.hms.dao.util.DaoTypes;
import com.example.hms.entity.Reservation;
import com.example.hms.service.custom.ReservationService;
import org.hibernate.Session;

import java.util.List;

public class ReservationServiceImpl implements ReservationService {

    private ReservationDao reservationDao;
    public ReservationServiceImpl(){
        reservationDao = DaoFactory.getDaoFactory().getDao(DaoTypes.ReservationDao);
    }

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
