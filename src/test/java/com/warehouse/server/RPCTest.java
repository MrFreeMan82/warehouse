package com.warehouse.server;

import com.warehouse.shared.entity.UserSession;
import junit.framework.TestCase;

import java.nio.charset.Charset;
import java.util.List;


/**
 * Created by Дима on 28.04.2017.
 *
 */

public class RPCTest extends TestCase
{
    public void testResource()
    {
        String sql = Resource.getSQLResource(UserSession.FIND_SESSION_BY_KEY);
        System.out.println(sql);
        assertNotNull(sql);

        String name = Resource.getStringResource("name");
        System.out.println(new String(name.getBytes(), Charset.forName("UTF-8")));
        assertNotNull(name);
    }

    public void testDAOService()
    {
        DAOService service = new DAOService();
        UserSession session = new UserSession();
        session.setKey("12");

        List list = service.querySelect(null, UserSession.FIND_SESSION_BY_KEY, session);

        assertNotNull(list);
    }
}
