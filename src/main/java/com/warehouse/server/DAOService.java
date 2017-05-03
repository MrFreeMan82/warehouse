package com.warehouse.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.warehouse.server.dao.DAO;
import com.warehouse.client.Service;
import com.warehouse.shared.entity.Base;


/**
 * Created by Дима on 30.04.2017.
 *
 */

public class DAOService extends RemoteServiceServlet implements Service
{

    private DAO getDAOByClass(Class cls) throws Exception
    {
        if(!cls.isAnnotationPresent(DAOLocator.class))
            throw new Exception("Unknown Entity " + cls.getName());

        DAOLocator locator = (DAOLocator) cls.getAnnotation(DAOLocator.class);

        return locator.value().newInstance();
    }


    @Override
    public Base findEntityBy(String sessionKey, String namedQuery, Base params)
    {
        System.out.println("Server: looking for an entity class " + params.getClass().getName());
        try {
            DAO dao = getDAOByClass(params.getClass());
            System.out.println("Server: Class dao is " + dao.getClass().getName());
            return dao.findEntity(namedQuery, params);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
