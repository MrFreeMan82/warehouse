package com.warehouse.client;

import com.warehouse.client.event.*;
import com.warehouse.client.page.MainPage;
import com.warehouse.client.page.UserDetailPage;


/**
 * Created by Дима on 15.04.2017.
 *
 */

public class AppController implements AppEventHandler
{
    void go()
    {
      // new UserDetailPage();
        new MainPage();
    }

    @Override
    public void onRequest(AppEvent event)
    {
        event.getAction().action(event);
    }
}
