package com.example.hms.dao.custom;

import com.example.hms.entity.*;
import org.hibernate.Session;

import java.util.List;

public interface StudentDao extends CrudDao<Student> {
    List<Student> searchStudentByText(String text, Session session);
}
