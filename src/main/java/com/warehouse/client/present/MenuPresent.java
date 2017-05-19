package com.warehouse.client.present;

import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.ui.Widget;
import com.warehouse.client.utils.Dockable;
import com.warehouse.shared.source.MenuAction;
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
