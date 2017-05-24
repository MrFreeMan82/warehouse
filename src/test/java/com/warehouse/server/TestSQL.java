package com.warehouse.server;

import com.warehouse.shared.dto.*;
import com.warehouse.shared.request.Request;
import com.warehouse.shared.request.Type;
import junit.framework.TestCase;


/**
 * Created by Дима on 19.05.2017.
 *
 */

public class TestSQL  extends TestCase
{
    public void testMainMenu(){

        DAOService service = new DAOService();
        Request request = new Request(Type.MAIN_MENU, new MenuItem());

        ListDTO list = (ListDTO) service.selectList(request);


        for(DTO item: list.getList()) {

            MenuItem menuItem = (MenuItem) item;
            MenuItem parent = list.get(menuItem.getParent().getId());

            System.out.println(menuItem.getName() + ":" + menuItem.isLeaf() + ":" + parent.getName());
        }


        assertEquals(list.getClass(), ListDTO.class);
    }

    public void testUserList() {

        DAOService service = new DAOService();
        Request request = new Request(Type.USER_LIST, new UserView());

        ListDTO listDTO = (ListDTO) service.selectList(request);

        listDTO.getList().forEach(item-> System.out.println(item.toString()));
    }

    public void testPersist(){

        UserDetail userDetail = new UserDetail();
        userDetail.setName("Bill");
        userDetail.setPassword("123gfgdfgfdgg");
        userDetail.setType_id(1);

        DAOService service = new DAOService();

        Request request = new Request(Type.INSERT_USER, userDetail);

         service.insert(request);
    }

    public void testUpdate() {

        DAOService service = new DAOService();

        UserDetail example = new UserDetail();
        example.setPassword("123gfgdfg");

        Request request = new Request(Type.USER_BY_PASSWORD, example);

        UserDetail user = (UserDetail) service.select(request);

        user.setName("RIMA");

        request = new Request(Type.UPDATE_USER, user);
        DTO dto = service.update(request);

        assertFalse(dto instanceof Empty);
    }

    public void testDelete(){

        DAOService service = new DAOService();
        UserDetail example = new UserDetail();
        example.setPassword("123gfgdfg");

        UserDetail user = (UserDetail) service.select(new Request(Type.USER_BY_PASSWORD, example));
        service.delete(new Request(Type.DELETE_USER, user));

    }
}
