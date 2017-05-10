package com.warehouse.server.dao;

import com.warehouse.shared.entity.UserSession;

/**
 * Created by Дима on 02.05.2017.
 *
 */

public class UserSessionDAO extends DAO
{
    {
      if(! transition.containsKey(UserSession.FIND_SESSION_BY_KEY))
        transition.put(UserSession.FIND_SESSION_BY_KEY, (example) -> database.selectSessionByKey(example));
    }
}
