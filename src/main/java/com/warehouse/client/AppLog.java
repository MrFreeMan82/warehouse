package com.warehouse.client;

import com.warehouse.client.events.*;

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
        StringBuilder builder = new StringBuilder();
        builder.append("Event: ");
        builder.append(event.getKind());
        builder.append(" Params: ");
        String[] params = event.getParams();
        if(params.length > 0)
        {
            for(String param: params)
            {
                builder.append(param);
                builder.append(';');
            }
        }
        else builder.append('-');
        logger.info(builder.toString());
    }

    @Override
    public void onError(ErrorEvent event) {
        logger.log(Level.SEVERE, event.getMsg(), event.getException());
    }
}
