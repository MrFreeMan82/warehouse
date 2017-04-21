package com.warehouse.client;

import com.google.gwt.json.client.JSONObject;
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

        builder.append("JSON: ");
        JSONObject json = event.getJSONObject();
        if(json == null) {
            String jsonString = event.getJsonString();
            builder.append(jsonString);
        } else {
            builder.append(json.toString());
        }

        logger.info(builder.toString());
    }

    @Override
    public void onError(ErrorEvent event) {
        logger.log(Level.SEVERE, event.getMsg(), event.getException());
    }
}
