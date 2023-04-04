package com.example.hms.service.custom.impl;

import com.example.hms.dao.DaoFactory;
import com.example.hms.dao.custom.StudentDao;
import com.example.hms.dao.util.DaoTypes;
import com.example.hms.dto.StudentDto;
import com.example.hms.entity.Student;
import com.example.hms.service.custom.StudentService;
import com.example.hms.service.util.Converter;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao;

    public StudentServiceImpl() {
        studentDao = DaoFactory.getDaoFactory().getDao(DaoTypes.StudentsDao);
    }

    @Override
    public Boolean save(StudentDto dto, Session session) throws RuntimeException {
        Transaction transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            studentDao.save(Converter.getInstance().toStudentEntity(dto), session);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException("Student not added");
        }
    }

    @Override
    public Boolean update(StudentDto dto, Session session) {
        Transaction transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            studentDao.update(Converter.getInstance().toStudentEntity(dto), session);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException("Student not updated");
        }
    }

    @Override
    public Boolean delete(StudentDto dto, Session session) throws RuntimeException {
        Transaction transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            studentDao.delete(Converter.getInstance().toStudentEntity(dto), session);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException("Student not Deleted");
        }
    }

    @Override
    public StudentDto view(StudentDto dto, Session session) {
        return null;
    }

    @Override
    public List<StudentDto> getAll(Session session) throws RuntimeException {
        List<Student> studentList = studentDao.getAll(session);
        if (studentList.size() > 0) {
            return studentList.stream().map(student -> Converter.getInstance().toStudentDto(student)).collect(Collectors.toList());
        }
        throw new RuntimeException("Empty Student list!");
    }

    @Override
    public String getLastId(Session session) {
        String lastId = studentDao.getLastId(session);
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
