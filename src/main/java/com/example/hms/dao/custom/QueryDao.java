package com.example.hms.dao.custom;

import com.example.hms.dao.SuperDao;
import com.example.hms.entity.Student;
import org.hibernate.Session;

import java.util.List;

public interface QueryDao extends SuperDao {
    List <Student>getUnpaidStudents(Session session);
}
