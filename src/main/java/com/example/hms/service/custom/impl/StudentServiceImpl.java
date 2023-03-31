package com.example.hms.service.custom.impl;

import com.example.hms.dao.DaoFactory;
import com.example.hms.dao.custom.StudentDao;
import com.example.hms.dao.util.DaoTypes;
import com.example.hms.dto.SuperDto;
import com.example.hms.entity.Student;
import com.example.hms.service.custom.StudentService;
import org.hibernate.Session;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    StudentDao studentDao;

    public StudentServiceImpl() {
        studentDao = DaoFactory.getDaoFactory().getDao(DaoTypes.StudentsDao);
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
    public List<Student> getAll(Session session) {
        return null;
    }
}
