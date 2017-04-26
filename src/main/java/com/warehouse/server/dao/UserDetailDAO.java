package com.warehouse.server.dao;

import com.warehouse.server.Hibernate;
import com.warehouse.server.entity.UserDetail;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Дима on 23.04.2017.
 *
 */

public class UserDetailDAO
{
    public String save(UserDetail userDetail)
    {
        Session session = Hibernate.getSession();
        session.beginTransaction();
        session.persist(userDetail);
        session.getTransaction().commit();
        session.close();
        return "Hello " + userDetail.getName();
    }

    public List<UserDetail> getAllUsers()
    {
        return null;
    }

    public UserDetail findById(int id)
    {
        return null;
    }
}
