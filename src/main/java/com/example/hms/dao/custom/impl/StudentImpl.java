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
        try (session) {
            transaction.begin();
            session.save(entity);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Boolean update(Student entity, Session session) throws RuntimeException {
        Transaction transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            session.update(entity);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Boolean delete(Student entity, Session session) throws RuntimeException{
        Transaction transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            session.delete(entity);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Student view(Student entity, Session session) throws RuntimeException {
        try (session) {
            return session.get(Student.class, entity.getStudent_id());
        }
    }

    @Override
    public List<Student> getAll(Session session) throws RuntimeException {
        try (session) {
            String sql = "FROM Student";
            Query query = session.createQuery(sql);
            List<Student> list = query.list();
            return list;
        }
    }

    @Override
    public String getLastId(Session session)throws RuntimeException  {
        try (session) {
            Query query = session.createQuery("SELECT student_id FROM Student ORDER BY student_id DESC");
            query.setMaxResults(1);
            List results = query.list();
            return (results.size() == 0) ? null : (String) results.get(0);
        }
    }
}
