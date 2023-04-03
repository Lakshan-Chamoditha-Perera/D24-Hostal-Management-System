package com.example.hms.service.custom.impl;

import com.example.hms.dao.DaoFactory;
import com.example.hms.dao.custom.ReservationDao;
import com.example.hms.dao.custom.RoomDao;
import com.example.hms.dao.util.DaoTypes;
import com.example.hms.dto.ReservationDto;
import com.example.hms.entity.Room;
import com.example.hms.service.custom.ReservationService;
import com.example.hms.service.util.Converter;
import com.example.hms.util.FactoryConfiguration;
import org.hibernate.Session;

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
        return reservationDao.save(Converter.getInstance().toReservationEntity(dto), session);
       /* if (save) {
            Integer qty = dto.getRoomDto().getQty();
            Room roomEntity = roomDao.view(Converter.getInstance().toRoomEntity(dto.getRoomDto()), FactoryConfiguration.getFactoryConfiguration().getSession());
            System.out.println(roomEntity);
            int i = roomEntity.getQty() - qty;
            roomEntity.setQty(i);
            System.out.println(roomEntity);
            return roomDao.update(roomEntity, FactoryConfiguration.getFactoryConfiguration().getSession());
        }
        throw new RuntimeException("Transaction not success");*/
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
