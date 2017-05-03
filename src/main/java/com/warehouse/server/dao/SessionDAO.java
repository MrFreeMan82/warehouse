package com.warehouse.server.dao;

import com.warehouse.server.Hibernate;
import com.warehouse.shared.entity.Base;
import com.warehouse.shared.entity.Session;

import java.util.List;


/**
 * Created by Дима on 02.05.2017.
 *
 */

public class SessionDAO extends DAO
{
    @Override
    public Base findEntity(String namedQuery, Base params)
    {
        switch (namedQuery)
        {
            case Session.FIND_BY_KEY:

                    try(org.hibernate.Session session = Hibernate.openSession())
                    {
                        Session parameters = (Session) params;

                        System.out.println("Server: param key=" + parameters.getKey());
                        List<Session> fromBD = session.
                                createNamedQuery(namedQuery, Session.class)
                                .setParameter("key", parameters.getKey())
                                .list();

                        return fromBD.size() == 1? fromBD.get(0): null;
                    } catch (Exception e){
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }

            default: return null;
        }
    }
}
