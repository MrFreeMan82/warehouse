package com.warehouse.shared.dto;

import com.warehouse.server.entity.CustomEntity;

import java.io.Serializable;

/**
 * Created by Дима on 18.05.2017.
 *
 */

public final class Empty extends DTO implements Serializable {
    private String msg;

    public Empty(){}
    public Empty(String msg){this.msg = msg;}
    public String getMsg(){return msg;}

    @Override
    public DTO copyEntity(CustomEntity entity) {
        return this;
    }
}
