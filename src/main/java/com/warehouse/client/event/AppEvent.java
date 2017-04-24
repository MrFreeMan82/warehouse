package com.warehouse.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.warehouse.client.present.Present;


/**
 * Created by Дима on 15.04.2017.
 *
 */
public class AppEvent extends GwtEvent<AppEventHandler>
{
    private Present present;
    private Object event;
    private Object sender;

    public static final Type<AppEventHandler> TYPE = new Type<>();

    public AppEvent(Present present, Object event, Object sender)
    {
        this.present = present;
        this.event = event;
        this.sender = sender;
    }

    @Override
    public String toString()
    {
        return   "Present: " + (present == null ? "-": present.getClass().getName()) + '\n' +
                 "Sender: " +  (sender == null ? "-": sender.getClass().getName()) + '\n' +
                 "Event: " + (event == null ? "-": event.getClass().getName());
    }
    
    @Override
    public Type<AppEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(AppEventHandler handler) {
        handler.onRequest(this);
    }
}
