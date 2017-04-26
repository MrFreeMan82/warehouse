package com.warehouse.server.entity;


import com.warehouse.shared.constraint.UserDetailConstraint;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Created by Дима on 20.04.2017.
 *
 */

public class UserDetail
{
    private Long id;

    @NotNull
    private Long userType;

    @NotNull
    @Size(min = UserDetailConstraint.MIN_USER_NAME, max = UserDetailConstraint.MAX_USER_NAME)
    private String name;

    @NotNull
    @Size(min = UserDetailConstraint.MIN_PASSWORD, max = UserDetailConstraint.MAX_PASSWORD)
    private String password;

    public Long getId() { return id;  }
    public void setId(Long id) { this.id = id; }

    public Long getUserType() { return userType; }
    public void setUserType(Long userType) { this.userType = userType; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }


    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
