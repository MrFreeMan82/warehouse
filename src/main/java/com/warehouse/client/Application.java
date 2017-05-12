package com.warehouse.client;


import com.warehouse.client.present.LoginPresent;
import com.warehouse.client.present.MainPresent;
import com.warehouse.client.present.Present;
import com.warehouse.shared.action.LoginAction;
import com.warehouse.shared.action.MainPresentAction;
import com.warehouse.client.listener.LoginListener;
import com.warehouse.shared.dto.UserDetailDTO;


/**
 * Created by Дима on 30.04.2017.
 *
 */

class Application
{
    private Present mainPresent;
    private LoginAction login;

    void go(String key)
    {            // first off all define user via key
        login = new LoginPresent();
        login.addLoginListener(new LoginListener()
        {
            @Override
            public void onSuccess(UserDetailDTO userDetail)
            {
                Warehouse.logger.info("Login OK. User name is " + userDetail.getName());
                mainPresent = new MainPresent();
                mainPresent.show();
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
