package com.example.hms.dao.custom.impl;

import com.example.hms.dao.custom.RoomDao;
import com.example.hms.entity.Room;
import com.example.hms.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class RoomImpl implements RoomDao {

    @Override
    public Boolean save(Room entity, Session session) throws RuntimeException {
        Transaction transaction = session.getTransaction();
        try {
            session.save(entity);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException(exception);
        } finally {
            session.close();
        }
    }

    @Override
    public Boolean update(Room entity, Session session) {
        Transaction transaction = session.getTransaction();
        try {
            session.update(entity);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException(exception);
        } finally {
            session.close();
        }
    }

    @Override
    public Boolean delete(Room entity, Session session) {
        Transaction transaction = session.getTransaction();
        try {
            session.delete(entity);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException(exception);
        } finally {
            session.close();
        }
    }

    @Override
    public Room view(Room entity, Session session) {
        try {
            return session.get(Room.class, entity.getRoom_type_id());
        } finally {
            session.close();
        }
    }

    @Override
    public List<Room> getAll(Session session) {
        try {
            String sql = "From Room ";
            Query query = session.createQuery(sql);
            List<Room> list = query.list();
            return list;
        } finally {
            session.close();
        }
    }
}
