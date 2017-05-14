package com.warehouse.server;

import com.warehouse.server.dao.LoginDAO;
import com.warehouse.server.entity.UserDetail;
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
        LoginDTO login = new LoginDTO();
        login.setKey("12");

        List<? extends DTO> list = service.querySelect(null, LoginDAO.LOGIN_BY_KEY, login);

        if(list.size() > 0)
        {
            UserSessionDTO sessionDTO = (UserSessionDTO) list.get(0);
            UserDetailDTO userDetailDTO = sessionDTO.getUser();
            System.out.println(userDetailDTO.getName());
            System.out.println(userDetailDTO.getUserType().getRuleSetDTO().getComment());
            for(RuleDTO ruleDTO: userDetailDTO.getUserType().getRuleSetDTO().getAsList())
            {
                System.out.println(ruleDTO.getOrder() + ":" + ruleDTO.getPresent() + ":" + ruleDTO.getWidgets());
            }
        }
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
