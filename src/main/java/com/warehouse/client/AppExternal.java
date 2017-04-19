package com.warehouse.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.*;
import com.warehouse.client.events.AppEvent;
import com.warehouse.client.events.ErrorEvent;
import com.warehouse.client.events.External;
import com.warehouse.client.events.KindOfLogin;

import java.nio.charset.Charset;
import java.security.MessageDigest;

/**
 * Created by Дима on 19.04.2017.
 *
 */

public class AppExternal implements External
{
    private static final String CONTENT_TYPE = "Content-type";
    private static final String FORM = "application/x-www-form-urlencoded";
    private static final String LOGIN_URL = GWT.getModuleBaseURL()+"authorize";

    private String hashPassword(String password) throws Exception
    {
        if(password.equals("")) return "";
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.reset();
        byte[] bytes = md.digest(password.getBytes(Charset.forName("UTF-8")));
        StringBuilder hash = new StringBuilder();
        for(byte one: bytes)
            hash.append(Integer.toHexString((one & 0xFF) | 0x100).substring(1,3));
        return hash.toString();
    }

    @Override
    public void login(final AppEvent event)
    {
        try {
            String url = URL.encode(LOGIN_URL);
            String hashedPsw = hashPassword(event.getParams()[0]);
            String data = "psw=" + hashedPsw;

            RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, url);
            builder.setHeader(CONTENT_TYPE, FORM);

            builder.sendRequest(data, new RequestCallback()
            {
                @Override
                public void onResponseReceived(Request request, Response response)
                {
                    Warehouse.getEventBus().fireEvent(new AppEvent<>(
                            KindOfLogin.EXTERNAL_RESPONSE, event.getSender(), response.getText()));
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
