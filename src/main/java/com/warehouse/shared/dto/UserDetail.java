package com.warehouse.shared.dto;

import com.warehouse.server.EntityLocator;
import com.warehouse.server.entity.CustomEntity;
import com.warehouse.server.entity.UserDetailEntity;
import com.warehouse.server.entity.UserTypeEntity;
import com.warehouse.shared.Utils;

import java.io.Serializable;


/**
 * Created by Дима on 20.04.2017.
 *
 */



@EntityLocator(value = UserDetailEntity.class)
public final class UserDetail extends DTO implements Serializable
{
    private UserType type;
    private Integer status;
    private String name;
    private String password;

    public UserDetail(){}

    public UserDetail(UserDetailEntity userDetail){
        copyEntity(userDetail);
    }

    public UserType getUserType() { return type; }
    public UserDetail setUserType(UserType type) { this.type = type; return this;}

    public Integer getStatus() {return status;}
    public void setStatus(Integer status) {this.status = status;}

    public String getName() { return name; }
    public UserDetail setName(String name) { this.name = name; return this;}


    public String getPassword() { return password; }
    public UserDetail setPassword(String password) { this.password = password; return this;}

    @Override
    public CustomEntity createEntity() {

        UserDetailEntity entity = new UserDetailEntity();
        entity.setId(getId());
        entity.setUserType((UserTypeEntity) type.createEntity());
        entity.setStatus(status);
        entity.setName(name);
        entity.setPassword(password);
        return entity;
    }

    @Override
    public DTO copyEntity(CustomEntity entity) {
        UserDetailEntity userDetail = (UserDetailEntity) entity;
        super.setId(userDetail.getId());
        type = new UserType(userDetail.getUserType());
        status = userDetail.getStatus();
        name = userDetail.getName();
        password = userDetail.getPassword();
        return this;
    }

    @Override
    public String toString() {
        return Utils.format("{id} {name} {password}", getId(), name, password);
    }
}
