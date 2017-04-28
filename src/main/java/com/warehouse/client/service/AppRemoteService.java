package com.warehouse.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.warehouse.shared.DAOEnum;
import com.warehouse.shared.entity.Base;

import java.util.List;

/**
 * Created by Дима on 28.04.2017.
 *
 */
@RemoteServiceRelativePath("controller")
public interface AppRemoteService extends RemoteService
{
    List<? extends Base> entityRequest(DAOEnum Dao, String method, String jsonArgs);
}
