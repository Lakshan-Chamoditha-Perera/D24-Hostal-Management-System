package com.example.hms.dao.custom.impl;

import com.example.hms.dao.custom.StudentDao;
import com.example.hms.entity.Student;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
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
    public Boolean delete(String id, Session session) throws RuntimeException {
        Student student = new Student();
        student.setStudent_id(id);
        session.delete(student);
        System.out.println("Std Dao end");
        return true;
    }

    @Override
    public Student view(String id, Session session) throws RuntimeException {
        return session.get(Student.class, id);
    }

    @Override
    public List<Student> getAll(Session session) throws RuntimeException {
        String sql = "FROM Student";
        Query query = session.createQuery(sql);
        List<Student> list = query.list();
        return list;
    }

    @Override
    public String getLastId(Session session) throws RuntimeException {
        Query query = session.createQuery("SELECT student_id FROM Student ORDER BY student_id DESC");
        query.setMaxResults(1);
        List results = query.list();
        return (results.size() == 0) ? null : (String) results.get(0);
    }

    @Override
    public List<Student> searchStudentByText(String text, Session session) {
        Query query = session.createQuery("FROM Student  WHERE name LIKE '%" + text + "%'");
        List<Student> list = query.list();
        return list;
    }
}
