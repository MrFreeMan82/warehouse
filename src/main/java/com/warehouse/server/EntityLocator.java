package com.warehouse.server;

import com.warehouse.server.entity.CustomEntity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Дима on 12.05.2017.
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface EntityLocator
{
    Class<? extends CustomEntity> value();
}
