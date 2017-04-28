package com.warehouse.client;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;

import com.warehouse.client.proxy.UserTypeProxy;
import com.warehouse.server.dao.DAO;
import com.warehouse.server.dao.UserTypeDAO;

import java.util.List;


/**
 * Created by Дима on 28.04.2017.
 *
 */

public interface AppRequestFactory extends RequestFactory
{
    @Service(value = UserTypeDAO.class, locator = DAO.class)
    interface UserTypeContext extends RequestContext
    {
       Request<List<UserTypeProxy>> getAllUserTypes();
    }

    UserTypeContext userTypeContext();
}
