package com.example.hms.dao.custom.impl;

import com.example.hms.dao.custom.RoomDao;
import com.example.hms.entity.Room;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class RoomDaoImpl implements RoomDao {

    @Override
    public Boolean save(Room entity, Session session) throws RuntimeException {
        session.save(entity);
        return true;
    }

    @Override
    public Boolean update(Room entity, Session session)throws RuntimeException {
        session.update(entity);
        return true;
    }

    @Override
    public Boolean delete(Room entity, Session session) {
        session.delete(entity);
        return true;
    }

    @Override
    public Room view(Room entity, Session session) {
        try (session) {
            return session.get(Room.class, entity.getRoom_type_id());
        }
    }

    @Override
    public List<Room> getAll(Session session) {
        try (session) {
            String sql = "FROM Room";
            Query query = session.createQuery(sql);
            List list = query.list();
            return list;
        }
    }

    @Override
    public String getLastId(Session session) {
        try (session) {
            Query query = session.createQuery("SELECT room_type_id FROM Room ORDER BY room_type_id DESC");
            query.setMaxResults(1);
            List results = query.list();
            return (results.size() == 0) ? null : (String) results.get(0);
        }
    }
}
