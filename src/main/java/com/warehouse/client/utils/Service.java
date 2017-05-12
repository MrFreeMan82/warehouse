package com.warehouse.client.utils;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.warehouse.shared.dto.DTO;

import java.util.List;


/**
 * Created by Дима on 30.04.2017.
 *
 */

@RemoteServiceRelativePath("dao")
public interface Service extends RemoteService
{
    List<? extends DTO> querySelect(String sessionKey, String queryName, DTO example);
    int queryInsert(String sessionKey, String queryName, DTO example);
}
