package com.warehouse.client.event;

import com.google.gwt.json.client.JSONObject;
import com.warehouse.client.page.Page;

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
    private JSONObject json;
    private String jsonString;


    public AppEventBuilder setEvent(Object event) { this.event = event; return this; }
    public AppEventBuilder setSenderID(String sender) { this.sender = sender; return this;  }
    public AppEventBuilder setPage(Page page) {  this.page = page; return this; }
    public AppEventBuilder setAction(EventAction action) {  this.action = action; return this; }
    public AppEventBuilder setJSONObject(JSONObject json) { this.json = json; return this; }
    public AppEventBuilder setJSONString(String jsonString) { this.jsonString = jsonString; return this; }

    public AppEvent build()
    {
        if((event == null) || (sender == null) || (page == null) || (action == null))
            throw new RuntimeException("Event properties not set.");

        AppEvent appEvent = new AppEvent();
        appEvent.setEvent(event);
        appEvent.setSenderID(sender);
        appEvent.setPage(page);
        appEvent.setAction(action);
        appEvent.setJSONObject(json);
        appEvent.setJsonString(jsonString);
        return appEvent;
    }

}
