package com.warehouse.server.dao;

import com.warehouse.client.listener.LoginListener;
import com.warehouse.server.DAOService;
import com.warehouse.server.Resource;
import com.warehouse.server.entity.UserDetailEntity;
import com.warehouse.shared.action.LoginAction;
import com.warehouse.shared.dto.Login;
import com.warehouse.shared.dto.UserDetail;
import com.warehouse.shared.dto.UserSession;
import com.warehouse.shared.function.FunctionOne;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by Дима on 12.05.2017.
 *
 */

public class LoginDAO extends DAO<UserSession, Login, UserSession> implements LoginAction
{
    // Query Names
    public static final String LOGIN_BY_KEY = "loginByKey";
    public static final String LOGIN_BY_PASSWORD = "loginByPassword";

    private HashMap<String, FunctionOne<UserSession, Login>> queryTable = new HashMap<>();
    private static HashMap<String, UserSession> sessionHashMap = new HashMap<>();

    public LoginDAO() {
        queryTable.put(LOGIN_BY_KEY, (dto)->this.loginByKey(dto.getKey()));
        queryTable.put(LOGIN_BY_PASSWORD, (dto)->this.loginByPassword(dto.getPassword()));
    }

    @Override
    public UserSession loginByKey(String key)
    {
       DAOService.logger.info(LOGIN_BY_KEY);
       return sessionHashMap.get(key);
    }

    @Override
    public UserSession loginByPassword(String password)
    {
        DAOService.logger.info(LOGIN_BY_PASSWORD);
        String resource = LOGIN_BY_PASSWORD + ".sql";
        String sql = String.format(Resource.getSQL(resource), password);

        List<UserDetailEntity> userEntities = internalSelect(sql, UserDetailEntity.class);

        if((userEntities == null) || (userEntities.size() == 0)) return null;

        UserSession session = new UserSession();
        session.setKey(UUID.randomUUID().toString());
        session.setUser(new UserDetail(userEntities.get(0)));
        sessionHashMap.put(session.getKey(), session);

        return session;
    }

    @Override
    public void addLoginListener(LoginListener listener) {}

    @Override
    public void create(UserSession t) {}

    @Override
    public void edit(UserSession t) {}

    @Override
    public void delete(UserSession t) {}

    @Override
    public List<UserSession> find(String queryName, Login login) {
        return new ArrayList<>();
    }

    @Override
    public UserSession findOne(String queryName, Login login) {
        return queryTable.get(queryName).go(login);
    }
}
