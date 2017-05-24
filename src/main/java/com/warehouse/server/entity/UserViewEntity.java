package com.warehouse.server.entity;

import com.warehouse.server.DTOLocator;
import com.warehouse.shared.dto.UserView;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Дима on 24.05.2017.
 *
 */
@Entity(name = "USER_VIEW")
@DTOLocator(value = UserView.class)
public final class UserViewEntity extends CustomEntity {

    @Id
    public long id;
    public long type_id;
    public long status_id;
    public String name;
    public String hashed_password;
    public String type_name;
    public String status_name;
}
