package com.warehouse.server.object;

/**
 * Created by Дима on 20.04.2017.
 *
 */
public class UserDetail
{
    private int id;
    private int userType;
    private String name;
    private String password;

    public int getId() { return id;  }
    public void setId(int id) { this.id = id; }

    public int getUserType() { return userType; }
    public void setUserType(int userType) { this.userType = userType; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }


    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
