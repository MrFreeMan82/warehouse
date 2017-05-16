package com.warehouse.client;


import com.warehouse.client.listener.LoginListener;
import com.warehouse.client.present.LoginPresent;
import com.warehouse.client.present.Present;
import com.warehouse.shared.action.LoginAction;
import com.warehouse.shared.action.RuleAction;
import com.warehouse.shared.dto.*;

/**
 * Created by Дима on 30.04.2017.
 *
 */

public class Application
{
    private Present mainPresent;
    private Present login;


    public static RuleSet ruleSet = new RuleSet();

    void go(String key)
    {            // first off all define user via key

        UserType userTypeDTO = new UserType();
        userTypeDTO.setId(1L);
        userTypeDTO.setName("Admin");

        login = new LoginPresent();

        LoginAction action = (LoginAction) login;
        action.addLoginListener(new LoginListener()
        {
            @Override
            public void onSuccess(UserSession session)
            {
                Warehouse.info("Login OK. User name is " + session.getUser().getName());
                Warehouse.sessionKey = session.getSessionKey();
                DTOEnum.updateDTO(DTOEnum.USER_TYPE, session.getUser().getUserType());
                DTOEnum.updateDTO(DTOEnum.USER_DETAIL, session.getUser());
                ruleSet = session.getUser().getUserType().getRuleSet();
                ((RuleAction) login).apply(ruleSet.filterByPresent(login));
                login.setTitle(login.getTitle() + " " + session.getUser().getName());
                login.show();

                // ToDo create menu
              //  mainPresent = new MainPresent();
             //   mainPresent.show();
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
