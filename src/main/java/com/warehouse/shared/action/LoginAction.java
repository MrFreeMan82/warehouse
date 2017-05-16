package com.warehouse.shared.action;

import com.warehouse.client.listener.LoginListener;
import com.warehouse.shared.dto.UserDetail;
import com.warehouse.shared.dto.UserSession;

import java.util.List;

/**
 * Created by Дима on 30.04.2017.
 *
 */

public interface LoginAction
{
   UserSession loginByKey(String key);
   UserSession loginByPassword(String password);
   void addLoginListener(LoginListener listener);
}
