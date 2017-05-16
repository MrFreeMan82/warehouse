package com.warehouse.client.listener;

import com.warehouse.shared.dto.MenuItem;

/**
 * Created by Дима on 08.05.2017.
 *
 */

public interface MenuListener
{
    void onNavigate(MenuItem navItem);
}
