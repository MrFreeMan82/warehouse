package com.warehouse.shared;

/**
 * Created by Дима on 28.04.2017.
 *
 */

public enum DAOEnum
{
    USER_TYPE("com.warehouse.server.dao.UserTypeDAO");

    private String dao;

    DAOEnum(String dao) {this.dao = dao;}

    public String getDaoClassName(){return dao;}
}
