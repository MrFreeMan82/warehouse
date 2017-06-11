package com.warehouse.server;

import com.warehouse.shared.Utils;
import com.warehouse.shared.dto.*;
import com.warehouse.shared.dto.ServerException;
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

        if((dto != null) && !(dto instanceof ServerException))
        {
            UserSession sessionDTO = (UserSession) dto;
            UserDetail userDetailDTO = sessionDTO.getUser();
            System.out.println(userDetailDTO.getName());
        }
        else
            System.out.println(dto.getRequest().name() + ":" + ((ServerException) dto).getMsg());
        assertNotNull(dto);
    }

    public void testLoginByPassword()
    {
        DAOService service = new DAOService();
        String passw = Utils.hashString("12345678");
        DTO dto = service.login("password="+ passw);

        if((dto != null) && !(dto instanceof ServerException)) {
            UserSession session = (UserSession) dto;
            System.out.println(session.getKey() + ":"+ session.getUser().getName());
        }
        else
            System.out.println(dto.getRequest().name() + ":" + ((ServerException) dto).getMsg());
        assertTrue(dto instanceof UserSession);
    }
}
