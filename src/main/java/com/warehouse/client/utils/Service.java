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
    DTO selectOne(String queryName, DTO example);
    List<? extends DTO> selectList(String queryName, DTO example);
}
