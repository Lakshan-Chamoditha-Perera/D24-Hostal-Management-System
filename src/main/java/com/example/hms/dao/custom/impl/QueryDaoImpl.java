package com.example.hms.dao.custom.impl;

import com.example.hms.dao.custom.QueryDao;
import org.hibernate.Session;

import java.util.List;

public class QueryDaoImpl implements QueryDao {
    @Override
    public List getUnpaidStudents(Session session) {
        return null;
    }
}
