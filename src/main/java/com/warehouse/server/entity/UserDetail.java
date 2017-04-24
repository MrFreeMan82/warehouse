package com.warehouse.server.entity;


import javax.validation.constraints.NotNull;

/**
 * Created by Дима on 20.04.2017.
 *
 */
public class UserDetail
{
    private Long id;
    private Long userType;

    private String name;
    private String password;

    public Long getId() { return id;  }
    public void setId(Long id) { this.id = id; }

    public Long getUserType() { return userType; }
    public void setUserType(Long userType) { this.userType = userType; }

    @NotNull
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }


    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
