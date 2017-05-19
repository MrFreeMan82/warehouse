package com.warehouse.server;

import com.warehouse.shared.dto.DTO;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Дима on 18.05.2017.
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface DTOLocator
{
    Class<? extends DTO> value();
}
