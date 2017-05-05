package com.warehouse.client.impl;


import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.warehouse.client.Warehouse;
import com.warehouse.client.action.LoginAction;
import com.warehouse.client.listener.LoginListener;
import com.warehouse.shared.entity.Session;
import com.warehouse.client.Service;
import com.warehouse.client.ServiceAsync;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Дима on 30.04.2017.
 *
 */
public class LoginActionImpl implements LoginAction
{
    private List<LoginListener> listeners = new ArrayList<>();


    @Override
    public void loginByKey(String key)
    {
        Warehouse.logger.info("loginByKey " + key);
        Session params = new Session();
        params.setKey(key);

        ServiceAsync<List<Session>> async = GWT.create(Service.class);
        async.querySelect(Warehouse.sessionKey, Session.FIND_BY_KEY, params, new AsyncCallback<List<Session>>()
        {
            @Override
            public void onFailure(Throwable throwable) {
                for(LoginListener listener: listeners)
                    listener.onFail(throwable.getMessage());
            }

            @Override
            public void onSuccess(List<Session> session)
            {
                if((session == null) || (session.size() != 1))
                    onFailure(new Exception("Such key '" + key + "' not found" ));

                else for(LoginListener listener: listeners) listener.onSuccess(session.get(0).getUser());
            }
        });
    }

    @Override
    public void addLoginListener(LoginListener listener) {
        listeners.add(listener);
    }

    @Override
    public void loginByPassword(String password) {
        Warehouse.logger.info("loginByKey " + password);
        // ToDo create request to server
    }
}
