package com.warehouse.server.dao;

import com.warehouse.client.listener.LoginListener;
import com.warehouse.server.DAOService;
import com.warehouse.server.Resource;
import com.warehouse.server.entity.UserSession;
import com.warehouse.shared.action.LoginAction;
import com.warehouse.shared.dto.DTO;
import com.warehouse.shared.dto.LoginDTO;
import com.warehouse.shared.dto.UserSessionDTO;
import com.warehouse.shared.function.FunctionOneArg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Дима on 12.05.2017.
 *
 */

public class LoginDAO extends DAO implements LoginAction
{
    // Query Names
    public static final String LOGIN_BY_KEY = "loginByKey";
    public static final String LOGIN_BY_PASSWORD = "loginByPassword";

    private HashMap<String, FunctionOneArg<List<UserSessionDTO>, LoginDTO>> queryTable = new HashMap<>();

    public LoginDAO() {
        queryTable.put(LOGIN_BY_KEY, (dto)->this.loginByKey(dto.getKey()));
        queryTable.put(LOGIN_BY_PASSWORD, (dto)->this.loginByPassword(dto.getPassword()));
    }

    @Override
    public List<? extends DTO> select(String queryName, DTO example) {

        return queryTable.get(queryName).go((LoginDTO) example);
    }

    private List<UserSessionDTO> doSelect(String sql)
    {
        List<UserSession> sessions = internalSelect(sql, UserSession.class);
        List<UserSessionDTO> sessionDTOS = new ArrayList<>();

        if((sessions == null) || (sessions.size() == 0)) return sessionDTOS;

        for (UserSession session: sessions)
            sessionDTOS.add(new UserSessionDTO(session));

        return sessionDTOS;
    }

    @Override
    public List<UserSessionDTO> loginByKey(String key)
    {
       DAOService.logger.info(LOGIN_BY_KEY);
       String resource = LOGIN_BY_KEY + ".sql";
       String sql = String.format(Resource.getSQL(resource), key);
       return doSelect(sql);
    }

    @Override
    public List<UserSessionDTO> loginByPassword(String password)
    {
        DAOService.logger.info(LOGIN_BY_PASSWORD);
        String resource = LOGIN_BY_PASSWORD + ".sql";
        String sql = String.format(Resource.getSQL(resource), password);
        return doSelect(sql);
    }

    @Override
    public void addLoginListener(LoginListener listener) {}

    @Override
    public void show() {}
}
