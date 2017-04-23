package com.warehouse.client.context;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.warehouse.client.proxy.UserDetailProxy;
import com.warehouse.server.dao.UserDetailDAO;
import com.warehouse.server.dao.DAOLocator;

/**
 * Created by Дима on 23.04.2017.
 *
 */

@Service(value = UserDetailDAO.class, locator = DAOLocator.class)
public interface UserDetailContext extends RequestContext
{
    Request<UserDetailProxy> findById(int id);

    Request<String> save(UserDetailProxy userDetail);
}
