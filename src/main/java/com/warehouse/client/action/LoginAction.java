package com.warehouse.client.action;

import com.warehouse.client.listener.LoginListener;

/**
 * Created by Дима on 30.04.2017.
 *
 */

public interface LoginAction
{
   void loginByKey(String key);
   void loginByPassword(String password);
   void addLoginListener(LoginListener listener);
}
