package com.warehouse.shared.dto;

import com.warehouse.server.EntityLocator;
import com.warehouse.server.entity.CustomEntity;
import com.warehouse.server.entity.UserDetailEntity;
import com.warehouse.shared.Utils;

import java.io.Serializable;


/**
 * Created by Дима on 20.04.2017.
 *
 */



@EntityLocator(read = UserDetailEntity.class)
public final class UserDetail extends DTO implements Serializable
{
    private Long type_id;
    private Long status;
    private String name;
    private String password;

    public UserDetail(){status = 1L;}

    public UserDetail(UserDetailEntity userDetail){
        this();
        copyEntity(userDetail);
    }

    public Long getType() {return type_id;}
    public void setType(Long type_id) {this.type_id = type_id;}

    public Long getStatus() {return status;}
    public void setStatus(Long status) {this.status = status;}

    public String getName() { return name; }
    public UserDetail setName(String name) { this.name = name; return this;}

    public String getPassword() { return password; }
    public UserDetail setPassword(String password) { this.password = password; return this;}

    @Override
    public CustomEntity createEntity() {

        UserDetailEntity entity = new UserDetailEntity();
        entity.id =getId();
        entity.type_id = type_id;
        entity.status_id = status;
        entity.name = name;
        entity.password = password;
        return entity;
    }

    @Override
    public DTO copyEntity(CustomEntity entity) {
        UserDetailEntity userDetail = (UserDetailEntity) entity;
        setId(userDetail.id);
        type_id = userDetail.type_id;
        status = userDetail.status_id;
        name = userDetail.name;
        password = userDetail.password;
        return this;
    }

    @Override
    public String toString() {
        return Utils.format("{id} {name} {password}", getId(), name, password);
    }
}
