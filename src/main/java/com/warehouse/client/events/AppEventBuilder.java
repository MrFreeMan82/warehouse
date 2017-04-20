package com.warehouse.client.events;

import com.warehouse.client.pages.Page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Дима on 20.04.2017.
 *
 */

public class AppEventBuilder
{
    private Object event;
    private String sender;
    private Page page;
    private EventAction action;
    private ArrayList<Map<Enum, String>> params = new ArrayList<>();


    public AppEventBuilder setEvent(Object event) { this.event = event; return this; }

    public AppEventBuilder setSenderID(String sender) { this.sender = sender; return this;  }

    public AppEventBuilder setPage(Page page) {  this.page = page; return this; }

    public AppEventBuilder setAction(EventAction action) {  this.action = action; return this; }

    public AppEventBuilder addParam(Enum key, String value)
    {
        Map<Enum, String> item = new HashMap<>();
        item.put(key, value);
        params.add(item);
        return this;
    }

    public AppEvent build()
    {
        if((event == null) || (sender == null) || (page == null) || (action == null))
            throw new RuntimeException("Event properties not set.");

        AppEvent appEvent = new AppEvent();
        appEvent.setEvent(event);
        appEvent.setSenderID(sender);
        appEvent.setPage(page);
        appEvent.setAction(action);
        appEvent.setParams(params);
        return appEvent;
    }

}
