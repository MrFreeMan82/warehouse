package com.warehouse.client.present;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.view.client.TreeViewModel;
import com.warehouse.client.utils.Service;
import com.warehouse.client.utils.ServiceAsync;
import com.warehouse.client.Warehouse;
import com.warehouse.client.utils.NavTreeModel;
import com.warehouse.client.utils.Transition;
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
    private List<Transition> transitions;
    private List<NavItem> items = new ArrayList<>();
    private CellTree cellTree;
    private NavigateListener listener = this;
    private SimplePanel dockPanel;

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
                dockPanel.add(cellTree);
            }
        });
    }

    NavigationPresent(SimplePanel dockPanel, List<Transition> transitions)
    {
        this.dockPanel = dockPanel;
        this.transitions = transitions;
        requestNavItems();
    }

    @Override
    public void onNavigate(NavItem navItem) {
        Long id = navItem.getId();
        Warehouse.logger.info("NavigatePresentAction to tag '" + id + "'");

        for(Transition transition: transitions)
        {
            if (transition.trigger.equals(id))
            {
                transition.action.go();
                return;
            }
        }
        Warehouse.logger.info("Fail");
    }
}
