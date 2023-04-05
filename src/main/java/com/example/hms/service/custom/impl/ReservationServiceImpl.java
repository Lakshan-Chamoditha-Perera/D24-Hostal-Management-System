package com.example.hms.service.custom.impl;

import com.example.hms.dao.DaoFactory;
import com.example.hms.dao.custom.ReservationDao;
import com.example.hms.dao.custom.RoomDao;
import com.example.hms.dao.util.DaoTypes;
import com.example.hms.dto.ReservationDto;
import com.example.hms.service.custom.ReservationService;
import com.example.hms.service.util.Converter;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ReservationServiceImpl implements ReservationService {

    private final ReservationDao reservationDao;
    private final RoomDao roomDao;

    public ReservationServiceImpl() {
        reservationDao = DaoFactory.getDaoFactory().getDao(DaoTypes.ReservationDao);
        roomDao = DaoFactory.getDaoFactory().getDao(DaoTypes.RoomDao);
    }
    @Override
    public Boolean save(ReservationDto dto, Session session) throws RuntimeException {
        Transaction transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            reservationDao.save(Converter.getInstance().toReservationEntity(dto), session);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public Boolean update(ReservationDto dto, Session session) {
        return null;
    }

    @Override
    public Boolean delete(ReservationDto dto, Session session) {
        return null;
    }

    @Override
    public ReservationDto view(ReservationDto dto, Session session) {
        return null;
    }

    @Override
    public List<ReservationDto> getAll(Session session) {
        return null;
    }

    @Override
    public String getLastId(Session session) {
        String lastId = reservationDao.getLastId(session);
        if (lastId == null) {
            return "REC-000001";
        } else {
            String[] split = lastId.split("[R][E][C][-]");
            int lastDigits = Integer.parseInt(split[1]);
            lastDigits++;
            return (String.format("REC-%06d", lastDigits));
        }
    }
}
