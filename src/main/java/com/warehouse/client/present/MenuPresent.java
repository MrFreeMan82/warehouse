package com.warehouse.client.present;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.TreeViewModel;
import com.warehouse.client.utils.Dockable;
import com.warehouse.client.utils.Service;
import com.warehouse.client.utils.ServiceAsync;
import com.warehouse.client.Warehouse;
import com.warehouse.client.utils.MenuTreeModel;
import com.warehouse.shared.action.MenuAction;
import com.warehouse.client.listener.MenuListener;
import com.warehouse.shared.dto.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дима on 05.05.2017.
 *
 */

class MenuPresent extends Present implements MenuAction, Dockable<Widget>
{
    private List<MenuItem> items = new ArrayList<>();
    private CellTree cellTree;
    private MenuListener listener;

    @Override
    public List<MenuItem> requestMenuList()
    {
      /*  ServiceAsync<List<MenuItem>> async = GWT.create(Service.class);
        async.s(Warehouse.sessionKey,
                "", new MenuItem(), new AsyncCallback<List<MenuItem>>()
                {
                    @Override
                    public void onFailure(Throwable throwable) {
                        Warehouse.severe(throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(List<MenuItem> navItems)
                    {
                        items = navItems;
                        TreeViewModel navigateTreeModel = new MenuTreeModel(listener, items);
                        cellTree = new CellTree(navigateTreeModel, null);
                        cellTree.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);
                    }
                }
        );*/
        return null;
    }

    @Override
    public void addMenuListener(MenuListener listener) {
        this.listener = listener;
    }

    @Override
    public void show() {}

    @Override
    public Widget dockable() {return cellTree; }
}
