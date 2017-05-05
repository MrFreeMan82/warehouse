package com.warehouse.client;

import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.warehouse.client.present.Present;

/**
 * Created by Дима on 25.04.2017.
 *
 */
@Deprecated
public class LogEvent
{
    private enum Type{INFO, ERROR, SERVER_FAILURE}

    private Type type;
    private Present present;
    private Object sender;
    private String msg;
    private Throwable exception;
    private ServerFailure serverFailure;



    public LogEvent(Present present, Object sender, String msg)
    {
        type = Type.INFO;
        this.present = present;
        this.sender = sender;
        this.msg = msg;
    }

    public LogEvent(Throwable exception){
        type = Type.ERROR;
        this.exception = exception;
    }

    public LogEvent(ServerFailure serverFailure)
    {
        type = Type.SERVER_FAILURE;
        this.serverFailure = serverFailure;
    }


    @Override
    public String toString()
    {
        switch (type)
        {
            case ERROR: {
                String result = "";
                result += "Exception Type: " + exception.getClass().getName() + '\n';
                result += "Error: " + exception.getMessage() + '\n';
                result += "Trace: ";
                StringBuilder trace = new StringBuilder();
                for (StackTraceElement element : exception.getStackTrace()) trace.append(element.toString());
                result += trace.toString();
                return result;
            }
            case SERVER_FAILURE: {
                String result = "SERVER ERROR" + '\n';
                result += "Exception Type: " + serverFailure.getExceptionType() + '\n';
                result += "Request: " + serverFailure.getRequestContext() + '\n';
                result += "Error: " + serverFailure.getMessage() + '\n';
                result += "Trace: " + serverFailure.getStackTraceString();
                return result;
            }
            case INFO: {
                return "Present: " + (present == null ? "-" : present.getClass().getName()) + '\n' +
                        "Sender: " + (sender == null ? "-" : sender.getClass().getName()) + '\n' +
                        "Msg: " + (msg == null ? "-" : msg);
            }
            default:
                return "Unknown";
        }
    }
}
