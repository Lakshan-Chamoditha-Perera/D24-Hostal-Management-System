package com.example.hms.service.custom.impl;

import com.example.hms.dao.DaoFactory;
import com.example.hms.dao.custom.RoomDao;
import com.example.hms.dao.util.DaoTypes;
import com.example.hms.dto.RoomDto;
import com.example.hms.entity.Room;
import com.example.hms.service.custom.RoomService;
import com.example.hms.service.util.Converter;
import com.example.hms.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public class RoomServiceImpl implements RoomService {
    private final RoomDao roomDao;

    public RoomServiceImpl() {
        roomDao = DaoFactory.getDaoFactory().getDao(DaoTypes.RoomDao);
    }

    @Override
    public Boolean save(RoomDto dto) throws RuntimeException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            roomDao.save(Converter.getInstance().toRoomEntity(dto), session);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Boolean update(RoomDto dto) throws RuntimeException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            roomDao.update(Converter.getInstance().toRoomEntity(dto), session);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Boolean delete(RoomDto dto) throws RuntimeException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            roomDao.delete(Converter.getInstance().toRoomEntity(dto), session);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException(exception);
        }
    }

    @Override
    public RoomDto view(RoomDto dto) throws RuntimeException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Room entity = roomDao.view(Converter.getInstance().toRoomEntity(dto), session);
        if (entity != null) {
            return Converter.getInstance().toRoomDto(entity);
        }
        throw new RuntimeException("Room not found!");
    }

    @Override
    public List<RoomDto> getAll() throws RuntimeException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        List<Room> roomList = roomDao.getAll(session);
        if (roomList.size() > 0) {
            return roomList.stream().map(room -> Converter.getInstance().toRoomDto(room)).collect(Collectors.toList());
        }
        throw new RuntimeException("Empty Room list!");
    }

    @Override
    public String getLastId() throws RuntimeException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        String lastId = roomDao.getLastId(session);
        if (lastId == null) {
            return "RM-0001";
        } else {
            String[] split = lastId.split("[R][M][-]");
            int lastDigits = Integer.parseInt(split[1]);
            lastDigits++;
            return (String.format("RM-%04d", lastDigits));
        }
    }
}
