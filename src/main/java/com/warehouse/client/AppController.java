package com.warehouse.client;

import com.warehouse.client.event.*;
import com.warehouse.client.present.MainPresent;


/**
 * Created by Дима on 15.04.2017.
 *
 */

public class AppController implements AppEventHandler
{
    void go()
    {
      // new UserDetailProxy();
        new MainPresent();
    }

    @Override
    public void onRequest(AppEvent event)
    {
        event.getAction().action(event);
    }
}
