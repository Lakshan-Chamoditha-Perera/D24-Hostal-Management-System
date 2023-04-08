package com.example.hms.service.custom.impl;

import com.example.hms.dao.DaoFactory;
import com.example.hms.dao.custom.QueryDao;
import com.example.hms.dao.custom.StudentDao;
import com.example.hms.dao.util.DaoTypes;
import com.example.hms.dto.StudentDto;
import com.example.hms.entity.Student;
import com.example.hms.service.custom.StudentService;
import com.example.hms.service.util.Converter;
import com.example.hms.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao;
    private final QueryDao queryDao;

    public StudentServiceImpl() {
        studentDao = DaoFactory.getInstance().getDao(DaoTypes.StudentsDao);
        queryDao = DaoFactory.getInstance().getDao(DaoTypes.QueryDao);
    }

    @Override
    public Boolean save(StudentDto dto) throws RuntimeException {

        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
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
    public Boolean update(StudentDto dto) {

        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
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
    public Boolean delete(String id) throws RuntimeException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            studentDao.delete(id, session);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException("Student not Deleted");
        }
    }

    @Override
    public StudentDto view(String id) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        try (session) {
            Student student = studentDao.view(id, session);
            if (student != null) return Converter.getInstance().toStudentDto(student);
            throw new RuntimeException("Student not found");
        } catch (RuntimeException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public List<StudentDto> getAll() throws RuntimeException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        try (session) {
            List<Student> studentList = studentDao.getAll(session);
            if (studentList.size() > 0) {
                return studentList.stream().map(student -> Converter.getInstance().toStudentDto(student)).collect(Collectors.toList());
            }
        }
        throw new RuntimeException("Empty Student list!");
    }

    @Override
    public String getLastId() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        try (session) {
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

    @Override
    public List<StudentDto> getUnpaidStudents() throws RuntimeException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        List<Student> unpaidStudents = queryDao.getUnpaidStudents(session);
        if (unpaidStudents.size() > 0) {
            return unpaidStudents.stream().map(student -> Converter.getInstance().toStudentDto(student)).collect(Collectors.toList());
        }
        throw new RuntimeException("No any unpaid case yet!");
    }

    @Override
    public List<StudentDto> searchStudentByText(String text) throws RuntimeException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        try (session) {
            List<Student> searchStudentByText = studentDao.searchStudentByText(text, session);
            if (searchStudentByText.size() > 0) {
                return searchStudentByText.stream().map(student -> Converter.getInstance().toStudentDto(student)).collect(Collectors.toList());
            }
            throw new RuntimeException("No any match found");
        }
    }
}
