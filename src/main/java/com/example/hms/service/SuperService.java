package com.example.hms.service;

import com.example.hms.dto.SuperDto;
import com.example.hms.entity.SuperEntity;
import org.hibernate.Session;

import java.util.List;

public interface SuperService<T extends SuperDto> {
    Boolean save(T entity);

    Boolean update(T entity);

    Boolean delete(T entity);

    T view(T entity) throws RuntimeException;

    List<T> getAll();
    String getLastId();
}
