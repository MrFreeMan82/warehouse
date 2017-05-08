package com.warehouse.client.present;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.TreeViewModel;
import com.warehouse.client.Service;
import com.warehouse.client.ServiceAsync;
import com.warehouse.client.Warehouse;
import com.warehouse.client.action.MainPresentAction;
import com.warehouse.client.additional.NavTreeModel;
import com.warehouse.client.listener.NavigateListener;
import com.warehouse.shared.entity.NavItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дима on 05.05.2017.
 *
 */

class NavigationPresent extends Present implements NavigateListener
{
    private static final Long USER_LIST = 0x1L;

    private List<NavItem> items = new ArrayList<>();
    private MainPresentAction action;
    private CellTree cellTree;
    private NavigateListener listener = this;

    private void requestNavItems()
    {

        // ToDo Сделать запрос к базе для получения списка навигации

        Warehouse.logger.info("Request for navigation tree");
        ServiceAsync<List<NavItem>> async = GWT.create(Service.class);
        async.querySelect(Warehouse.sessionKey, "", new NavItem(), new AsyncCallback<List<NavItem>>()
        {
            @Override
            public void onFailure(Throwable throwable) {
                Warehouse.logger.severe(throwable.getMessage());
            }

            @Override
            public void onSuccess(List<NavItem> navItems) {
                Warehouse.logger.info("OK");
                items = navItems;
                TreeViewModel navigateTreeModel = new NavTreeModel(listener, items);
                cellTree = new CellTree(navigateTreeModel, null);
                cellTree.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);
            }
        });
    }

    NavigationPresent(MainPresentAction action)
    {
        this.action = action;
        requestNavItems();
    }

    CellTree getCellTree() {return cellTree; }

    @Override
    public void onNavigate(NavItem navItem) {
        Warehouse.logger.info("NavigatePresentAction to tag '" + navItem.getId() + "'");
        Long id = navItem.getId();

        if(id.equals(USER_LIST))
        {
            action.dockPresent(new UserListPresent());
        }
        else Warehouse.logger.info("Fail");
    }
}
