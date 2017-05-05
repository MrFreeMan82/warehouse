package com.warehouse.client.listener;

import com.warehouse.shared.entity.UserDetail;

/**
 * Created by Дима on 30.04.2017.
 *
 */

public interface LoginListener
{
    void onSuccess(UserDetail userDetail);
    void onFail(String why);
}
