package com.warehouse.server.dao;

import com.warehouse.server.object.UserDetail;

/**
 * Created by Дима on 23.04.2017.
 *
 */

public class UserDetailDAO
{
    public String save(UserDetail userDetail)
    {
        return "Hello " + userDetail.getName();
    }

    public UserDetail findById(int id)
    {
        return null;
    }
}
