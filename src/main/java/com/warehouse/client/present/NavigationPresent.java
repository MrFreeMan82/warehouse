package com.warehouse.client.present;

import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.view.client.TreeViewModel;
import com.warehouse.client.Warehouse;
import com.warehouse.client.impl.NavTreeModel;
import com.warehouse.client.action.MainPresentAction;
import com.warehouse.client.action.NavigatePresentAction;
import com.warehouse.shared.entity.NavItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дима on 05.05.2017.
 *
 */

class NavigationPresent extends Present implements NavigatePresentAction
{
    private MainPresentAction mainPresentAction;
    private List<NavItem> navItems = new ArrayList<>();
    private CellTree cellTree;

    private void buildNavItems()
    {
        NavItem item = new NavItem();
        item.setName("Пользователи");
        item.setChildren(new ArrayList<>());

        NavItem item1 = new NavItem();
        item1.setName("Документы");

        NavItem subItem1 = new NavItem();
        subItem1.setName("Список");
        subItem1.setTag("USER_LIST");
        item.getChildren().add(subItem1);

        navItems.add(item);
        navItems.add(item1);

        // ToDo Сделать запрос к базе для получения списка навигации

        /*Warehouse.logger.info("Request for navigation tree");
        ServiceAsync<List<NavItem>> async = GWT.create(Service.class);
        async.query(Warehouse.sessionKey, "", new NavItem(), new AsyncCallback<List<NavItem>>()
        {
            @Override
            public void onFailure(Throwable throwable) {
                Warehouse.logger.severe(throwable.getMessage());
            }

            @Override
            public void onSuccess(List<NavItem> navItems) {
                model = new TreeModel(navItems);
            }
        });*/
    }

    NavigationPresent(MainPresentAction mainPresentAction)
    {
        this.mainPresentAction = mainPresentAction;
        buildNavItems();
        TreeViewModel navigateTreeModel = new NavTreeModel(this);
        cellTree = new CellTree(navigateTreeModel, null);
        cellTree.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);
    }

    CellTree getCellTree() {return cellTree; }

    @Override
    public List<NavItem> getNavItems() {return navItems;}

    @Override
    public void onNavigate(NavItem navItem) {
        String tag = navItem.getTag() == null ? "-": navItem.getTag();
        Warehouse.logger.info("NavigatePresentAction to tag '" + tag + "'");

        switch (tag)
        {
            case "USER_LIST": {
                mainPresentAction.showUserList();
                Warehouse.logger.info("OK");
                break;
            }
            default: Warehouse.logger.info("Fail");
        }
    }
}
