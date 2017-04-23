package com.warehouse.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.json.client.JSONObject;
import com.warehouse.client.present.Present;


/**
 * Created by Дима on 15.04.2017.
 *
 */
public class AppEvent extends GwtEvent<AppEventHandler>
{
    private boolean handled;
    private Present page;
    private EventAction action;
    private JSONObject json;
    private String jsonString;
    private Object event;
    private String sender;

    public static final Type<AppEventHandler> TYPE = new Type<>();

    public boolean isHandled() { return handled; }
    public void setHandled(boolean handled) { this.handled = handled; }

    public Present getPage() {  return page; }
    void setPage(Present page) { this.page = page; }


    public String getJsonString() {return jsonString;}
    void setJsonString(String jsonString) {this.jsonString = jsonString;}


    public EventAction getAction() { return action; }
    void setAction(EventAction action) { this.action = action; }


    public JSONObject getJSONObject() {return json; }
    void setJSONObject(JSONObject json) { this.json = json; }


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
