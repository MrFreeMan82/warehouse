package com.warehouse.shared.dto;

import com.warehouse.server.entity.UserDetail;

import java.io.Serializable;
import java.nio.charset.Charset;


/**
 * Created by Дима on 20.04.2017.
 *
 */

public class UserDetailDTO extends DTO implements Serializable
{
    private Long id;
    private UserTypeDTO type;
    private byte[] name;
    private String password;

    public UserDetailDTO(){}

    public UserDetailDTO(UserDetail userDetail){
        id = userDetail.getId();
        type = new UserTypeDTO(userDetail.getUserType());
        name = userDetail.getName().getBytes();
        password = userDetail.getPassword();
    }

    public Long getId() { return id;  }
    public void setId(Long id) { this.id = id; }


    public UserTypeDTO getUserType() { return type; }
    public void setUserType(UserTypeDTO type) { this.type = type; }


    public String getName() { return new String(name, Charset.forName("UTF-8")); }
    public void setName(String name) { this.name = name.getBytes(); }


    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

}
