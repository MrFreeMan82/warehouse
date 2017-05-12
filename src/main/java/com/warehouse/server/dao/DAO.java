package com.warehouse.server.dao;

import com.warehouse.shared.dto.DTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Created by Дима on 12.05.2017.
 *
 */

public abstract class DAO {
    private static final SessionFactory factory = new Configuration().configure().buildSessionFactory();

    <T>List<T> internalSelect(String sql, Class<T> clazz)
    {
        try(Session session = factory.openSession()) {
            return session.createNativeQuery(sql, clazz).list();
        }
    }
    public abstract List<? extends DTO> select(String queryName, DTO example);
}
