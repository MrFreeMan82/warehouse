package com.warehouse.server;

import com.warehouse.server.dao.DAO;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Дима on 30.04.2017.
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface DAOLocator
{
    Class<?extends DAO> value();
}
