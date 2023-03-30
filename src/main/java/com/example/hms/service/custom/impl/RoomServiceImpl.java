package com.example.hms.service.custom.impl;

import com.example.hms.dao.DaoFactory;
import com.example.hms.dao.custom.RoomDao;
import com.example.hms.dao.util.DaoTypes;
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
    public Boolean save(Room entity, Session session) {
        return null;
    }

    @Override
    public Boolean update(Room entity, Session session) {
        return null;
    }

    @Override
    public Boolean delete(Room entity, Session session) {
        return null;
    }

    @Override
    public Room view(Room entity, Session session) {
        return null;
    }

    @Override
    public List<Room> getAll(Session session) {
        return null;
    }
}
