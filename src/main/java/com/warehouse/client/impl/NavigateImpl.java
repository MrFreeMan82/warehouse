package com.warehouse.client.impl;

import com.warehouse.client.Warehouse;
import com.warehouse.client.interf.Main;
import com.warehouse.client.interf.Navigate;
import com.warehouse.shared.entity.NavItem;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Дима on 05.05.2017.
 *
 */

public class NavigateImpl implements Navigate
{
    private Main main;
    private List<NavItem> navItems = new ArrayList<>();

    public NavigateImpl(Main main)
    {
        this.main = main;
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


    @Override
    public List<NavItem> getNavItems() {return navItems; }

    @Override
    public void onNavigate(NavItem navItem) {
        String tag = navItem.getTag() == null ? "-": navItem.getTag();
        Warehouse.logger.info("Navigate to tag '" + tag + "'");

        if(tag.equals("USER_LIST")) {
            main.showUserList();
            Warehouse.logger.info("OK");
        }
        else {
            Warehouse.logger.info("Fail");
        }
    }

}
