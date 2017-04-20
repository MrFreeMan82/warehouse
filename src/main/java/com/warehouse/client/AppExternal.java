package com.warehouse.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.*;
import com.warehouse.client.actions.ActionsLogin;
import com.warehouse.client.events.AppEvent;
import com.warehouse.client.actions.External;
import com.warehouse.client.events.AppEventBuilder;
import com.warehouse.client.events.ErrorEvent;
import com.warehouse.shared.Utils;


/**
 * Created by Дима on 19.04.2017.
 *
 */

public class AppExternal implements External
{
    private static final String CONTENT_TYPE = "Content-type";
    private static final String FORM = "application/x-www-form-urlencoded";
    private static final String LOGIN_URL = GWT.getModuleBaseURL()+"authorize";

    @Override
    public void login(final AppEvent event)
    {
        try {
            String url = URL.encode(LOGIN_URL);
            String hashedPsw = event.getParam(AppController.MapKeys.LOGIN_PASSWORD);
            String data = Utils.POST_Keys.PASSWORD.name() + "=" + hashedPsw;

            RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, url);
            builder.setHeader(CONTENT_TYPE, FORM);

            builder.sendRequest(data, new RequestCallback()
            {
                @Override
                public void onResponseReceived(Request request, Response response)
                {
                    Warehouse.getEventBus().fireEvent(
                            new AppEventBuilder()
                            .setPage(event.getPage())
                            .setSenderID(toString())
                            .setEvent(response)
                            .setAction(ActionsLogin.EXTERNAL_RESPONSE)
                            .addParam(AppController.MapKeys.APP_EXTERNAL, response.getText())
                            .build()
                    );
                }

                @Override
                public void onError(Request request, Throwable throwable)
                {
                    Warehouse.getEventBus().fireEvent(new ErrorEvent(throwable, "URL= " + url));
                }
            });
        } catch (Exception e) {
            Warehouse.getEventBus().fireEvent(new ErrorEvent(e, "Login Request Error"));
        }
    }
}
