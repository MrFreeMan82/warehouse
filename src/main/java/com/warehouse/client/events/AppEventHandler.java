package com.warehouse.client.events;

import com.google.gwt.event.shared.EventHandler;

/**
 * Created by Дима on 15.04.2017.
 *
 */

public interface AppEventHandler extends EventHandler
{
    void onRequest(AppEvent event);
}
