package com.warehouse.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.warehouse.client.pages.Page;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Дима on 15.04.2017.
 *
 */
public class AppEvent extends GwtEvent<AppEventHandler>
{
    private boolean handled;
    private Page page;
    private EventAction action;
    private ArrayList<Map<Enum, String>> params;
    private Object event;
    private String sender;

    public static final Type<AppEventHandler> TYPE = new Type<>();

    public boolean isHandled() { return handled; }
    public void setHandled(boolean handled) { this.handled = handled; }

    public Page getPage() {  return page; }
    void setPage(Page page) { this.page = page; }


    public EventAction getAction() { return action; }
    void setAction(EventAction action) { this.action = action; }


    public ArrayList<Map<Enum, String>> getParams(){return params;}
    void setParams(ArrayList<Map<Enum, String>> params) { this.params = params; }
    public String getParam(Enum key)
    {
        for(Map<Enum, String> param: params)
            if(param.containsKey(key)) return param.get(key);

        return "";
    }

    public Object getEvent() { return event; }
    void setEvent(Object event) { this.event = event; }


    public String getSenderID() { return sender; }
    void setSenderID(String sender) {this.sender = sender; }
    
    @Override
    public Type<AppEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(AppEventHandler handler) {
        handler.onRequest(this);
    }
}
