package com.example.hms.dao.custom.impl;

import com.example.hms.dao.custom.ReservationDao;
import com.example.hms.entity.Reservation;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ReservationImpl implements ReservationDao {

    @Override
    public Boolean save(Reservation entity, Session session) {
        Transaction transaction = session.getTransaction();
        try (session) {
            session.save(entity);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Boolean update(Reservation entity, Session session) {
        Transaction transaction = session.getTransaction();
        try (session) {
            session.update(entity);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Boolean delete(Reservation entity, Session session) {
        Transaction transaction = session.getTransaction();
        try (session) {
            session.delete(entity);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Reservation view(Reservation entity, Session session) {
        return null;
    }

    @Override
    public List<Reservation> getAll(Session session) {
        return null;
    }

    @Override
    public String getLastId(Session session) {

        return null;
    }
}
