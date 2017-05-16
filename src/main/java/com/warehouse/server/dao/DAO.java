package com.warehouse.server.dao;

import com.warehouse.shared.action.BaseAction;
import com.warehouse.shared.dto.DTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Created by Дима on 12.05.2017.
 *
 */

public abstract class DAO<Find extends DTO, Example extends DTO, CRUD extends DTO> implements BaseAction<Find, Example, CRUD>{
    private static final SessionFactory factory = new Configuration().configure().buildSessionFactory();

    <Entity>List<Entity> internalSelect(String sql, Class<Entity> clazz)
    {
        try(Session session = factory.openSession()) {
            return session.createNativeQuery(sql, clazz).list();
        }
    }
}
