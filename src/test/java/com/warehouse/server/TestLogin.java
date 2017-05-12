package com.warehouse.server;

import com.warehouse.server.dao.LoginDAO;
import com.warehouse.shared.dto.DTO;
import com.warehouse.shared.dto.LoginDTO;
import com.warehouse.shared.dto.UserSessionDTO;
import junit.framework.TestCase;

import java.util.List;


/**
 * Created by Дима on 28.04.2017.
 *
 */

public class TestLogin extends TestCase
{
    public void testResource()
    {
        String sql = Resource.getSQL(LoginDAO.LOGIN_BY_KEY);
        System.out.println(sql);
        assertNotNull(sql);
    }

    public void testLoginByKey()
    {
        DAOService service = new DAOService();
        LoginDTO login = new LoginDTO();
        login.setKey("12");

        List<? extends DTO> list = service.querySelect(null, LoginDAO.LOGIN_BY_KEY, login);

        if(list.size() > 0)
            System.out.println(((UserSessionDTO)list.get(0)).getUser().getName());
        else
            System.out.println("Empty");
        assertEquals(1, list.size());
    }

    public void testLoginByPassword()
    {
        DAOService service = new DAOService();
        LoginDTO login = new LoginDTO();
        login.setPassword("fdsfersdfsdfs");

        List<? extends DTO> list = service.querySelect(null, LoginDAO.LOGIN_BY_PASSWORD, login);

        if(list.size() > 0)
            System.out.println(((UserSessionDTO)list.get(0)).getUser().getName());
        else
            System.out.println("Empty");
        assertEquals(1, list.size());
    }
}
