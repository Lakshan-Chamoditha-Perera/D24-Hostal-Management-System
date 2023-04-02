package com.example.hms.service.util;

import com.example.hms.dto.UserDto;
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

}
