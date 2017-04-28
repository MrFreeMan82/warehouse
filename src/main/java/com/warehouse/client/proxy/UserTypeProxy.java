package com.warehouse.client.proxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;
import com.warehouse.server.entity.UserType;

/**
 * Created by Дима on 28.04.2017.
 *
 */
@ProxyFor(UserType.class)
public interface UserTypeProxy extends ValueProxy
{
    String getName();
    void setName(String name);
}
