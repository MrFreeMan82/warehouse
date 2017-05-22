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
        Request request = new Request(Type.USER_LIST, new UserDetail());

        ListDTO listDTO = (ListDTO) service.selectList(request);

        listDTO.getList().forEach(item-> System.out.println(item.toString()));
    }

    public void testPersist(){

        RuleSet ruleSet = new RuleSet();
        ruleSet.setId(0L);
        ruleSet.setPriority(0);

        UserType userType = new UserType();
        userType.setId(1L);
        userType.setRuleSet(ruleSet);
        userType.setName("Admin");

        UserDetail userDetail = new UserDetail();
        userDetail.setName("Bill");
        userDetail.setPassword("123gfgdfg");
        userDetail.setUserType(userType);

        DAOService service = new DAOService();

        Request request = new Request(Type.INSERT_USER, userDetail);

         service.insert(request);

    }
}
