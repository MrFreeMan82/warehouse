package com.warehouse.server;

import com.warehouse.shared.dto.*;
import com.warehouse.shared.request.Request;
import com.warehouse.shared.request.SQL;
import junit.framework.TestCase;


/**
 * Created by Дима on 19.05.2017.
 *
 */

public class TestSQL  extends TestCase
{
    public void testMainMenu(){

        DAOService service = new DAOService();
        Request request = new Request(SQL.MAIN_MENU, new MenuItem());

        HashedDTO list = (HashedDTO) service.selectList(request);


        for(DTO item: list.getList()) {

            MenuItem menuItem = (MenuItem) item;
            MenuItem parent = list.get(menuItem.getParent().getId());

            System.out.println(menuItem.getName() + ":" + menuItem.isLeaf() + ":" + parent.getName());
        }


        assertEquals(list.getClass(), HashedDTO.class);
    }

    public void testUserList() {

        DAOService service = new DAOService();
        Request request = new Request(SQL.USER_LIST, new UserDetail());

        HashedDTO hashedDTO = (HashedDTO) service.selectList(request);

        hashedDTO.getList().forEach(item-> System.out.println(item.toString()));
    }

    public void testArtiqules() {

        DAOService service = new DAOService();
        Request request = new Request(SQL.GROUPS_WITH_ARTIQULES, new Group());
        HashedDTO hashedDTO = (HashedDTO) service.selectList(request);
        hashedDTO.getList().forEach(item-> System.out.println(item.toString()));
    }

    public void testPersist(){

        UserDetail userDetail = new UserDetail();
        userDetail.setName("Bill");
        userDetail.setPassword("123gfgdfgfdgg");
        userDetail.setType(1L);

        DAOService service = new DAOService();

        Request request = new Request(SQL.INSERT_USER, userDetail);

         service.insert(request);
    }

    public void testUpdate() {

        DAOService service = new DAOService();

        UserDetail example = new UserDetail();
        example.setPassword("123gfgdfg");

        Request request = new Request(SQL.USER_BY_PASSWORD, example);

        UserDetail user = (UserDetail) service.select(request);

        user.setName("RIMA");

        request = new Request(SQL.UPDATE_USER, user);
        DTO dto = service.update(request);

        assertFalse(dto instanceof Empty);
    }

    public void testDelete(){

        DAOService service = new DAOService();
        UserDetail example = new UserDetail();
        example.setPassword("123gfgdfg");

        UserDetail user = (UserDetail) service.select(new Request(SQL.USER_BY_PASSWORD, example));
        service.delete(new Request(SQL.DELETE_USER, user));

    }
}
