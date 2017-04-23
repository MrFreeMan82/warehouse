package com.warehouse.client;


import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.warehouse.client.context.UserDetailContext;

/**
 * Created by Дима on 23.04.2017.
 *
 */

public interface AppRequestFactory extends RequestFactory
{
    UserDetailContext userDetailContext();
}
