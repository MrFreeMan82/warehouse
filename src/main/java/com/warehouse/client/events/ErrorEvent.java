package com.warehouse.client.events;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Created by Дима on 16.04.2017.
 *
 */

public class ErrorEvent extends GwtEvent<ErrorEventHandler>
{
    private Throwable exception;
    private String msg;

    public ErrorEvent(Throwable exception, String msg)
    {
        this.exception = exception;
        this.msg = msg;
    }

    public Throwable getException(){return exception;}
    public String getMsg(){return msg;}
    public static final Type<ErrorEventHandler> TYPE = new Type<>();

    @Override
    public Type<ErrorEventHandler> getAssociatedType() { return TYPE; }

    @Override
    protected void dispatch(ErrorEventHandler handler){  handler.onError(this);  }
}
