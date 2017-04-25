package com.warehouse.server.dao;

import com.warehouse.server.Hib;
import com.warehouse.server.entity.UserDetail;
import org.hibernate.Session;

/**
 * Created by Дима on 23.04.2017.
 *
 */

public class UserDetailDAO
{
    public String save(UserDetail userDetail)
    {
        Session session = Hib.getSession();
        session.beginTransaction();
        session.persist(userDetail);
        session.getTransaction().commit();
        session.close();
        return "Hello " + userDetail.getName();
    }

    public UserDetail findById(int id)
    {
        return null;
    }
}
