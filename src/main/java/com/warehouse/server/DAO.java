package com.warehouse.server;

import com.warehouse.server.entity.CustomEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Created by Дима on 12.05.2017.
 *
 */

abstract class DAO
{
    private static final SessionFactory factory = new Configuration().configure().buildSessionFactory();

    List<? extends CustomEntity> internalSelect(String sql, Class<? extends CustomEntity> clazz)
    {
        try(Session session = factory.openSession()) {
            return session.createQuery(sql, clazz).list();
        }
    }
}
