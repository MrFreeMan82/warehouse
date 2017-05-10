package com.warehouse.client.present;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.TreeViewModel;
import com.warehouse.client.utils.Service;
import com.warehouse.client.utils.ServiceAsync;
import com.warehouse.client.Warehouse;
import com.warehouse.client.utils.MenuTreeModel;
import com.warehouse.shared.transition.VoidNoArg;
import com.warehouse.shared.transition.Transition;
import com.warehouse.client.listener.MenuListener;
import com.warehouse.shared.entity.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дима on 05.05.2017.
 *
 */

class MenuPresent extends Present implements MenuListener
{
    private List<Transition<Long, VoidNoArg>> transitions;
    private List<MenuItem> items = new ArrayList<>();
    private CellTree cellTree;
    private MenuListener listener = this;

    private void requestMenuItems()
    {
        ServiceAsync<List<MenuItem>> async = GWT.create(Service.class);
        async.querySelect(Warehouse.sessionKey, MenuItem.GET_ALL_MENU_ITEMS, new MenuItem(), new AsyncCallback<List<MenuItem>>()
        {
            @Override
            public void onFailure(Throwable throwable) {
                Warehouse.logger.severe(throwable.getMessage());
            }

            @Override
            public void onSuccess(List<MenuItem> navItems)
            {
                items = navItems;
                TreeViewModel navigateTreeModel = new MenuTreeModel(listener, items);
                cellTree = new CellTree(navigateTreeModel, null);
                cellTree.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);
            }
        });
    }

    CellTree getCellTree(){return cellTree;}

    MenuPresent(List<Transition<Long, VoidNoArg>> transitions)
    {
        this.transitions = transitions;
        requestMenuItems();
    }

    @Override
    public void onNavigate(MenuItem navItem) {
        Long id = navItem.getId();

        for(Transition transition: transitions)
        {
            if (transition.trigger.equals(id))
            {
                ((VoidNoArg) transition.action).go();
                return;
            }
        }
    }
}