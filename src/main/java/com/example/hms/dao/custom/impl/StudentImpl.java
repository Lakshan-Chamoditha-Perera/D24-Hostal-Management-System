package com.example.hms.dao.custom.impl;

import com.example.hms.dao.custom.StudentDao;
import com.example.hms.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class StudentImpl implements StudentDao {


    @Override
    public Boolean save(Student entity, Session session) throws RuntimeException {
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
    public Boolean update(Student entity, Session session) {
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
    public Boolean delete(Student entity, Session session) {
        Transaction transaction = session.getTransaction();
        try {
            session.delete(entity.getStudent_id());
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
    public Student view(Student entity, Session session) {
        try {
            return session.get(Student.class, entity.getStudent_id());
        } finally {
            session.close();
        }
    }

    @Override
    public List<Student> getAll(Session session) {
        try {
            String sql = "From Student";
            Query query = session.createQuery(sql);
            List<Student> list = query.list();
            return list;
        } finally {
            session.close();
        }
    }
}
