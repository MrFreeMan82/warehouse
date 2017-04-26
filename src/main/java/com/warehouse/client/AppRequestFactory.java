package com.warehouse.client;


import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;
import com.warehouse.client.proxy.UserDetailProxy;
import com.warehouse.server.dao.DAOLocator;
import com.warehouse.server.dao.UserDetailDAO;

import java.util.List;

/**
 * Created by Дима on 23.04.2017.
 *
 */

public interface AppRequestFactory extends RequestFactory
{
    @Service(value = UserDetailDAO.class, locator = DAOLocator.class)
    interface UserDetailContext extends RequestContext
    {
        Request<List<UserDetailProxy>> getAllUsers();
        Request<UserDetailProxy> findById(int id);
        Request<String> save(UserDetailProxy userDetail);
    }

    UserDetailContext userDetailContext();
}
