package com.warehouse.client;


import com.warehouse.client.present.LoginPresent;
import com.warehouse.client.present.MainPresent;
import com.warehouse.client.present.Present;
import com.warehouse.shared.action.LoginAction;
import com.warehouse.client.listener.LoginListener;
import com.warehouse.shared.action.RuleAction;
import com.warehouse.shared.dto.UserDetailDTO;
import com.warehouse.shared.dto.UserTypeDTO;


/**
 * Created by Дима on 30.04.2017.
 *
 */

class Application
{
    private Present mainPresent;
    private Present login;

    void go(String key)
    {            // first off all define user via key

        UserTypeDTO userTypeDTO = new UserTypeDTO();
        userTypeDTO.setId(1L);
        userTypeDTO.setName("Admin");

        login = new LoginPresent();

        RuleAction ruleAction = (RuleAction) login;
        ruleAction.requestRules(userTypeDTO, login.getClass().getName());

        LoginAction action = (LoginAction) login;
        action.addLoginListener(new LoginListener()
        {
            @Override
            public void onSuccess(UserDetailDTO userDetail)
            {
                Warehouse.info("Login OK. User name is " + userDetail.getName());
                mainPresent = new MainPresent();
                mainPresent.show();
            }

            @Override
            public void onFail(String why)
            {
                Warehouse.info("Fail: " + why);
                login.show();
            }
        });
        Warehouse.info("Login by key " + key);
        action.loginByKey(key);
    }
}
