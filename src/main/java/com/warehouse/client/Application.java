package com.warehouse.client;


import com.warehouse.client.action.LoginAction;
import com.warehouse.client.listener.LoginListener;
import com.warehouse.client.present.LoginPresent;
import com.warehouse.client.present.MainPresent;
import com.warehouse.shared.entity.UserDetail;


/**
 * Created by Дима on 30.04.2017.
 *
 */

class Application
{
    private LoginPresent loginPresent;
    private LoginAction login;

    void setLogin(LoginAction login){this.login = login;}

    void go(String key)
    {
            // first off all define user via key
        login.addLoginListener(new LoginListener()
        {
            @Override
            public void onSuccess(UserDetail userDetail)
            {
                Warehouse.logger.info("LoginAction OK. User name is " + userDetail.getName());
                new MainPresent(userDetail);
            }

            @Override
            public void onFail(String why)
            {
                Warehouse.logger.info(why);
                if(loginPresent == null) loginPresent = new LoginPresent(login);
            }
        });
        login.loginByKey(key);
    }
}
