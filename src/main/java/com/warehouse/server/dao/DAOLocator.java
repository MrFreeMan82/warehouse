package com.warehouse.server.dao;

import com.google.web.bindery.requestfactory.shared.ServiceLocator;

/**
 * Created by Дима on 23.04.2017.
 *
 */
public class DAOLocator implements ServiceLocator
{
    @Override
    public Object getInstance(Class<?> aClass)
    {
        try {
            System.out.println("New DAO request: " + aClass.getName());
            return aClass.newInstance();
        } catch (Exception e) {
            return null;
        }
    }
}
