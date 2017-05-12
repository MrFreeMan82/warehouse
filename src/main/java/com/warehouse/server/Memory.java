package com.warehouse.server;

import com.warehouse.server.dao.LoginDAO;
import com.warehouse.shared.dto.*;
import com.warehouse.shared.function.FunctionOneArg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Дима on 10.05.2017.
 *
 */

public class Memory implements Database
{
    private static Memory instance;
    private HashMap<String, FunctionOneArg<List<? extends DTO>, DTO>> prepareTable = new HashMap<>();

    private Memory() {
        prepareTable.put(LoginDAO.LOGIN_BY_KEY, this::selectSessionByKey);
    }

    static Memory getInstance()
    {
        if(instance == null) instance = new Memory();
        return instance;
    }

    @Override
    public List<? extends DTO> select(String queryName, DTO example)
    {
        return prepareTable.get(queryName).go(example);
    }

    private List<? extends DTO> selectSessionByKey(DTO example)
    {
        UserSessionDTO session = (UserSessionDTO) example;

        if(session.getKey().equals("12"))
        {
            UserTypeDTO type = new UserTypeDTO();
            type.setName("Admin");
            type.setId(1L);

            UserDetailDTO userDetail = new UserDetailDTO();
            userDetail.setName("Mark");
            userDetail.setId(1L);
            userDetail.setUserType(type);

            session.setUser(userDetail);

            List<UserSessionDTO> list = new ArrayList<>();
            list.add(session);
            return list;
        }
        return new ArrayList<>();
    }

    private List<? extends DTO> selectAllMenuItems()
    {
        MenuItemDTO users = new MenuItemDTO();
        users.setName("Users");
       // users.setChildren(new ArrayList<>());

        MenuItemDTO sub = new MenuItemDTO();
      //  sub.setId(MenuItemDTO.USER_LIST);
        sub.setName("List");
      //  users.getChildren().add(sub);

        List<MenuItemDTO> list = new ArrayList<>();
        list.add(users);

        return list;
    }

    private List<? extends DTO> selectAllUserType() {
        List<UserTypeDTO> list = new ArrayList<>();

        UserTypeDTO admin = new UserTypeDTO();
        admin.setId(1L);
        admin.setName("Admin");

        list.add(admin);

        UserTypeDTO oper = new UserTypeDTO();
        oper.setId(2L);
        oper.setName("Operator");

        list.add(oper);
        return list;
    }
}
