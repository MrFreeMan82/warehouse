package com.warehouse.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.warehouse.client.pages.Page;

/**
 * Created by Дима on 15.04.2017.
 *
 */
public class AppEvent<T extends Enum<T> & EventAction> extends GwtEvent<AppEventHandler>
{
    private boolean handled;
    private Page sender;
    private T kind;
    private String[] params;

    public static Type<AppEventHandler> TYPE = new Type<>();

    public
        AppEvent(T kind, Page sender, String ... params)
    {
        this.sender = sender;
        this.kind = kind;
        this.params = params;
    }

    public void setHandled(boolean value){handled = value;}
    public boolean isHandled(){return handled;}
    public T getKind(){return kind;}
    public void doAction(){kind.action(this);}
    public String[] getParams(){return params;}
    public Page getSender(){return sender;}

    @Override
    public Type<AppEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(AppEventHandler handler) {
        handler.onRequest(this);
    }
}
