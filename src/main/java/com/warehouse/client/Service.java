package com.warehouse.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.warehouse.shared.entity.Base;


/**
 * Created by Дима on 30.04.2017.
 *
 */

@RemoteServiceRelativePath("dao")
public interface Service extends RemoteService
{
    Base findEntityBy(String sessionKey, String namedQuery, Base params);
}
