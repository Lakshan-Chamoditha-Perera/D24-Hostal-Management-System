package com.example.hms.dao.custom;

import com.example.hms.dao.SuperDao;
import org.hibernate.Session;

import java.util.List;

public interface QueryDao extends SuperDao {
    List getUnpaidStudents(Session session);
}
