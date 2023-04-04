package com.example.hms.dao.custom.impl;

import com.example.hms.dao.custom.StudentDao;
import com.example.hms.entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDaoImpl implements StudentDao {

    @Override
    public Boolean save(Student entity, Session session) throws RuntimeException {
        session.save(entity);
        return true;
    }

    @Override
    public Boolean update(Student entity, Session session) throws RuntimeException {
        session.update(entity);
        return true;
    }

    @Override
    public Boolean delete(Student entity, Session session) throws RuntimeException {
        session.delete(entity);
        return true;

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
    public String getLastId(Session session) throws RuntimeException {
        try (session) {
            Query query = session.createQuery("SELECT student_id FROM Student ORDER BY student_id DESC");
            query.setMaxResults(1);
            List results = query.list();
            return (results.size() == 0) ? null : (String) results.get(0);
        }
    }
}
