package com.example.hms.service.custom.impl;

import com.example.hms.dao.DaoFactory;
import com.example.hms.dao.custom.RoomDao;
import com.example.hms.dao.util.DaoTypes;
import com.example.hms.dto.SuperDto;
import com.example.hms.entity.Room;
import com.example.hms.service.custom.RoomService;
import org.hibernate.Session;

import java.util.List;

public class RoomServiceImpl implements RoomService {
    RoomDao roomDao;
    public RoomServiceImpl(){
        roomDao  = DaoFactory.getDaoFactory().getDao(DaoTypes.RoomDao);
    }
    @Override
    public Boolean save(SuperDto dto, Session session) {
        return null;
    }

    @Override
    public Boolean update(SuperDto dto, Session session) {
        return null;
    }

    @Override
    public Boolean delete(SuperDto dto, Session session) {
        return null;
    }

    @Override
    public SuperDto view(SuperDto dto, Session session) {
        return null;
    }

    @Override
    public List<Room> getAll(Session session) {
        return null;
    }

    @Override
    public String getLastId(Session session) {
        return null;
    }
}
