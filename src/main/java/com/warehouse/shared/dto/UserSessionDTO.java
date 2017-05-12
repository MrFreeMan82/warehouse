package com.warehouse.shared.dto;


import com.warehouse.server.entity.UserSession;

import java.io.Serializable;


/**
 * Created by Дима on 02.05.2017.
 *
 */

public class UserSessionDTO extends DTO implements Serializable
{
    private Long id;
    private UserDetailDTO user;
    private String key;

    public UserSessionDTO(){}
    public UserSessionDTO(UserSession session)
    {
        id = session.getId();
        user = new UserDetailDTO(session.getUser());
        key = session.getKey();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }


    public UserDetailDTO getUser() { return user; }
    public void setUser(UserDetailDTO user) { this.user = user; }


    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }
}
