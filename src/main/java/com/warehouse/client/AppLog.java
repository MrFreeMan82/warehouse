package com.warehouse.client;

import com.warehouse.client.events.*;

import java.util.ArrayList;
import java.util.Map;
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
        builder.append("Page: ");
        builder.append(event.getPage().getClass().getName());
        builder.append('\n');
        builder.append("Sender: ");
        builder.append(event.getSenderID());
        builder.append('\n');
        builder.append("Event: ");
        builder.append(event.getEvent().getClass().getName());
        builder.append('\n');
        builder.append("Action: ");
        builder.append(event.getAction());
        builder.append('\n');
        builder.append("Params: ");
        ArrayList<Map<Enum, String>> params = event.getParams();
        if(params.size() > 0)
        {
            for(Map param: params)
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
