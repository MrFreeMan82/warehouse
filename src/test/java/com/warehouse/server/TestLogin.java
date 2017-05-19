package com.warehouse.server;

import com.warehouse.shared.Utils;
import com.warehouse.shared.dto.*;
import junit.framework.TestCase;


/**
 * Created by Дима on 28.04.2017.
 *
 */

public class TestLogin extends TestCase
{

    public void testLoginByKey()
    {
        DAOService service = new DAOService();

        DTO dto = service.login("key=123");

        if((dto != null) && !(dto instanceof Empty))
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
            System.out.println(dto.getRequest().name() + ":" + ((Empty) dto).getMsg());
        assertNotNull(dto);
    }

    public void testLoginByPassword()
    {
        DAOService service = new DAOService();
        String passw = Utils.hashString("12345678");
        DTO dto = service.login("password="+ passw);

        if((dto != null) && !(dto instanceof Empty)) {
            UserSession session = (UserSession) dto;
            System.out.println(session.getKey() + ":"+ session.getUser().getName());
        }
        else
            System.out.println(dto.getRequest().name() + ":" + ((Empty) dto).getMsg());
        assertTrue(dto instanceof UserSession);
    }
}
