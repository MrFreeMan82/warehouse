package com.warehouse.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * Created by Дима on 16.04.2017.
 *
 */

public interface ErrorEventHandler extends EventHandler
{
    void onError(ErrorEvent event);
}
