package com.warehouse.client.impl;


import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.warehouse.client.Warehouse;
import com.warehouse.client.interf.Login;
import com.warehouse.client.interf.LoginListener;
import com.warehouse.client.present.LoginPresent;
import com.warehouse.shared.entity.Session;
import com.warehouse.client.Service;
import com.warehouse.client.ServiceAsync;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Дима on 30.04.2017.
 *
 */
public class LoginImpl implements Login
{
    private List<LoginListener> listeners = new ArrayList<>();


    @Override
    public void loginByKey(String key)
    {
        Warehouse.logger.info("loginByKey " + key);
        Session params = new Session();
        params.setKey(key);

        ServiceAsync<Session> async = GWT.create(Service.class);
        async.findEntityBy(Warehouse.sessionKey, Session.FIND_BY_KEY, params, new AsyncCallback<Session>()
        {
            @Override
            public void onFailure(Throwable throwable) {
                for(LoginListener listener: listeners)
                    listener.onFail(throwable.getMessage());
            }

            @Override
            public void onSuccess(Session session)
            {
                if(session == null) onFailure(new Exception("Such key '" + key + "' not found" ));
                else for(LoginListener listener: listeners) listener.onSuccess(session.getUser());
            }
        });
    }

    @Override
    public void addLoginListener(LoginListener listener) {
        listeners.add(listener);
    }

    @Override
    public void loginView() {
        new LoginPresent(this);
    }

    @Override
    public void loginByPassword(String password) {
        Warehouse.logger.info("loginByKey " + password);
        // ToDo create request to server
    }
}
