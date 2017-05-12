package com.warehouse.shared.dto;

import com.warehouse.server.entity.UserType;

import java.io.Serializable;
import java.nio.charset.Charset;


/**
 * Created by Дима on 27.04.2017.
 *
 */

public class UserTypeDTO extends DTO implements Serializable
{
    private Long id;
    private byte[] name;

    public UserTypeDTO(){}
    public UserTypeDTO(UserType userType) {
        id = userType.getId();
        name = userType.getName().getBytes();
    }

    public Long getId() {return id; }
    public void setId(Long id) { this.id = id;   }

    public String getName() {  return new String(name, Charset.forName("UTF-8"));   }
    public void setName(String name) { this.name = name.getBytes(); }
}


