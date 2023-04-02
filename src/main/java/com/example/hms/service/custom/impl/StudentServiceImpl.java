package com.example.hms.service.custom.impl;

import com.example.hms.dao.DaoFactory;
import com.example.hms.dao.custom.StudentDao;
import com.example.hms.dao.util.DaoTypes;
import com.example.hms.dto.StudentDto;
import com.example.hms.entity.Student;
import com.example.hms.service.custom.StudentService;
import com.example.hms.service.util.Converter;
import org.hibernate.Session;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao;

    public StudentServiceImpl() {
        studentDao = DaoFactory.getDaoFactory().getDao(DaoTypes.StudentsDao);
    }

    @Override
    public Boolean save(StudentDto dto, Session session) {
        Student student = new Student();

        student.setStudent_id(dto.getStudent_id());
        student.setName(dto.getName());
        student.setAddress(dto.getAddress());
        student.setContact_no(dto.getContact_no());
        student.setGender(dto.getGender());
        student.setDate(Date.valueOf(dto.getDob()));

        return (studentDao.save(student, session));
    }

    @Override
    public Boolean update(StudentDto dto, Session session) {
        return null;
    }

    @Override
    public Boolean delete(StudentDto dto, Session session) {
        return null;
    }

    @Override
    public StudentDto view(StudentDto dto, Session session) {
        return null;
    }

    @Override
    public List<StudentDto> getAll(Session session) throws RuntimeException {

        List<Student> studentList = studentDao.getAll(session);
//        studentDao.getLastId(session);
        if (studentList.size() > 0) {
            return studentList.stream().map(student -> Converter.getInstance().toStudentDto(student)).collect(Collectors.toList());
        }
        throw new RuntimeException("Empty Student list!");
    }

    @Override
    public String getLastId(Session session) {
        String lastId = studentDao.getLastId(session);
        //  System.out.println(lastId);
        if (lastId == null) {
            return "IT000001";
        } else {
            String[] split = lastId.split("[I][T]");
            int lastDigits = Integer.parseInt(split[1]);
            lastDigits++;
            return (String.format("IT%06d", lastDigits));
        }
    }
}
