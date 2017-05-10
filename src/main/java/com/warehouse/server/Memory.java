package com.warehouse.server;

import com.warehouse.shared.entity.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дима on 10.05.2017.
 *
 */

public class Memory implements Database
{
    private static Memory instance;

    private Memory() {}

    static Memory getInstance()
    {
        if(instance == null) instance = new Memory();
        return instance;
    }

    @Override
    public List<? extends Base> selectSessionByKey(Base example)
    {
        UserSession session = (UserSession) example;

        if(session.getKey().equals("12"))
        {
            UserType type = new UserType();
            type.setName("Admin");
            type.setId(1L);

            UserDetail userDetail = new UserDetail();
            userDetail.setName(Resource.getStringResource("name"));
            userDetail.setId(1L);
            userDetail.setUserType(type);

            session.setUser(userDetail);

            List<UserSession> list = new ArrayList<>();
            list.add(session);
            return list;
        }
        return new ArrayList<>();
    }

    @Override
    public List<? extends Base> selectAllMenuItems()
    {
        MenuItem users = new MenuItem();
        users.setName("Users");
        users.setChildren(new ArrayList<>());

        MenuItem sub = new MenuItem();
        sub.setId(MenuItem.USER_LIST);
        sub.setName("List");
        users.getChildren().add(sub);

        List<MenuItem> list = new ArrayList<>();
        list.add(users);

        return list;
    }

    @Override
    public List<? extends Base> selectAllUserType() {
        List<UserType> list = new ArrayList<>();

        UserType admin = new UserType();
        admin.setId(1L);
        admin.setName("Admin");

        list.add(admin);

        UserType oper = new UserType();
        oper.setId(2L);
        oper.setName("Operator");

        list.add(oper);
        return list;
    }
}
