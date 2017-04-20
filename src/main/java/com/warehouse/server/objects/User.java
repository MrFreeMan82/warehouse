package com.warehouse.server.objects;

/**
 * Created by Дима on 20.04.2017.
 *
 */
public class User
{
    private int id;
    private int user_type_id;
    private String name;
    private String password;

    public int getId() { return id;  }
    public void setId(int id) { this.id = id; }

    public int getUser_type_id() { return user_type_id; }
    public void setUser_type_id(int user_type_id) { this.user_type_id = user_type_id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }


    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
