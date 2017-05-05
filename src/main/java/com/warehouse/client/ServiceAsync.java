package com.warehouse.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.warehouse.shared.entity.Base;


/**
 * Created by Дима on 30.04.2017.
 *
 */
public interface ServiceAsync<T>
{
    void querySelect(String sessionKey, String nameQuery, Base example, AsyncCallback<T> callback);
}
