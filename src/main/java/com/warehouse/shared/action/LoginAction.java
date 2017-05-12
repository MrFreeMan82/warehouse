package com.warehouse.shared.action;

import com.warehouse.client.listener.LoginListener;
import com.warehouse.shared.dto.UserSessionDTO;

import java.util.List;

/**
 * Created by Дима on 30.04.2017.
 *
 */

public interface LoginAction
{
   List<UserSessionDTO> loginByKey(String key);
   List<UserSessionDTO> loginByPassword(String password);
   void addLoginListener(LoginListener listener);
   void show();
}
