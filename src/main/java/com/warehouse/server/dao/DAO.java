package com.warehouse.server.dao;


import com.warehouse.shared.entity.Base;

/**
 * Created by Дима on 28.04.2017.
 *
 */

public abstract class DAO
{
    public abstract Base findEntity(String namedQuery, Base params);
}
