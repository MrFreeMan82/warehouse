package com.warehouse.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.warehouse.client.service.AppRemoteService;
import com.warehouse.server.dao.DAO;
import com.warehouse.server.dao.UserTypeDAO;
import com.warehouse.shared.DAOEnum;
import com.warehouse.shared.entity.Base;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дима on 28.04.2017.
 *
 */

public class ServiceController extends RemoteServiceServlet implements AppRemoteService
{

    private class Args
    {
        private Class[] argClasses;
        private Object[] args;

        Args(String jsonArg)
        {
            if((jsonArg == null) || (jsonArg.equals("")))
            {
                args = new Object[]{};
                argClasses = new Class[]{};
            }
        }
    }

    @Override
    public List<? extends Base> entityRequest(DAOEnum Dao, String methodName, String jsonArgs)
    {
        try {
            // define dao class
            switch (Dao) {
                case USER_TYPE: {
                    DAO newDao = new UserTypeDAO();
                    Args args = new Args(jsonArgs);
                    Method method = newDao.getClass().getMethod(methodName, args.argClasses);

                    @SuppressWarnings("unchecked")
                    List<? extends Base> list = (ArrayList<? extends Base>) method.invoke(newDao, args.args);

                    return list;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }
}
