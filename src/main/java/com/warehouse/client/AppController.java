package com.warehouse.client;

import com.warehouse.client.events.*;
import com.warehouse.client.pages.LoginPage;


/**
 * Created by Дима on 15.04.2017.
 *
 */

public class AppController implements AppEventHandler
{
    private String key="";

    void go()
    {
        if(key.equals(""))
        {
            new LoginPage();
        }
    }

    @Override
    public void onRequest(AppEvent event)
    {
        event.doAction();
    }
}
