package com.warehouse.client.listener;

import com.warehouse.shared.dto.MenuItemDTO;

/**
 * Created by Дима on 08.05.2017.
 *
 */

public interface MenuListener
{
    void onNavigate(MenuItemDTO navItem);
}
