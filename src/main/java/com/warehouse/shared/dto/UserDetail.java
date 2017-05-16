package com.warehouse.shared.dto;

import com.warehouse.server.entity.UserDetailEntity;

import java.io.Serializable;


/**
 * Created by Дима on 20.04.2017.
 *
 */

public class UserDetail extends DTO implements Serializable
{
    private UserType type;
    private String name;
    private String password;

    public UserDetail(){}

    public UserDetail(UserDetailEntity userDetail){
        super.setId(userDetail.getId());
        type = new UserType(userDetail.getUserType());
        name = userDetail.getName();
        password = userDetail.getPassword();
    }

    public UserType getUserType() { return type; }
    public void setUserType(UserType type) { this.type = type; }


    public String getName() { return name; }
    public void setName(String name) { this.name = name; }


    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
