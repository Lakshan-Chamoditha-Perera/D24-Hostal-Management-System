package com.example.hms.dao.custom;

import com.example.hms.dao.SuperDao;
import com.example.hms.entity.SuperEntity;
import org.hibernate.Session;

import java.util.List;

public interface CrudDao<T extends SuperEntity> extends SuperDao {
    Boolean save(T entity, Session session);

    Boolean update(T entity, Session session);

    Boolean delete(T entity ,Session session);

    T view(T entity,Session session);

    List<T> getAll(Session session);
}
