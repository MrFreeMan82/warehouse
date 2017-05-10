package com.warehouse.server.dao;


import com.warehouse.server.Database;
import com.warehouse.shared.entity.Base;
import com.warehouse.shared.transition.FunctionOneArg;
import com.warehouse.shared.transition.Transition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дима on 28.04.2017.
 *
 */

public abstract class DAO
{
    List<Transition<String, FunctionOneArg<List<? extends Base>, Base>>> transitions = new ArrayList<>();
    Database database;

    public void setDatabase(Database database) {this.database = database;}

    public List<? extends Base> querySelect(String queryName, Base example)
    {
        for (Transition<String, FunctionOneArg<List<? extends Base>, Base>> transition: transitions)
        {
            if(transition.trigger.equals(queryName))
                    return transition.action.go(example);
        }

        return new ArrayList<>();
    }
}
