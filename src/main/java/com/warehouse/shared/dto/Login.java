package com.warehouse.shared.dto;

import com.warehouse.server.dao.DAOLocator;
import com.warehouse.server.dao.LoginDAO;

/**
 * Created by Дима on 12.05.2017.
 *
 */

@DAOLocator(value = LoginDAO.class)
public class Login extends DTO
{
    private String key;
    private String password;

    public String getKey() {return key;}
    public void setKey(String key) {this.key = key;}


    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
}
