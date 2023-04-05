package com.example.hms.service.custom;

import com.example.hms.dto.StudentDto;
import com.example.hms.entity.Student;
import com.example.hms.entity.User;
import com.example.hms.service.SuperService;
import org.hibernate.Session;

import java.util.List;

public interface StudentService extends SuperService <StudentDto> {
    List getUnpaidStudents(Session session);
}
