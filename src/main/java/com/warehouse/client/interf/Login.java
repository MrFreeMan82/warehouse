package com.warehouse.client.interf;

/**
 * Created by Дима on 30.04.2017.
 *
 */

public interface Login
{
   void loginByKey(String key);
   void loginByPassword(String password);
   void addLoginListener(LoginListener listener);
}
