package com.warehouse.server.dao;

import com.warehouse.shared.entity.MenuItem;
import com.warehouse.shared.transition.Transition;


/**
 * Created by Дима on 08.05.2017.
 *
 */

public class MenuItemDAO extends DAO
{
    {
        transitions.clear();
        transitions.add(new Transition<>(MenuItem.GET_ALL_MENU_ITEMS, (example)->database.selectAllMenuItems()));
    }
}
