package com.warehouse.client;


import com.warehouse.client.action.LoginAction;
import com.warehouse.client.action.MainPresentAction;
import com.warehouse.client.listener.LoginListener;
import com.warehouse.shared.entity.UserDetail;


/**
 * Created by Дима on 30.04.2017.
 *
 */

class Application
{
    private MainPresentAction mainPresentAction;
    private LoginAction login;

    void setMainPresentAction(MainPresentAction mainPresentAction){this.mainPresentAction = mainPresentAction;}
    void setLoginAction(LoginAction login){this.login = login;}

    void go(String key)
    {            // first off all define user via key
        login.addLoginListener(new LoginListener()
        {
            @Override
            public void onSuccess(UserDetail userDetail)
            {
                Warehouse.logger.info("Login OK. User name is " + userDetail.getName());
                mainPresentAction.show(userDetail.getUserType());
            }

            @Override
            public void onFail(String why)
            {
                Warehouse.logger.info("Fail: " + why);
                login.show();
            }
        });
        Warehouse.logger.info("Login by key " + key);
        login.loginByKey(key);
    }
}
