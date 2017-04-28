package com.warehouse.server;

import com.warehouse.server.dao.UserTypeDAO;
import com.warehouse.server.entity.UserType;
import junit.framework.TestCase;

import java.util.List;

/**
 * Created by Дима on 28.04.2017.
 *
 */

public class RPCTest extends TestCase
{
    public void testData()
    {
        UserTypeDAO typeDAO = new UserTypeDAO();
        List<UserType> list = typeDAO.getAllUserTypes();

        System.out.println(list.get(0).getName());
    }

    public void testReflection()
    {


    }
}
