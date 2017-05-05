package com.warehouse.server.dao;


import com.warehouse.shared.entity.Base;

import java.util.List;

/**
 * Created by Дима on 28.04.2017.
 *
 */

public abstract class DAO
{
    public abstract List<? extends Base> querySelect(String namedQuery, Base params);
}
