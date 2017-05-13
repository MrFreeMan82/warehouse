package com.warehouse.server.test;

import com.warehouse.server.Database;
import com.warehouse.server.dao.LoginDAO;
import com.warehouse.server.dao.RuleDAO;
import com.warehouse.shared.dto.*;
import com.warehouse.shared.function.FunctionOne;

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
    private HashMap<String, FunctionOne<List<? extends DTO>, DTO>> prepareTable = new HashMap<>();

    private Memory() {
        prepareTable.put(LoginDAO.LOGIN_BY_KEY, this::selectSessionByKey);
        prepareTable.put(RuleDAO.GET_RULES_BY_PRESENT_USERTYPE, this::getRuleByPresentUserType);
    }

    public static Memory getInstance()
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
        LoginDTO login = (LoginDTO) example;

        if(login.getKey().equals("12"))
        {
            UserTypeDTO type = new UserTypeDTO();
            type.setName("Admin");
            type.setId(1L);

            UserDetailDTO userDetail = new UserDetailDTO();
            userDetail.setName("Mark");
            userDetail.setId(1L);
            userDetail.setUserType(type);

            UserSessionDTO session = new UserSessionDTO();
            session.setKey(login.getKey());
            session.setUser(userDetail);

            List<UserSessionDTO> list = new ArrayList<>();
            list.add(session);
            return list;
        }
        return new ArrayList<>();
    }

    private List<? extends DTO> getRuleByPresentUserType(DTO example)
    {
        RuleDTO ruleDTO = (RuleDTO) example;
        List<RuleDTO> rules = new ArrayList<>();
        RuleDTO rule = new RuleDTO();
        rule.setPresent(ruleDTO.getPresent());
        rule.setUserTypeDTO(ruleDTO.getUserTypeDTO());
        rule.setAction('-');
        rule.setWidgets("password;sendButton");
        rules.add(rule);

        RuleDTO rule2 = new RuleDTO();
        rule2.setPresent(rule.getPresent());
        rule2.setUserTypeDTO(rule.getUserTypeDTO());
        rule2.setAction('+');
        rule2.setWidgets("password");
        rules.add(rule2);

        RuleDTO rule3 = new RuleDTO();
        rules.add(rule3);
        return  rules;
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
