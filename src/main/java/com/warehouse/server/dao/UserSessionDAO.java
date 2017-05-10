package com.warehouse.server.dao;

import com.warehouse.shared.entity.UserSession;
import com.warehouse.shared.transition.Transition;



/**
 * Created by Дима on 02.05.2017.
 *
 */

public class UserSessionDAO extends DAO
{
    {
      transitions.clear();
      transitions.add(new Transition<>(UserSession.FIND_SESSION_BY_KEY,
              (example) -> database.selectSessionByKey(example)));
    }
}
