package com.example.hms.dao.custom.impl;

import com.example.hms.dao.custom.QueryDao;
import com.example.hms.entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class QueryDaoImpl implements QueryDao {
    @Override
    public List<Student> getUnpaidStudents(Session session) {
        Query query = session.createQuery("SELECT DISTINCT s FROM Student s LEFT JOIN s.reservationList r LEFT JOIN r.room rm WHERE r.status = 'un-paid'");
        return (List<Student>) query.list();
    }
}
