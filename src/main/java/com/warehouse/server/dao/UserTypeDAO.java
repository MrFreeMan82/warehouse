package com.warehouse.server.dao;

import com.warehouse.shared.entity.UserType;
import com.warehouse.shared.transition.Transition;

/**
 * Created by Дима on 27.04.2017.
 *
 */

public class UserTypeDAO extends DAO
{
    {
        transitions.clear();
        transitions.add(new Transition<>(UserType.GET_ALL_USER_TYPES, (example)->database.selectAllUserType()));
    }
}
