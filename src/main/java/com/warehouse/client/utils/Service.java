package com.warehouse.client.utils;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.warehouse.shared.dto.DTO;
import com.warehouse.shared.Request;

/**
 * Created by Дима on 30.04.2017.
 *
 */

@RemoteServiceRelativePath("dao")
public interface Service extends RemoteService
{
    DTO login(String loginParameters);
    DTO select(Request query);
    DTO selectList(Request query);
}
