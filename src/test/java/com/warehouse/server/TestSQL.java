package com.warehouse.server;

import com.warehouse.shared.dto.UserDetail;
import com.warehouse.shared.request.Request;
import com.warehouse.shared.request.Type;
import com.warehouse.shared.dto.DTO;
import com.warehouse.shared.dto.ListDTO;
import com.warehouse.shared.dto.MenuItem;
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
        Request request = new Request(Type.USER_LIST, new UserDetail());

        ListDTO listDTO = (ListDTO) service.selectList(request);

        listDTO.getList().forEach(item-> System.out.println(item.toString()));
    }
}
