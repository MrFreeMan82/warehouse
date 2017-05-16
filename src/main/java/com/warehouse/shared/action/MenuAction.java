package com.warehouse.shared.action;

import com.warehouse.client.listener.MenuListener;
import com.warehouse.shared.dto.MenuItem;

import java.util.List;

/**
 * Created by Дима on 12.05.2017.
 *
 */

public interface MenuAction
{
    List<MenuItem> requestMenuList();
    void addMenuListener(MenuListener listener);
}
