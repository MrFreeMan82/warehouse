package com.warehouse.server;

import com.warehouse.server.entity.CustomEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.procedure.ProcedureCall;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
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

    CustomEntity internalLoad(Long id, Class<? extends CustomEntity> clazz) {
        try (Session session = factory.openSession()){
            return session.load(clazz, id);
        }
    }

    Long internalInsert(CustomEntity entity) {

        Long id;
        try(Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                id = (Long) session.save(entity);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
        return id;
    }

    void internalUpdate(CustomEntity entity){

        try(Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.update(entity);
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

    void internalProcedureCall(String name, Object ... params){
        System.out.println(name);

        try(Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try{
                StoredProcedureQuery procedureQuery = session.createStoredProcedureQuery(name);
                if(params.length > 0) {
                    for(int i = 0; i < params.length; i++)
                        procedureQuery.registerStoredProcedureParameter(i, params[i].getClass(),ParameterMode.IN)
                                .setParameter(i, params[i]);
                }
                procedureQuery.execute();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }
}
