package com.warehouse.client;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.warehouse.client.action.ActionUserDetail;
import com.warehouse.client.action.External;
import com.warehouse.client.event.AppEventBuilder;
import com.warehouse.client.event.ErrorEvent;
import com.warehouse.client.page.Page;


/**
 * Created by Дима on 19.04.2017.
 *
 */

public class AppExternal implements External
{
    private static final String CONTENT_TYPE = "Content-type";
    private static final String FORM = "application/x-www-form-urlencoded";

    @Override
    public void request(Page sender, String url, String data)
    {
        RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, url);
        builder.setHeader(CONTENT_TYPE, FORM);
        String postData = "json=" + data;

        try {
            builder.sendRequest(postData, new RequestCallback() {
                @Override
                public void onResponseReceived(Request request, Response response)
                {
                    Warehouse.eventBus.fireEvent(
                            new AppEventBuilder()
                            .setPage(sender)
                            .setSenderID(toString())
                            .setEvent("onResponseReceived")
                            .setAction(ActionUserDetail.EXTERNAL_RESPONSE)
                            .setJSONString(response.getText())
                            .build()
                    );
                }

                @Override
                public void onError(Request request, Throwable throwable) {
                    Warehouse.eventBus.fireEvent(new ErrorEvent(throwable, "URL= " + url));
                }
            });

        } catch (Exception e) {
            Warehouse.eventBus.fireEvent(new ErrorEvent(e, "Login Request Error"));
        }
    }
}
