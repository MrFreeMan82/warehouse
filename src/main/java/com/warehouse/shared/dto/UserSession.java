package com.warehouse.shared.dto;


import com.warehouse.server.entity.UserSessionEntity;

import java.io.Serializable;


/**
 * Created by Дима on 02.05.2017.
 *
 */

public class UserSession extends DTO implements Serializable
{
    private UserDetail user;
    private String key;

    public UserSession(){}
    public UserSession(UserSessionEntity session)
    {
        super.setId(session.getId());
        user = new UserDetail(session.getUser());
        key = session.getKey();
    }

    public UserDetail getUser() { return user; }
    public void setUser(UserDetail user) { this.user = user; }


    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }
}
