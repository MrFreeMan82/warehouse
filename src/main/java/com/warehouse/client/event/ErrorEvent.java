package com.warehouse.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Created by Дима on 16.04.2017.
 *
 */

public class ErrorEvent extends GwtEvent<ErrorEventHandler>
{
    public static final Type<ErrorEventHandler> TYPE = new Type<>();
    private Throwable exception;

    public ErrorEvent(Throwable exception)
    {
        this.exception = exception;
    }

    @Override
    public String toString()
    {
        String result = "";
        result += "Exception Type: " + exception.getClass().getName() + '\n';
        result += "Error: " + exception.getMessage() + '\n';
        result += "Trace: ";
        StringBuilder trace = new StringBuilder();
        for (StackTraceElement element : exception.getStackTrace()) trace.append(element.toString());
        result += trace.toString();
        return result;
    }

    @Override
    public Type<ErrorEventHandler> getAssociatedType() { return TYPE; }

    @Override
    protected void dispatch(ErrorEventHandler handler){  handler.onError(this);  }
}
