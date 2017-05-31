package com.warehouse.shared.dto;


import com.warehouse.server.EntityLocator;
import com.warehouse.server.entity.CustomEntity;
import com.warehouse.server.entity.UserSessionEntity;
import com.warehouse.shared.Utils;

import java.io.Serializable;


/**
 * Created by Дима on 02.05.2017.
 *
 */
@EntityLocator(read = UserSessionEntity.class)
public final class UserSession extends DTO implements Serializable
{
    private UserDetail user;
    private String key;

    public UserSession(){}
    public UserSession(UserSessionEntity session) {
        copyEntity(session);
    }
    public UserSession(UserDetail user, String key){
        this.user = user;
        this.key = key;
    }

    public UserDetail getUser() { return user; }
    public void setUser(UserDetail user) { this.user = user; }


    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }

    @Override
    public DTO copyEntity(CustomEntity entity) {
        UserSessionEntity session = (UserSessionEntity) entity;
        super.setId(session.getId());
        user = new UserDetail(session.getUser());
        key = session.getKey();
        return this;
    }

    @Override
    public String toString() {
        return Utils.format("{id} {user_name} {key}", getId(), user == null ? null: user.getName());
    }
}
