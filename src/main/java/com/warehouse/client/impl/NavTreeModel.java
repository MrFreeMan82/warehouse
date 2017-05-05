package com.warehouse.client.impl;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;
import com.warehouse.client.interf.Navigate;
import com.warehouse.shared.entity.NavItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дима on 05.05.2017.
 *
 */

public class NavTreeModel implements TreeViewModel
{
    private Navigate navigate;
    private List<NavItem> navItems = new ArrayList<>();
    private SingleSelectionModel<NavItem> selectionModel = new SingleSelectionModel<>();
    private Cell<NavItem> cell = new AbstractCell<NavItem>("click") {
        @Override
        public void render(Context context, NavItem navItem, SafeHtmlBuilder safeHtmlBuilder)
        {
            if(navItem != null) safeHtmlBuilder.appendEscaped(navItem.getName());
        }

        @Override
        public void onBrowserEvent(Context context, Element parent, NavItem value, NativeEvent event, ValueUpdater<NavItem> valueUpdater)
        {
            if("click".equals(event.getType()))
            {
                navigate.onNavigate(value);
            }
            super.onBrowserEvent(context, parent, value, event, valueUpdater);
        }
    };

    public NavTreeModel(Navigate navigate) {
        this.navigate = navigate;
        navItems = navigate.getNavItems();
    }

    @Override
    public <T> NodeInfo<?> getNodeInfo(T value)
    {
        if(value == null)
        {
            // Level 0
            ListDataProvider<NavItem> dataProvider = new ListDataProvider<>(navItems);
            return new DefaultNodeInfo<>(dataProvider, cell);
        }
        else if(value instanceof NavItem)
        {
            NavItem navItem = (NavItem) value;
            ListDataProvider<NavItem> dataProvider = new ListDataProvider<>(navItem.getChildren());
            return new DefaultNodeInfo<>(dataProvider, cell, selectionModel, null);
        }
        return null;
    }

    @Override
    public boolean isLeaf(Object o)
    {
        if(! (o instanceof NavItem)) return false;

        NavItem navItem = (NavItem) o;
        return (navItem.getChildren() == null) || (navItem.getChildren().size() == 0);
    }
}
