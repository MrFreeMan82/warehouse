package com.warehouse.shared.dto;

import com.warehouse.server.EntityLocator;
import com.warehouse.server.entity.CustomEntity;
import com.warehouse.server.entity.UserViewEntity;
import com.warehouse.shared.Utils;

/**
 * Created by Дима on 24.05.2017.
 *
 */
@EntityLocator(read = UserViewEntity.class)
public final class UserView extends UserDetail {

    private String type_name;
    private String status_name;

    public String getTypeName() {return type_name;}
    public String getStatusName() {return status_name;}

    @Override
    public DTO copyEntity(CustomEntity entity) {

        UserViewEntity userViewEntity = (UserViewEntity) entity;

        setId(userViewEntity.id);
        setStatus(userViewEntity.status_id);
        setType(userViewEntity.type_id);
        setName(userViewEntity.name);
        setPassword(userViewEntity.hashed_password);
        type_name = userViewEntity.type_name;
        status_name = userViewEntity.status_name;
        return this;
    }

    @Override
    public String toString() {
        return Utils.format("{id} {name} {status} {type}", getId(), getName(), status_name, type_name);
    }
}
