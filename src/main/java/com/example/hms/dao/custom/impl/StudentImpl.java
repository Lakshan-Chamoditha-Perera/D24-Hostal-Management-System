package com.example.hms.dao.custom.impl;

import com.example.hms.dao.custom.StudentDao;
import com.example.hms.entity.Student;
import org.hibernate.Session;

import java.util.List;

public class StudentImpl implements StudentDao {


    @Override
    public Boolean save(Student entity, Session session) {
        return null;
    }

    @Override
    public Boolean update(Student entity, Session session) {
        return null;
    }

    @Override
    public Boolean delete(Student entity, Session session) {
        return null;
    }

    @Override
    public Student view(Student entity, Session session) {
        return null;
    }

    @Override
    public List<Student> getAll(Session session) {
        return null;
    }
}
