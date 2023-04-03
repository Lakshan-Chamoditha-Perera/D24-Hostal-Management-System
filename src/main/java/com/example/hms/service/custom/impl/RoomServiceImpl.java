package com.example.hms.service.custom.impl;

import com.example.hms.dao.DaoFactory;
import com.example.hms.dao.custom.RoomDao;
import com.example.hms.dao.util.DaoTypes;
import com.example.hms.dto.RoomDto;
import com.example.hms.entity.Room;
import com.example.hms.service.custom.RoomService;
import com.example.hms.service.util.Converter;
import org.hibernate.Session;

import java.util.List;
import java.util.stream.Collectors;

public class RoomServiceImpl implements RoomService {
    private final RoomDao roomDao;

    public RoomServiceImpl() {
        roomDao = DaoFactory.getDaoFactory().getDao(DaoTypes.RoomDao);
    }

    @Override
    public Boolean save(RoomDto dto, Session session) {
        return roomDao.save(Converter.getInstance().toRoomEntity(dto), session);
    }

    @Override
    public Boolean update(RoomDto dto, Session session) {
        return roomDao.update(Converter.getInstance().toRoomEntity(dto), session);
    }

    @Override
    public Boolean delete(RoomDto dto, Session session) {

        return roomDao.delete(Converter.getInstance().toRoomEntity(dto), session);
    }

    @Override
    public RoomDto view(RoomDto dto, Session session) {
        Room entity = roomDao.view(Converter.getInstance().toRoomEntity(dto), session);
        return Converter.getInstance().toRoomDto(entity);
    }

    @Override
    public List<RoomDto> getAll(Session session) {
        List<Room> roomList = roomDao.getAll(session);
        if (roomList.size() > 0) {
            return roomList.stream().map(room -> Converter.getInstance().toRoomDto(room)).collect(Collectors.toList());
        }
        throw new RuntimeException("Empty Room list!");
    }

    @Override
    public String getLastId(Session session) {
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
