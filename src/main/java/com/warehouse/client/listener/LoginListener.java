package com.warehouse.client.listener;

import com.warehouse.shared.dto.UserDetailDTO;

/**
 * Created by Дима on 30.04.2017.
 *
 */

public interface LoginListener
{
    void onSuccess(UserDetailDTO userDetail);
    void onFail(String why);
}
