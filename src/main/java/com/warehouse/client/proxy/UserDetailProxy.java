package com.warehouse.client.proxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;
import com.warehouse.server.entity.UserDetail;

/**
 * Created by Дима on 23.04.2017.
 *
 */
@ProxyFor(value = UserDetail.class)
public interface UserDetailProxy extends ValueProxy
{
    Long getId();
    void setId(Long id);

    Long getUserType();
    void setUserType(Long userType);
    //ToDo add user type entity with dao

    String getName();
    void setName(String name);

    String getPassword();
    void setPassword(String password);
}
