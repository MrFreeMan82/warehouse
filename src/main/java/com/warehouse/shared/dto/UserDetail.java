package com.warehouse.shared.dto;

import com.warehouse.server.EntityLocator;
import com.warehouse.server.entity.CustomEntity;
import com.warehouse.server.entity.UserDetailEntity;

import java.io.Serializable;


/**
 * Created by Дима on 20.04.2017.
 *
 */



@EntityLocator(value = UserDetailEntity.class)
public final class UserDetail extends DTO implements Serializable
{
    private UserType type;
    private String name;
    private String password;

    public UserDetail(){}

    public UserDetail(UserDetailEntity userDetail){
        copyEntity(userDetail);
    }

    public UserType getUserType() { return type; }
    public UserDetail setUserType(UserType type) { this.type = type; return this;}

    public String getName() { return name; }
    public UserDetail setName(String name) { this.name = name; return this;}


    public String getPassword() { return password; }
    public UserDetail setPassword(String password) { this.password = password; return this;}

    @Override
    public DTO copyEntity(CustomEntity entity) {
        UserDetailEntity userDetail = (UserDetailEntity) entity;
        super.setId(userDetail.getId());
        type = new UserType(userDetail.getUserType());
        name = userDetail.getName();
        password = userDetail.getPassword();
        return this;
    }
}
