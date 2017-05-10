package com.warehouse.server.dao;

import com.warehouse.shared.entity.UserType;

/**
 * Created by Дима on 27.04.2017.
 *
 */

public class UserTypeDAO extends DAO
{
    {
        if(! transition.containsKey(UserType.GET_ALL_USER_TYPES))
            transition.put(UserType.GET_ALL_USER_TYPES, (example)->database.selectAllUserType());
    }
}
