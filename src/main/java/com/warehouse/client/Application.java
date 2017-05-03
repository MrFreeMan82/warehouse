package com.warehouse.client;


import com.warehouse.client.interf.Login;
import com.warehouse.client.interf.LoginListener;
import com.warehouse.client.interf.Main;
import com.warehouse.shared.entity.UserDetail;


/**
 * Created by Дима on 30.04.2017.
 *
 */

class Application
{
    private Login login;
    private Main main;

    void setLogin(Login login){this.login = login;}
    void  setMain(Main main){this.main = main;}

    void go(String key)
    {
            // first off all define user via key
        login.addLoginListener(new LoginListener()
        {
            @Override
            public void onSuccess(UserDetail userDetail)
            {
                Warehouse.logger.info("Login OK. User name is " + userDetail.getName());
                main.mainView(userDetail);
            }

            @Override
            public void onFail(String why)
            {
                Warehouse.logger.info(why);
                login.loginView();
            }
        });
        login.loginByKey(key);
    }
}
