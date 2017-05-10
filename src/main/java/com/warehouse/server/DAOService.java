package com.warehouse.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.warehouse.server.dao.DAO;
import com.warehouse.client.utils.Service;
import com.warehouse.shared.entity.Base;

import java.util.List;


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
    public List<? extends Base> querySelect(String sessionKey, String queryName, Base example)
    {
        // ToDo добавить проверку sessionKey

        System.out.println("Server: mapping DAO by class " + example.getClass().getName());
        try {
            DAO dao = getDAOByClass(example.getClass());
            dao.setDatabase(Memory.getInstance());
            System.out.println("Server: DAO is " + dao.getClass().getName());
            return dao.querySelect(queryName, example);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public int queryInsert(String sessionKey, String namedQuery, Base example) {
        return 0;
    }

}
