package com.warehouse.client.action;

import com.warehouse.shared.entity.UserType;

/**
 * Created by Дима on 03.05.2017.
 *
 */

public interface MainPresentAction
{
    void dockUserListPresent();
    void show(UserType userType);
}
