package com.example.hms.service.custom.impl;

import com.example.hms.dao.DaoFactory;
import com.example.hms.dao.custom.ReservationDao;
import com.example.hms.dao.custom.RoomDao;
import com.example.hms.dao.util.DaoTypes;
import com.example.hms.dto.ReservationDto;
import com.example.hms.entity.Reservation;
import com.example.hms.service.custom.ReservationService;
import com.example.hms.service.util.Converter;
import com.example.hms.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public class ReservationServiceImpl implements ReservationService {

    private final ReservationDao reservationDao;
    private final RoomDao roomDao;

    public ReservationServiceImpl() {
        reservationDao = DaoFactory.getInstance().getDao(DaoTypes.ReservationDao);
        roomDao = DaoFactory.getInstance().getDao(DaoTypes.RoomDao);
    }

    @Override
    public Boolean save(ReservationDto dto) throws RuntimeException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
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
    public Boolean update(ReservationDto dto) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            reservationDao.update(Converter.getInstance().toReservationEntity(dto), session);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public Boolean delete(ReservationDto dto) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            reservationDao.delete(Converter.getInstance().toReservationEntity(dto), session);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException(exception);
        }
    }

    @Override
    public ReservationDto view(String id) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
//        Transaction transaction = session.getTransaction();
        try (session) {
            return Converter.getInstance().toReservationDto(reservationDao.view(id, session));
        } catch (RuntimeException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public List<ReservationDto> getAll() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        List<Reservation> reservationDtoList = reservationDao.getAll(session);
        if (reservationDtoList.size() > 0) {
            return reservationDtoList.stream().map(reservation -> Converter.getInstance().toReservationDto(reservation)).collect(Collectors.toList());
        }
        throw new RuntimeException("Empty Room list!");
    }

    @Override
    public String getLastId() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
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
