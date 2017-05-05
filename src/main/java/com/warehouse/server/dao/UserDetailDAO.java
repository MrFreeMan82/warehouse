package com.warehouse.server.dao;

import com.warehouse.server.Hibernate;
import com.warehouse.shared.entity.Base;
import com.warehouse.shared.entity.UserDetail;

import org.hibernate.Session;

import java.util.List;


/**
 * Created by Дима on 23.04.2017.
 *
 */

public class UserDetailDAO extends DAO
{
    public String save(UserDetail userDetail)
    {
        Session session = Hibernate.openSession();
        session.beginTransaction();
        session.persist(userDetail);
        session.getTransaction().commit();
        session.close();
        return "Hello " + userDetail.getName();
    }

    @Override
    public List<? extends Base> querySelect(String namedQuery, Base params) {
        return null;
    }
}
