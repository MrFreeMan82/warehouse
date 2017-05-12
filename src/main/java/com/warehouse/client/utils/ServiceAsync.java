package com.warehouse.client.utils;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.warehouse.shared.dto.DTO;


/**
 * Created by Дима on 30.04.2017.
 *
 */
public interface ServiceAsync<T>
{
    void querySelect(String sessionKey, String nameQuery, DTO example, AsyncCallback<T> callback);
    void queryInsert(String sessionKey, String nameQuery, DTO example, AsyncCallback<T> callback);
}
