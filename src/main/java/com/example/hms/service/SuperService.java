package com.example.hms.service;

import com.example.hms.entity.SuperEntity;
import org.hibernate.Session;

import java.util.List;

public interface SuperService<T extends SuperEntity> {
    Boolean save(T entity, Session session);

    Boolean update(T entity, Session session);

    Boolean delete(T entity, Session session);

    T view(T entity, Session session);

    List<T> getAll(Session session);
}
