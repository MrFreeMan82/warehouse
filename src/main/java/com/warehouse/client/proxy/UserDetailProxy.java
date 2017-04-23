package com.warehouse.client.proxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;
import com.warehouse.server.object.UserDetail;

/**
 * Created by Дима on 23.04.2017.
 *
 */
@ProxyFor(value = UserDetail.class)
public interface UserDetailProxy extends ValueProxy
{
    int getId();
    void setId(int id);

    int getUserType();
    void setUserType(int userType);
    //ToDo add user type object with dao

    String getName();
    void setName(String name);

    String getPassword();
    void setPassword(String password);
}
