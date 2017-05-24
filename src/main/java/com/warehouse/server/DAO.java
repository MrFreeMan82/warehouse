package com.warehouse.server;

import com.warehouse.server.entity.CustomEntity;
import com.warehouse.server.entity.UserDetailEntity;
import com.warehouse.shared.dto.DTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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

    void internalInsert(CustomEntity entity) {

        try(Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.persist(entity);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    void internalUpdate(CustomEntity entity){

        try(Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.update(entity);
                session.flush();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    void internalDelete(CustomEntity entity) {

        try(Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try{
                session.delete(entity);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }
}
