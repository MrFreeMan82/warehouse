package com.warehouse.client.utils;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.warehouse.shared.entity.Base;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;


/**
 * Created by Дима on 30.04.2017.
 *
 */

@RemoteServiceRelativePath("dao")
public interface Service extends RemoteService
{
    List<? extends Base> querySelect(String sessionKey, String queryName, Base example);
    int queryInsert(String sessionKey, String queryName, Base example);
}
