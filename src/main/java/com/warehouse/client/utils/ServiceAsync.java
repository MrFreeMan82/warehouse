package com.warehouse.client.utils;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.warehouse.shared.dto.DTO;
import com.warehouse.shared.request.Request;

/**
 * Created by Дима on 30.04.2017.
 *
 */
public interface ServiceAsync
{
    void login(String loginParameters, AsyncCallback<DTO> callback);
    void select(Request query, AsyncCallback<DTO> callback);
    void selectList(Request query, AsyncCallback<DTO> callback);
}
