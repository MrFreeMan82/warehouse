package com.warehouse.client.listener;

import com.warehouse.shared.dto.UserSession;

/**
 * Created by Дима on 30.04.2017.
 *
 */

public interface LoginListener
{
    void onSuccess(UserSession session);
    void onFail(String why);
}
