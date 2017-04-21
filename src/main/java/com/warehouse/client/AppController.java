package com.warehouse.client;

import com.warehouse.client.events.*;
import com.warehouse.client.pages.UserDetailPage;


/**
 * Created by Дима on 15.04.2017.
 *
 */

public class AppController implements AppEventHandler
{
    void go()
    {
       new UserDetailPage();
    }

    @Override
    public void onRequest(AppEvent event)
    {
        event.getAction().action(event);
    }
}
