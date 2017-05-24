package com.warehouse.shared.dto;

import com.warehouse.server.EntityLocator;
import com.warehouse.server.entity.CustomEntity;
import com.warehouse.server.entity.UserTypeEntity;
import com.warehouse.shared.Utils;

import java.io.Serializable;


/**
 * Created by Дима on 27.04.2017.
 *
 */
@EntityLocator(read = UserTypeEntity.class)
public final class UserType extends DTO implements Serializable
{
    private String name;

    public UserType(){}
    public UserType(UserTypeEntity userType) {
        copyEntity(userType);
    }

    public String getName() {  return name; }
    public void setName(String name) { this.name = name; }


    @Override
    public CustomEntity createEntity() {

        UserTypeEntity entity = new UserTypeEntity();
        entity.setId(getId());
        entity.setName(name);
        return entity;
    }

    @Override
    public DTO copyEntity(CustomEntity entity)
    {
        UserTypeEntity userType = (UserTypeEntity) entity;
        super.setId(userType.getId());
        name = userType.getName();
        return this;
    }

    @Override
    public String toString() {
        return Utils.format("{id} {name}", getId(), name);
    }
}


