package com.warehouse.client;

import com.warehouse.client.event.AppEvent;
import com.warehouse.client.event.AppEventHandler;
import com.warehouse.client.event.ErrorEvent;
import com.warehouse.client.event.ErrorEventHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Дима on 16.04.2017.
 *
 */

public class AppLog implements AppEventHandler, ErrorEventHandler
{
    private static final Logger logger = Logger.getLogger("AppLog");

    @Override
    public void onRequest(AppEvent event)
    {
        logger.info(event.toString());
    }

    @Override
    public void onError(ErrorEvent event) {
        logger.log(Level.SEVERE,  event.toString());
    }
}
