package com.warehouse.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.warehouse.shared.DAOEnum;
import com.warehouse.shared.entity.Base;

import java.util.List;

/**
 * Created by Дима on 28.04.2017.
 *
 */

public interface AppRemoteServiceAsync<T extends List<? extends Base>>
{
    void entityRequest(DAOEnum Dao, String method, String jsonArgs, AsyncCallback<T> callback);
}
