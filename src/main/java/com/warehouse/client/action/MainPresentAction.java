package com.warehouse.client.action;

import com.warehouse.client.present.Present;
import com.warehouse.client.utils.Dockable;
import com.warehouse.shared.entity.UserType;

/**
 * Created by Дима on 03.05.2017.
 *
 */

public interface MainPresentAction
{
    <T extends Present & Dockable> void dockPresent(T present);
    void show(UserType userType);
}
