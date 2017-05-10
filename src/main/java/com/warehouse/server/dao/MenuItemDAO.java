package com.warehouse.server.dao;

import com.warehouse.shared.entity.MenuItem;


/**
 * Created by Дима on 08.05.2017.
 *
 */

public class MenuItemDAO extends DAO
{
    {
        if(! transition.containsKey(MenuItem.GET_ALL_MENU_ITEMS))
            transition.put(MenuItem.GET_ALL_MENU_ITEMS, (example)->database.selectAllMenuItems());
    }
}
