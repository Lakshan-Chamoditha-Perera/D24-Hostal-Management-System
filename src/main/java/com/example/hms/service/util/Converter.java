package com.example.hms.service.util;

import com.example.hms.dto.StudentDto;
import com.example.hms.dto.UserDto;
import com.example.hms.entity.Student;
import com.example.hms.entity.User;

public class Converter {
    private static Converter converter;

    private Converter() {

    }

    public static Converter getInstance() {
        return converter == null ? converter = new Converter() : converter;
    }

    public UserDto toUserDto(User entity) {
        return new UserDto(entity.getId(), entity.getPassword(), entity.getPasswordHint());
    }

    public User toUserEntity(UserDto dto) {
        return new User(dto.getId(), dto.getPassword(), dto.getPasswordHint());
    }

    public StudentDto toStudentDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setStudent_id(student.getStudent_id());
        studentDto.setName(student.getName());
        studentDto.setContact_no(student.getContact_no());
        studentDto.setDob(String.valueOf(student.getDate()));
        studentDto.setGender(student.getGender());

        return studentDto;
    }
}
