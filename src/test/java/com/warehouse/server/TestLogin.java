package com.warehouse.server;

import com.warehouse.server.dao.LoginDAO;
import com.warehouse.shared.dto.*;
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
        Login login = new Login();
        login.setKey("12");

        DTO dto = service.selectOne(LoginDAO.LOGIN_BY_KEY, login);

        if(dto != null)
        {
            UserSession sessionDTO = (UserSession) dto;
            UserDetail userDetailDTO = sessionDTO.getUser();
            System.out.println(userDetailDTO.getName());
            System.out.println(userDetailDTO.getUserType().getRuleSet().getComment());
            for(Rule ruleDTO: userDetailDTO.getUserType().getRuleSet().getAsList())
            {
                System.out.println(ruleDTO.getOrder() + ":" + ruleDTO.getPresent() + ":" + ruleDTO.getWidgets());
            }
        }
        else
            System.out.println("Empty");
        assertNotNull(dto);
    }

    public void testLoginByPassword()
    {
        DAOService service = new DAOService();
        Login login = new Login();
        login.setPassword("12345678");

        DTO dto = service.selectOne(LoginDAO.LOGIN_BY_PASSWORD, login);

        if(dto != null) {
            UserSession session = (UserSession) dto;
            System.out.println(session.getKey() + ":"+ session.getUser().getName());
        }
        else
            System.out.println("Empty");
        assertNotNull(dto);
    }
}
