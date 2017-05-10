package com.warehouse.server.dao;


import com.warehouse.server.Database;
import com.warehouse.shared.entity.Base;
import com.warehouse.shared.function.FunctionOneArg;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Дима on 28.04.2017.
 *
 */

public abstract class DAO
{
    HashMap<String, FunctionOneArg<List<? extends Base>, Base>> transition = new HashMap<>();
    Database database;

    public void setDatabase(Database database) {this.database = database;}

    public List<? extends Base> querySelect(String queryName, Base example)
    {
        return transition.get(queryName).go(example);
    }
}
