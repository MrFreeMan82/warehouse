package com.warehouse.client.action;

import com.warehouse.shared.entity.NavItem;

import java.util.List;

/**
 * Created by Дима on 05.05.2017.
 *
 */

public interface NavigatePresentAction
{
    List<NavItem> getNavItems();
    void onNavigate(NavItem navItem);
}
