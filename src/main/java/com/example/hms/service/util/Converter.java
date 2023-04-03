package com.example.hms.service.util;

import com.example.hms.dto.RoomDto;
import com.example.hms.dto.StudentDto;
import com.example.hms.dto.UserDto;
import com.example.hms.entity.Room;
import com.example.hms.entity.Student;
import com.example.hms.entity.User;

import java.sql.Date;

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
        studentDto.setAddress(student.getAddress());
        return studentDto;
    }
    public Student toStudentEntity(StudentDto dto) {
        Student studentEntity = new Student();
        studentEntity.setStudent_id(dto.getStudent_id());
        studentEntity.setName(dto.getName());
        studentEntity.setContact_no(dto.getContact_no());
        studentEntity.setDate(Date.valueOf(String.valueOf(dto.getDob())));
        studentEntity.setGender(dto.getGender());
        studentEntity.setAddress(dto.getAddress());
        return studentEntity;
    }

    public RoomDto toRoomDto(Room entity) {
        RoomDto dto = new RoomDto();
        dto.setRoom_type_id(entity.getRoom_type_id());
        dto.setType(entity.getType());
        dto.setKey_money(entity.getKey_money());
        dto.setQty(entity.getQty());
        return dto;
    }

    public Room toRoomEntity(RoomDto dto) {
        Room entity = new Room();
        entity.setRoom_type_id(dto.getRoom_type_id());
        entity.setType(dto.getType());
        entity.setKey_money(dto.getKey_money());
        entity.setQty(dto.getQty());
        return entity;
    }
}
